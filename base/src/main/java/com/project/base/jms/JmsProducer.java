package com.project.base.jms;

/**
 * 消息生产者
 */
public interface JmsProducer {

  /**
   * 发送消息
   *
   * @param exchange   交换机
   * @param routingKey 路由key
   * @param object     消息
   */
  void send(String exchange, String routingKey, Object object);

}
