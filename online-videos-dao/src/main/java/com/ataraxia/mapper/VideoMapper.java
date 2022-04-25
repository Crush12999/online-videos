package com.ataraxia.mapper;

import com.ataraxia.domain.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author chuchen
 * @description 针对表【t_video(视频投稿记录表)】的数据库操作Mapper
 * @createDate 2022-04-24 22:37:30
 * @Entity generator.domain.VideoDO
 */
public interface VideoMapper extends BaseMapper<VideoDO> {

    /**
     * 批量添加视频标签
     *
     * @param tags 标签列表
     * @return
     */
    Integer batchSaveVideoTags(@Param("tags") List<VideoTagDO> tags);

    /**
     * 取消收藏视频
     *
     * @param videoId 视频id
     * @param userId  用户id
     */
    void deleteVideoCollection(@Param("videoId") Long videoId, @Param("userId") Long userId);

    /**
     * 收藏视频
     *
     * @param videoCollection 视频收藏实体
     */
    void saveVideoCollection(@Param("videoCollection") VideoCollectionDO videoCollection);

    /**
     * 获取视频收藏数
     *
     * @param videoId 视频id
     * @return 视频收藏数
     */
    Long getVideoCollections(@Param("videoId") Long videoId);

    /**
     * 获取用户是否收藏该视频
     *
     * @param videoId 视频id
     * @param userId  用户id
     * @return 视频收藏实例
     */
    VideoCollectionDO getVideoCollectionByVideoIdAndUserId(@Param("videoId") Long videoId, @Param("userId") Long userId);

    /**
     * 查询当前登录用户对该视频已经投了多少硬币
     *
     * @param videoId 视频id
     * @param userId  用户id
     * @return 已投币数
     */
    VideoCoinDO getVideoCoinByVideoIdAndUserId(@Param("videoId") Long videoId, @Param("userId") Long userId);

    /**
     * 新增视频投币
     *
     * @param videoCoin 视频硬币
     */
    void saveVideoCoin(@Param("videoCoin") VideoCoinDO videoCoin);

    /**
     * 更新视频投币
     *
     * @param videoCoin 视频硬币
     */
    void updateVideoCoin(@Param("videoCoin") VideoCoinDO videoCoin);

    /**
     * 获取视频投币数
     *
     * @param videoId 视频id
     * @return 视频投币数
     */
    Long getVideoCoinsAmount(@Param("videoId") Long videoId);

    /**
     * 发布视频评论
     *
     * @param videoComment 视频评论
     */
    void saveVideoComment(VideoCommentDO videoComment);

    /**
     * 分页查询视频一级评论数量
     *
     * @param params 参数
     * @return 视频评论总数
     */
    Long pageCountVideoComments(Map<String, Object> params);

    /**
     * 分页查询视频一级评论内容
     *
     * @param params 分页参数
     * @return 视频评论内容
     */
    List<VideoCommentDO> pageListVideoComments(Map<String, Object> params);

    /**
     * 批量查询二级评论
     *
     * @param rootIdList 父评论id
     * @return 二级评论列表
     */
    List<VideoCommentDO> listBatchVideoCommentsByRootIds(@Param("rootIdList") List<Long> rootIdList);
}




