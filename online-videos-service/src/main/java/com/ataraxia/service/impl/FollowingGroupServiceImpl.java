package com.ataraxia.service.impl;

import com.ataraxia.domain.FollowingGroupDO;
import com.ataraxia.mapper.FollowingGroupMapper;
import com.ataraxia.service.FollowingGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:13
 * @description 关注分组服务实现
 */
@Service
public class FollowingGroupServiceImpl extends ServiceImpl<FollowingGroupMapper, FollowingGroupDO> implements FollowingGroupService {
}
