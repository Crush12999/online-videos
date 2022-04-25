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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private static final String BARRAGE_KEY = "dm-video-";


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
     * 弹幕信息异步持久化
     *
     * @param barrage 弹幕信息
     */
    @Async
    @Override
    public void asyncSaveBarrage(BarrageDO barrage) {
        baseMapper.insert(barrage);
    }

    /**
     * 获取弹幕列表
     * 查询策略是优先查redis中的弹幕数据，
     * 如果没有的话查询数据库，然后把查询的数据写入redis当中
     *
     * @param videoId   视频id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 弹幕列表
     * @throws ParseException 转换异常
     */
    @Override
    public List<BarrageDO> listBarrages(Long videoId, String startTime, String endTime) throws ParseException {

        String key = BARRAGE_KEY + videoId;
        String value = redisTemplate.opsForValue().get(key);
        List<BarrageDO> list;
        if (!StringUtils.isNullOrEmpty(value)) {
            list = JSONArray.parseArray(value, BarrageDO.class);
            if (!StringUtils.isNullOrEmpty(startTime)
                    && !StringUtils.isNullOrEmpty(endTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = sdf.parse(startTime);
                Date endDate = sdf.parse(endTime);
                List<BarrageDO> childList = new ArrayList<>();
                for (BarrageDO danmu : list) {
                    Date createTime = danmu.getCreateTime();
                    if (createTime.after(startDate) && createTime.before(endDate)) {
                        childList.add(danmu);
                    }
                }
                list = childList;
            }
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("videoId", videoId);
            params.put("startTime", startTime);
            params.put("endTime", endTime);
            list = baseMapper.listBarrages(params);
            // 保存弹幕到redis
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(list));
        }
        return list;
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
