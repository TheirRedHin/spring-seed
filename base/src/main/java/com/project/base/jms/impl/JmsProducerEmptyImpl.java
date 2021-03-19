package com.project.base.jms.impl;

import com.project.base.jms.JmsProducer;
import org.springframework.stereotype.Service;

/**
 * 消息生产者的空实现，不做任何动作，作用为防止 JmsProducer 的其他实现报错
 */
@Service
public class JmsProducerEmptyImpl implements JmsProducer {

  @Override
  public void send(String exchange, String routingKey, Object object) {

  }
}
