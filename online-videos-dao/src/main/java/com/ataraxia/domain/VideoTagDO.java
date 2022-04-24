package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频标签关联表
 * @author chuchen
 * @TableName t_video_tag
 */
@TableName(value ="t_video_tag")
public class VideoTagDO implements Serializable {
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
     * 标签id
     */
    private Long tagId;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 标签id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 标签id
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
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

    @Override
    public String toString() {
        return "VideoTagDO{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", tagId=" + tagId +
                ", createTime=" + createTime +
                '}';
    }
}