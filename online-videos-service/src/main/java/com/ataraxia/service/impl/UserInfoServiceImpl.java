package com.ataraxia.service.impl;

import com.ataraxia.domain.UserInfoDO;
import com.ataraxia.mapper.UserInfoMapper;
import com.ataraxia.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Ataraxia
 * @create 2022/4/21 11:18
 * @description 用户基本信息服务实现类
 */
@Service("UserInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDO> implements UserInfoService {
}
