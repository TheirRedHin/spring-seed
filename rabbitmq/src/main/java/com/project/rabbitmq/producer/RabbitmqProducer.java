package com.project.rabbitmq.producer;

import com.project.base.jms.JmsProducer;
import com.project.rabbitmq.constants.RabbitConstant;
import javax.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 默认消息生产者
 */
@Primary
@Service
@ConditionalOnProperty(value = "jms.type", havingValue = "rabbitmq")
public class RabbitmqProducer implements JmsProducer {

  @Resource
  private RabbitTemplate rabbitTemplate;

  /**
   * 发送消息
   *
   * @param object 消息内容
   */
  @Override
  public void send(Object object) {
    rabbitTemplate
        .convertAndSend(RabbitConstant.EXCHANGE_TOPICS_INFORM, RabbitConstant.ROUTING_KEY_LOG,
            object);
  }

}
