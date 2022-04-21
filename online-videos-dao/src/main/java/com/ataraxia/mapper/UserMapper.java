package com.ataraxia.mapper;

import com.ataraxia.domain.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:34
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
