package com.project.base;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@Configuration
public class BaseApplication {

  public static void main(String[] args) {
    SpringApplication.run(BaseApplication.class, args);
  }

}
