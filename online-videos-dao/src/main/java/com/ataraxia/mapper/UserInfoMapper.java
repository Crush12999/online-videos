package com.ataraxia.mapper;

import com.ataraxia.domain.UserInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ataraxia
 * @create 2022/4/21 11:19
 * @description
 */

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {
}
