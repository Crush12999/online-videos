package com.ataraxia.service.impl;

import com.ataraxia.domain.UserFollowingDO;
import com.ataraxia.mapper.UserFollowingMapper;
import com.ataraxia.service.UserFollowingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:19
 * @description 用户关注服务接口实现类
 */
@Service
public class UserFollowingServiceImpl extends ServiceImpl<UserFollowingMapper, UserFollowingDO> implements UserFollowingService {
}
