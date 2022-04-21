package com.ataraxia.mapper;

import com.ataraxia.domain.UserFollowingDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:16
 * @description
 */
public interface UserFollowingMapper extends BaseMapper<UserFollowingDO> {
    /**
     * 通过用户ID与关注者的ID删除他们之间的关系
     * @param userId 用户ID
     * @param followingId 关注者ID
     * @return 影响行数
     */
    Integer deleteUserFollowing(@Param("userId") Long userId, @Param("followingId") Long followingId);
}
