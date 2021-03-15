package com.redhin.rabbitmq.producer;

import com.redhin.rabbitmq.config.RabbitmqConfig;
import javax.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class HelloProducer {

  @Resource
  private RabbitTemplate rabbitTemplate;

  public void send() {
    String message = "this is text string";
    rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.email", message);
    rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.sms", message);
  }

}
