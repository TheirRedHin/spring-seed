package com.redhin.rabbitmq;

import com.redhin.rabbitmq.producer.HelloProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

  @Autowired
  private HelloProducer helloProducer;

  @Test
  public void hello() throws Exception {
    helloProducer.send();
  }

}