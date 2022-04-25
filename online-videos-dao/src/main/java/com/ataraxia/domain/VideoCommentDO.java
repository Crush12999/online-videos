package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 视频评论表
 *
 * @author chuchen
 * @TableName t_video_comment
 */
@TableName(value = "t_video_comment")
public class VideoCommentDO implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 评论
     */
    private String comment;

    /**
     * 回复用户id
     */
    private Long replyUserId;

    /**
     * 根节点评论id
     */
    private Long rootId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 子评论列表
     */
    @TableField(exist = false)
    private List<VideoCommentDO> childList;

    /**
     * 创建这条评论的用户信息
     */
    @TableField(exist = false)
    private UserInfoDO userInfo;

    /**
     * 回复的是哪个用户
     */
    @TableField(exist = false)
    private UserInfoDO replyUserInfo;

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
     * 视频id
     */
    public Long getVideoId() {
        return videoId;
    }

    /**
     * 视频id
     */
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
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
     * 评论
     */
    public String getComment() {
        return comment;
    }

    /**
     * 评论
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 回复用户id
     */
    public Long getReplyUserId() {
        return replyUserId;
    }

    /**
     * 回复用户id
     */
    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    /**
     * 根节点评论id
     */
    public Long getRootId() {
        return rootId;
    }

    /**
     * 根节点评论id
     */
    public void setRootId(Long rootId) {
        this.rootId = rootId;
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

    public List<VideoCommentDO> getChildList() {
        return childList;
    }

    public void setChildList(List<VideoCommentDO> childList) {
        this.childList = childList;
    }

    public UserInfoDO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDO userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfoDO getReplyUserInfo() {
        return replyUserInfo;
    }

    public void setReplyUserInfo(UserInfoDO replyUserInfo) {
        this.replyUserInfo = replyUserInfo;
    }

    @Override
    public String toString() {
        return "VideoCommentDO{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                ", replyUserId=" + replyUserId +
                ", rootId=" + rootId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}