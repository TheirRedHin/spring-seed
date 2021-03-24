package com.project.rabbitmq.consumer;

import com.project.rabbitmq.constants.RabbitConstant;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 日志消息消费者
 */
@Service
@ConditionalOnProperty(value = "jms.type", havingValue = "rabbitmq")
public class RabbitmqLogConsumer {

  protected static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqLogConsumer.class);

  /**
   * 监听 QUEUE_INFORM_LOG 队列
   *
   * @param msg     消息内容
   * @param message 消息详细
   * @param channel 来源
   */
  @RabbitListener(queues = {RabbitConstant.QUEUE_INFORM_LOG})
  public void receiveSms(String msg, Message message, Channel channel) {
    // 处理日志
    LOGGER.info(msg);
  }

}
