package com.project.rabbitmq.consumer;

import com.project.rabbitmq.config.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloConsumer {

  // 监听email队列
  @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
  public void receiveEmail(String msg, Message message, Channel channel) {
    System.out.println("------------email------------");
    System.out.println(msg);
    System.out.println("------------email------------");
  }

  // 监听sms队列
  @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
  public void receiveSms(String msg, Message message, Channel channel) {
    System.out.println("------------sms------------");
    System.out.println(msg);
    System.out.println("------------sms------------");
  }

}
