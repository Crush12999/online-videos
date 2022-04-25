package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频硬币表
 *
 * @author chuchen
 * @TableName t_video_coin
 */
@TableName(value = "t_video_coin")
public class VideoCoinDO implements Serializable {

    /**
     * 视频投稿id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 视频投稿id
     */
    private Long videoId;

    /**
     * 投币数
     */
    private Integer amount;

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
     * 视频投稿id
     */
    public Long getId() {
        return id;
    }

    /**
     * 视频投稿id
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
     * 视频投稿id
     */
    public Long getVideoId() {
        return videoId;
    }

    /**
     * 视频投稿id
     */
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    /**
     * 投币数
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 投币数
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
        return "VideoCoinDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", videoId=" + videoId +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}