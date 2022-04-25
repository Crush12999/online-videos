package com.ataraxia.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ataraxia.domain.BarrageDO;
import com.ataraxia.mapper.BarrageMapper;
import com.ataraxia.service.BarrageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ataraxia
 * @create 2022/4/25 16:24
 * @description 弹幕服务处理层实现类
 */
@Service
public class BarrageServiceImpl extends ServiceImpl<BarrageMapper, BarrageDO>
        implements BarrageService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 弹幕信息持久化
     *
     * @param barrage 弹幕信息
     */
    @Override
    public void saveBarrage(BarrageDO barrage) {
        baseMapper.insert(barrage);
    }

    /**
     * 获取弹幕列表
     *
     * @param params 查询参数
     * @return 弹幕列表
     */
    @Override
    public List<BarrageDO> listBarrage(Map<String, Object> params) {
        return baseMapper.listBarrage(params);
    }

    /**
     * 将弹幕存到redis
     *
     * @param barrage 弹幕信息
     */
    @Override
    public void saveBarrageToRedis(BarrageDO barrage) {
        String key = "barrage-video-" + barrage.getVideoId();
        String value = redisTemplate.opsForValue().get(key);
        List<BarrageDO> list = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(value)) {
            list = JSONArray.parseArray(value, BarrageDO.class);
        }
        list.add(barrage);
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(list));
    }
}
