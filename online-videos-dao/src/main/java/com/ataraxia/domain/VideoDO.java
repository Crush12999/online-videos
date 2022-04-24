package com.ataraxia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 视频投稿记录表
 * @author chuchen
 * @TableName t_video
 */
@TableName(value ="t_video")
public class VideoDO implements Serializable {
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
     * 视频链接
     */
    private String url;

    /**
     * 封面链接
     */
    private String thumbnail;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频类型：0原创 1转载
     */
    private String type;

    /**
     * 视频时长
     */
    private String duration;

    /**
     * 所在分区：0鬼畜 1音乐 2电影
     */
    private String area;

    /**
     * 视频简介
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标签列表
     */
    @TableField(exist = false)
    private List<VideoTagDO> videoTagList;

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
     * 视频链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 视频链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 封面链接
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 封面链接
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 视频标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 视频标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 视频类型：0原创 1转载
     */
    public String getType() {
        return type;
    }

    /**
     * 视频类型：0原创 1转载
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 视频时长
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 视频时长
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * 所在分区：0鬼畜 1音乐 2电影
     */
    public String getArea() {
        return area;
    }

    /**
     * 所在分区：0鬼畜 1音乐 2电影
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 视频简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 视频简介
     */
    public void setDescription(String description) {
        this.description = description;
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

    public List<VideoTagDO> getVideoTagList() {
        return videoTagList;
    }

    public void setVideoTagList(List<VideoTagDO> videoTagList) {
        this.videoTagList = videoTagList;
    }

    @Override
    public String toString() {
        return "VideoDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", url='" + url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", area='" + area + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}