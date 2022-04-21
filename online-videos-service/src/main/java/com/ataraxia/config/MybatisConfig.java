package com.ataraxia.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ataraxia
 * @create 2022/4/21 14:30
 * @description Mybatis配置类
 */
@Configuration
@MapperScan("com.ataraxia.mapper")
public class MybatisConfig {
}
