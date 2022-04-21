package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:27
 * @description 用户基本信息表
 */

@TableName("t_user_info")
public class UserInfoDO {

    private Long id;

    private Long userId;

    private String nickname;

    private String avatar;

    private String sign;

    private String gender;

    private String birth;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserInfoDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sign='" + sign + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
