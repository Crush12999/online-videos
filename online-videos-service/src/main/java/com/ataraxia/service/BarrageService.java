package com.ataraxia.service;

import com.ataraxia.domain.BarrageDO;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 获取弹幕列表
     *
     * @param params 查询参数
     * @return 弹幕列表
     */
    List<BarrageDO> listBarrage(Map<String, Object> params);

    /**
     * 将弹幕存到redis
     *
     * @param barrage 弹幕信息
     */
    void saveBarrageToRedis(BarrageDO barrage);
}
