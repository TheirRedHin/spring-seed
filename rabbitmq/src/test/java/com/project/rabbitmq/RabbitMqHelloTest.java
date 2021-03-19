package com.project.rabbitmq;

import com.project.base.constants.RabbitConstant;
import com.project.rabbitmq.producer.DefaultJmsProducer;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

  @Resource
  private DefaultJmsProducer defaultJmsProducer;

  @Test
  public void hello() throws Exception {
    defaultJmsProducer
        .send(RabbitConstant.EXCHANGE_TOPICS_INFORM, RabbitConstant.ROUTING_KEY_LOG, "test");
  }

}