package com.ataraxia.config;

import com.ataraxia.domain.constant.UserMomentsConstant;
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

import java.util.List;

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

    /**
     * 创建生产者实例
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
     * 直接推送消息给消费者
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
                for (MessageExt msg : msgList) {
                    System.out.println(msg);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        return consumer;
    }

}
