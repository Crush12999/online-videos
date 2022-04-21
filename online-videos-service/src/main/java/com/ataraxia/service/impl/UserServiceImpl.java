package com.ataraxia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ataraxia.domain.PageResult;
import com.ataraxia.domain.UserDO;
import com.ataraxia.domain.UserInfoDO;
import com.ataraxia.domain.constant.UserConstant;
import com.ataraxia.domain.exception.ConditionException;
import com.ataraxia.mapper.UserInfoMapper;
import com.ataraxia.mapper.UserMapper;
import com.ataraxia.service.UserInfoService;
import com.ataraxia.service.UserService;
import com.ataraxia.util.MD5Util;
import com.ataraxia.util.RSAUtil;
import com.ataraxia.util.TokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:31
 * @description 用户业务处理接口实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TokenUtil tokenUtil;

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
        userInfo.setNick(UserConstant.DEFAULT_NICKNAME);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userInfoService.save(userInfo);

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

    @Override
    public String login(UserDO user) throws Exception {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw  new ConditionException("手机号不能为空！");
        }
        UserDO dbUser = this.getUserByPhone(phone);
        if (Objects.isNull(dbUser)) {
            throw new ConditionException("当前用户不存在！");
        }
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败！");
        }
        String salt = dbUser.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if (!md5Password.equals(dbUser.getPassword())) {
            throw new ConditionException("密码错误！");
        }
        TokenUtil tokenUtil = new TokenUtil();

        return tokenUtil.generateToken(dbUser.getId());
    }

    @Override
    public UserDO getUserInfo(Long userId) {
        UserDO user = baseMapper.selectById(userId);

        LambdaQueryWrapper<UserInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfoDO::getUserId, userId);
        UserInfoDO userInfo = userInfoService.getOne(wrapper);

        user.setUserInfo(userInfo);
        return user;
    }

    @Override
    public void updateUser(UserDO user) {
        user.setUpdateTime(new Date());
        baseMapper.updateById(user);
    }

    /**
     * 分页查询用户信息
     * @param params 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageResult<UserInfoDO> getPageListUserInfos(JSONObject params) {
        Integer no = params.getInteger("no");
        Integer size = params.getInteger("size");
        Long userId = params.getLong("userId");
        String nick = params.getString("nick");
        Page<UserInfoDO> page = new Page<>(no, size);

        LambdaQueryWrapper<UserInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfoDO::getUserId, userId);
        wrapper.like(UserInfoDO::getNick, nick);
        wrapper.orderByDesc(UserInfoDO::getId);

        IPage<UserInfoDO> userInfoPage = userInfoService.page(page, wrapper);
        return new PageResult<>(userInfoPage.getTotal(), userInfoPage.getRecords());
    }


}
