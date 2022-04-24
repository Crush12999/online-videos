package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/23 23:15
 * @description 刷新令牌实体类
 */
@TableName("t_refresh_token")
public class RefreshTokenDO {

    private Long id;

    private Long userId;

    private String refreshToken;

    private Date createTime;

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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RefreshTokenDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", refreshToken='" + refreshToken + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
