package com.ataraxia.service.impl;

import com.ataraxia.domain.UserCoinDO;
import com.ataraxia.mapper.UserCoinMapper;
import com.ataraxia.service.UserCoinService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Ataraxia
 * @create 2022/4/25 12:50
 * @description 用户硬币业务接口实现
 */
@Service
public class UserCoinServiceImpl extends ServiceImpl<UserCoinMapper, UserCoinDO>
        implements UserCoinService {

    /**
     * 查询用户硬币余额
     *
     * @param userId 用户id
     * @return 硬币数
     */
    @Override
    public Long getUserCoinsAmount(Long userId) {
        LambdaQueryWrapper<UserCoinDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCoinDO::getUserId, userId);
        return baseMapper.selectOne(wrapper).getAmount();
    }

    /**
     * 更新用户硬币
     *
     * @param userId          用户id
     * @param userCoinsAmount 用户剩余硬币数
     */
    @Override
    public void updateUserCoinsAmount(Long userId, Long userCoinsAmount) {
        UserCoinDO userCoin = baseMapper.selectById(userId);
        userCoin.setAmount(userCoinsAmount);
        baseMapper.updateById(userCoin);
    }
}
