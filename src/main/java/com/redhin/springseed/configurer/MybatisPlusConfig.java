package com.redhin.springseed.configurer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@MapperScan("com.redhin.springseed.mapper")
public class MybatisPlusConfig {

}
