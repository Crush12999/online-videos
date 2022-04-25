package com.ataraxia.mapper;

import com.ataraxia.domain.BarrageDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author Ataraxia
 * @create 2022/4/25 16:21
 * @description 弹幕业务持久层
 */
public interface BarrageMapper extends BaseMapper<BarrageDO> {

    /**
     * 获取弹幕列表
     *
     * @param params 查询参数
     * @return 弹幕列表
     */
    List<BarrageDO> listBarrage(Map<String, Object> params);
}
