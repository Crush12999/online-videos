package com.ataraxia.service;

import com.ataraxia.domain.FollowingGroupDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:12
 * @description 关注分组服务接口
 */
public interface FollowingGroupService extends IService<FollowingGroupDO> {

    /**
     * 通过类型获取关注分组
     * @param type 类型
     * @return 关注分组
     */
    FollowingGroupDO getByType(String type);

    /**
     * 通过Id获取关注分组
     * @param id 分组ID
     * @return 关注分组
     */
    FollowingGroupDO getById(Long id);

    /**
     * 通过用户ID把用户分组都拿到
     * @param userId
     * @return
     */
    List<FollowingGroupDO> getByUserId(Long userId);

    /**
     * 添加关注分组
     * @param followingGroup 分组信息
     * @return 新添加的分组ID
     */
    Long saveUserFollowingGroups(FollowingGroupDO followingGroup);

    /**
     * 查询关注分组
     * @param userId 用户ID
     * @return 分组列表
     */
    List<FollowingGroupDO> getUserFollowingGroups(Long userId);
}
