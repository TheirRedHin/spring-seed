package com.project.rabbitmq;

import com.project.rabbitmq.producer.RabbitmqProducer;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

  @Resource
  private RabbitmqProducer rabbitmqProducer;

  @Test
  public void hello() throws Exception {
    rabbitmqProducer
        .send("test");
  }

}