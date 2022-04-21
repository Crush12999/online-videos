package com.ataraxia.service;

import com.alibaba.fastjson.JSONObject;
import com.ataraxia.domain.PageResult;
import com.ataraxia.domain.UserDO;
import com.ataraxia.domain.UserInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:31
 * @description 用户业务处理接口
 */
public interface UserService extends IService<UserDO> {
    /**
     * 创建用户
     * @param user 用户
     */
    void insertUser(UserDO user);

    /**
     * 通过手机号获取用户
     * @param phone 手机号
     * @return 用户
     */
    UserDO getUserByPhone(String phone);

    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     */
    String login(UserDO user) throws Exception;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserDO getUserInfo(Long userId);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(UserDO user);

    /**
     * 分页查询用户信息
     * @param params 分页查询参数
     * @return 分页查询结果
     */
    PageResult<UserInfoDO> getPageListUserInfos(JSONObject params);
}
