package com.project.base.jms;

/**
 * 消息生产者
 */
public interface JmsProducer {

  void send(String exchange, String routingKey, Object object);

}
