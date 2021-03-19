package com.project.base.configurer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置类
 */
@Configuration
@MapperScan(basePackages = {"com.project.**.mapper"})
public class MybatisPlusConfig {

}
