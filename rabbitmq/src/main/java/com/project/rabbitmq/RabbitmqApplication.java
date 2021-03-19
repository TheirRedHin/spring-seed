package com.project.rabbitmq;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@Configuration
@MapperScan(basePackages = {"com.project.**.dao"})
@ComponentScan({"com.project.*"})
public class RabbitmqApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqApplication.class, args);
  }

}
