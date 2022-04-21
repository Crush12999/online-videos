package com.ataraxia.service.impl;

import com.ataraxia.domain.UserInfoDO;
import com.ataraxia.mapper.UserInfoMapper;
import com.ataraxia.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/21 11:18
 * @description 用户基本信息服务实现类
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDO> implements UserInfoService {

    /**
     * 更新用户信息
     * @param userInfo
     */
    @Override
    public void updateUserInfos(UserInfoDO userInfo) {
        userInfo.setUpdateTime(new Date());
        LambdaQueryWrapper<UserInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfoDO::getUserId, userInfo.getUserId());
        baseMapper.update(userInfo, wrapper);
    }
}
