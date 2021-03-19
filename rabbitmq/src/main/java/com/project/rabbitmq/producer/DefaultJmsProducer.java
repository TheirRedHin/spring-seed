package com.project.rabbitmq.producer;

import com.project.base.jms.JmsProducer;
import javax.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 默认消息生产者
 */
@Primary
@Service
public class DefaultJmsProducer implements JmsProducer {

  @Resource
  private RabbitTemplate rabbitTemplate;

  /**
   * 发送消息
   *
   * @param exchange   交换机
   * @param routingKey 路由key
   * @param object     消息内容
   */
  @Override
  public void send(String exchange, String routingKey, Object object) {
    rabbitTemplate.convertAndSend(exchange, routingKey, object);
  }

}
