package com.ataraxia.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ataraxia.domain.UserFollowingDO;
import com.ataraxia.domain.UserMomentDO;
import com.ataraxia.domain.constant.UserMomentsConstant;
import com.ataraxia.service.UserFollowingService;
import com.ataraxia.websocket.WebSocketService;
import com.mysql.cj.util.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ataraxia
 * @create 2022/4/22 23:26
 * @description RocketMQ配置类
 */
@Configuration
public class RocketMqConfig {

    @Value("${rocketmq.name.server.address}")
    private String nameServerAddress;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserFollowingService userFollowingService;


    /**
     * 创建动态生产者实例
     *
     * @return
     * @throws MQClientException
     */
    @Bean("momentsProducer")
    public DefaultMQProducer momentsProducer() throws MQClientException {
        // 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer(UserMomentsConstant.GROUP_MOMENTS);
        // 设置名称服务器地址
        producer.setNamesrvAddr(nameServerAddress);
        producer.start();
        return producer;
    }

    /**
     * 直接推送动态消息给消费者
     */
    @Bean("momentsConsumer")
    public DefaultMQPushConsumer momentsConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(UserMomentsConstant.GROUP_MOMENTS);
        consumer.setNamesrvAddr(nameServerAddress);

        // 订阅内容，第一个参数是主题，第二个参数是相关子主题，"*"表示所有相关主题都订阅
        consumer.subscribe(UserMomentsConstant.TOPIC_MOMENTS, "*");

        // 给消费者设置监听器，当生产者推送新消息到MQ的时候，MQ会把相关消息推给消费者，消费者需要监听器抓取消息。
        // 使用并发监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            /**
             * 监听器
             * @param msgList 消息扩充，再传出的消息基础上增加一些结果
             * @param consumeConcurrentlyContext 处理的上下文
             * @return 处理的结果返回
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt msg = msgList.get(0);
                if (Objects.isNull(msg)) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                // 获取实体类字符串
                String bodyStr = new String(msg.getBody());
                // 获取创建动态的时候的实体类
                UserMomentDO userMoment = JSONObject.toJavaObject(JSONObject.parseObject(bodyStr), UserMomentDO.class);
                // 为了方便查找所有跟这个用户相关的，订阅了这个用户的所有订阅者
                Long userId = userMoment.getUserId();
                // 查找粉丝
                List<UserFollowingDO> fanList = userFollowingService.listUserFans(userId);
                // 给每个粉丝发送新发布的动态
                for (UserFollowingDO fan : fanList) {
                    // 把动态放到redis里，订阅用户再从缓存中获取
                    String redisKey = "subscribed-" + fan.getUserId();
                    // 获取用户订阅的列表
                    String subscribedListStr = redisTemplate.opsForValue().get(redisKey);
                    List<UserMomentDO> subscribedList;
                    if (StringUtils.isNullOrEmpty(subscribedListStr)) {
                        subscribedList = new ArrayList<>();
                    } else {
                        subscribedList = JSONArray.parseArray(subscribedListStr, UserMomentDO.class);
                    }
                    subscribedList.add(userMoment);
                    redisTemplate.opsForValue().set(redisKey, JSONObject.toJSONString(subscribedList));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        return consumer;
    }

    /**
     * 创建弹幕生产者实例
     *
     * @return
     * @throws MQClientException
     */
    @Bean("barrageProducer")
    public DefaultMQProducer barrageProducer() throws MQClientException {
        // 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer(UserMomentsConstant.GROUP_BARRAGES);
        // 设置名称服务器地址
        producer.setNamesrvAddr(nameServerAddress);
        producer.start();
        return producer;
    }

    /**
     * 直接推送弹幕消息给消费者
     */
    @Bean("barrageConsumer")
    public DefaultMQPushConsumer barrageConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(UserMomentsConstant.TOPIC_BARRAGES);
        // 设置NameServer的地址
        consumer.setNamesrvAddr(nameServerAddress);

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的信息
        // 订阅内容，第一个参数是主题，第二个参数是相关子主题，"*"表示所有相关主题都订阅
        consumer.subscribe(UserMomentsConstant.TOPIC_MOMENTS, "*");

        // 给消费者设置监听器，当生产者推送新消息到MQ的时候，MQ会把相关消息推给消费者，消费者需要监听器抓取消息。
        // 使用并发监听，注册回调实现类来处理从broker拉取回来的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            /**
             * 监听器
             * @param msgList 消息扩充，再传出的消息基础上增加一些结果
             * @param consumeConcurrentlyContext 处理的上下文
             * @return 处理的结果返回
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt msg = msgList.get(0);
                byte[] msgByte = msg.getBody();
                String bodyStr = new String(msgByte);
                JSONObject jsonObject = JSONObject.parseObject(bodyStr);
                String sessionId = jsonObject.getString("sessionId");
                String message = jsonObject.getString("message");
                WebSocketService webSocketService = WebSocketService.WEBSOCKET_MAP.get(sessionId);
                if (webSocketService.getSession().isOpen()) {
                    try {
                        webSocketService.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        return consumer;
    }

}
