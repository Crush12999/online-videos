package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户动态表
 * @author chuchen
 * @TableName t_user_moment
 */
@TableName(value ="t_user_moment")
public class UserMomentDO implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 动态类型：0视频 1直播 2专栏动态
     */
    private String type;

    /**
     * 内容详情ID
     */
    private Long contentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 动态类型：0视频 1直播 2专栏动态
     */
    public String getType() {
        return type;
    }

    /**
     * 动态类型：0视频 1直播 2专栏动态
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 内容详情ID
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * 内容详情ID
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserMomentDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", type='" + type + '\'' +
                ", contentId=" + contentId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}