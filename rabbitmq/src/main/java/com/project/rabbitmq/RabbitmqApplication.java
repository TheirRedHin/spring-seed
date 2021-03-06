package com.project.rabbitmq;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@Configuration
public class RabbitmqApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqApplication.class, args);
  }

}
