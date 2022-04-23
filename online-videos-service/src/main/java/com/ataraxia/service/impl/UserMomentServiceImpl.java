package com.ataraxia.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ataraxia.domain.UserMomentDO;
import com.ataraxia.domain.constant.UserMomentsConstant;
import com.ataraxia.mapper.UserMomentMapper;
import com.ataraxia.service.UserMomentService;
import com.ataraxia.util.RocketMqUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
* @author chuchen
* @description 针对表【t_user_moment(用户动态表)】的数据库操作Service实现
* @createDate 2022-04-23 00:00:14
*/
@Service
public class UserMomentServiceImpl extends ServiceImpl<UserMomentMapper, UserMomentDO>
    implements UserMomentService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 添加用户动态
     * @param userMoment 用户动态
     * @throws Exception MQ处理异常
     */
    @Override
    public void insertUserMoments(UserMomentDO userMoment) throws Exception {
        userMoment.setCreateTime(new Date());
        baseMapper.insert(userMoment);

        // 用户动态添加成功后，发送消息给 MQ 告知相关订阅者
        DefaultMQProducer producer = (DefaultMQProducer) applicationContext.getBean("momentsProducer");
        Message msg = new Message(UserMomentsConstant.TOPIC_MOMENTS, JSONObject.toJSONString(userMoment).getBytes(StandardCharsets.UTF_8));
        RocketMqUtil.syncSendMsg(producer, msg);
    }

    /**
     * 查询用户关注的人的相关动态信息
     * @param userId 当前登录用户ID
     * @return 用户关注的人的相关动态信息列表
     */
    @Override
    public List<UserMomentDO> listUserSubscribedMoments(Long userId) {
        String redisKey = "subscribed-" + userId;
        String listStr = redisTemplate.opsForValue().get(redisKey);
        return JSONArray.parseArray(listStr, UserMomentDO.class);
    }
}




