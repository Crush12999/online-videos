package com.ataraxia.service.impl;

import com.ataraxia.domain.UserDO;
import com.ataraxia.domain.UserInfoDO;
import com.ataraxia.domain.constant.UserConstant;
import com.ataraxia.domain.exception.ConditionException;
import com.ataraxia.mapper.UserInfoMapper;
import com.ataraxia.mapper.UserMapper;
import com.ataraxia.service.UserService;
import com.ataraxia.util.MD5Util;
import com.ataraxia.util.RSAUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:31
 * @description 用户业务处理接口实现
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 创建用户
     *
     * @param user 用户
     */
    @Override
    public void insertUser(UserDO user) {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw  new ConditionException("手机号不能为空！");
        }
        UserDO dbUser = this.getUserByPhone(phone);
        if (Objects.nonNull(dbUser)) {
            throw new ConditionException("该手机号已经被注册！");
        }
        Date now = new Date();
        // 通过当前时间戳来生成盐值
        String salt = String.valueOf(now.getTime());
        // 前端经过RSA加密过的密码
        String password = user.getPassword();
        // 原始明文密码
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败！");
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(now);
        baseMapper.insert(user);

        // 添加用户信息
        UserInfoDO userInfo = new UserInfoDO();
        // 插入成功后可以得到用户ID
        userInfo.setUserId(user.getId());
        userInfo.setNickname(UserConstant.DEFAULT_NICKNAME);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userInfoMapper.insert(userInfo);

    }

    /**
     * 通过手机号获取用户
     *
     * @param phone 手机号
     * @return 用户
     */
    @Override
    public UserDO getUserByPhone(String phone) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getPhone, phone);
        return baseMapper.selectOne(wrapper);
    }


}
