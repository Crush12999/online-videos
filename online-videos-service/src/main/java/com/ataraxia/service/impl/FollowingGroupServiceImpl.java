package com.ataraxia.service.impl;

import com.ataraxia.domain.FollowingGroupDO;
import com.ataraxia.domain.constant.UserConstant;
import com.ataraxia.mapper.FollowingGroupMapper;
import com.ataraxia.service.FollowingGroupService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:13
 * @description 关注分组服务实现
 */
@Service
public class FollowingGroupServiceImpl extends ServiceImpl<FollowingGroupMapper, FollowingGroupDO> implements FollowingGroupService {

    /**
     * 通过类型获取关注分组
     * @param type 类型
     * @return 关注分组
     */
    @Override
    public FollowingGroupDO getByType(String type) {
        LambdaQueryWrapper<FollowingGroupDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowingGroupDO::getType, type);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 通过Id获取关注分组
     * @param id 分组ID
     * @return 关注分组
     */
    @Override
    public FollowingGroupDO getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<FollowingGroupDO> listByUserId(Long userId) {
        LambdaQueryWrapper<FollowingGroupDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowingGroupDO::getUserId, userId).or().in(FollowingGroupDO::getType, "0", "1", "2");
        return baseMapper.selectList(wrapper);
    }

    /**
     * 添加关注分组
     * @param followingGroup 分组信息
     * @return 新添加的分组ID
     */
    @Override
    public Long saveUserFollowingGroups(FollowingGroupDO followingGroup) {
        followingGroup.setCreateTime(new Date());
        followingGroup.setType(UserConstant.USER_FOLLOWING_GROUP_TYPE_USER);
        baseMapper.insert(followingGroup);
        return followingGroup.getId();
    }

    /**
     * 查询关注分组
     * @param userId 用户ID
     * @return 分组列表
     */
    @Override
    public List<FollowingGroupDO> listUserFollowingGroups(Long userId) {
        LambdaQueryWrapper<FollowingGroupDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowingGroupDO::getUserId, userId);
        return baseMapper.selectList(wrapper);
    }

}
