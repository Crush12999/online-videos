package com.ataraxia.mapper;

import com.ataraxia.domain.VideoTagDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ataraxia.domain.VideoDO;
import org.apache.ibatis.annotations.Param;

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
     * @param tags 标签列表
     * @return
     */
    Integer batchSaveVideoTags(@Param("tags") List<VideoTagDO> tags);
}




