package com.ataraxia.mapper;

import com.ataraxia.domain.RefreshTokenDO;
import com.ataraxia.domain.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:34
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 删除刷新token
     * @param refreshToken 刷新状态的token
     * @param userId 用户ID
     */
    void deleteRefreshToken(@Param("refreshToken") String refreshToken, @Param("userId") Long userId);

    /**
     * 插入刷新状态用的token
     * @param refreshToken 刷新状态的token
     * @param userId 用户ID
     * @param createTime 创建时间
     */
    void insertRefreshToken(@Param("refreshToken") String refreshToken, @Param("userId") Long userId, @Param("createTime") Date createTime);

    /**
     * 获取刷新令牌实例
     * @param refreshToken 刷新令牌
     * @return 刷新令牌实例
     */
    RefreshTokenDO getRefreshTokenDetail(@Param("refreshToken") String refreshToken);
}
