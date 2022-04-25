package com.ataraxia.mapper;

import com.ataraxia.domain.VideoCollectionDO;
import com.ataraxia.domain.VideoTagDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ataraxia.domain.VideoDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
}




