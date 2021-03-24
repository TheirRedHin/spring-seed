package com.project.base.jms;

/**
 * 消息生产者
 */
public interface JmsProducer {

  /**
   * 发送消息
   *
   * @param object 消息
   */
  void send(Object object);

}
