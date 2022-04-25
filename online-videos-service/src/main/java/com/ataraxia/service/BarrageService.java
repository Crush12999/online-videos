package com.ataraxia.service;

import com.ataraxia.domain.BarrageDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Ataraxia
 * @create 2022/4/25 16:23
 * @description 弹幕业务处理层
 */
public interface BarrageService extends IService<BarrageDO> {

    /**
     * 弹幕信息持久化
     *
     * @param barrage 弹幕信息
     */
    void saveBarrage(BarrageDO barrage);

    /**
     * 弹幕信息异步持久化
     *
     * @param barrage 弹幕信息
     */
    void asyncSaveBarrage(BarrageDO barrage);

    /**
     * 获取弹幕列表
     *
     * @param videoId   视频id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 弹幕列表
     * @throws ParseException 转换异常
     */
    List<BarrageDO> listBarrages(Long videoId, String startTime, String endTime) throws ParseException;

    /**
     * 将弹幕存到redis
     *
     * @param barrage 弹幕信息
     */
    void saveBarrageToRedis(BarrageDO barrage);
}
