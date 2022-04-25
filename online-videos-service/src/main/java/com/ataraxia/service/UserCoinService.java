package com.ataraxia.service;

import com.ataraxia.domain.UserCoinDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Ataraxia
 * @create 2022/4/25 12:49
 * @description 用户硬币业务处理层
 */
public interface UserCoinService extends IService<UserCoinDO> {

    /**
     * 查询用户硬币余额
     *
     * @param userId 用户id
     * @return 硬币数
     */
    Long getUserCoinsAmount(Long userId);

    /**
     * 更新用户硬币
     *
     * @param userId          用户id
     * @param userCoinsAmount 用户剩余硬币数
     */
    void updateUserCoinsAmount(Long userId, Long userCoinsAmount);
}
