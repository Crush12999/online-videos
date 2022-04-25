package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏分组表
 * @author chuchen
 * @TableName t_collection_group
 */
@TableName(value ="t_collection_group")
public class CollectionGroupDO implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关注分组名称
     */
    private String name;

    /**
     * 关注分组类型：0默认分组  1用户自定义分组
     */
    private String type;

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
     * 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 关注分组名称
     */
    public String getName() {
        return name;
    }

    /**
     * 关注分组名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 关注分组类型：0默认分组  1用户自定义分组
     */
    public String getType() {
        return type;
    }

    /**
     * 关注分组类型：0默认分组  1用户自定义分组
     */
    public void setType(String type) {
        this.type = type;
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
        return "CollectionGroupDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}