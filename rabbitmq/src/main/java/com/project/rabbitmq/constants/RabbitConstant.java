package com.project.rabbitmq.constants;

/**
 * Rabbit 常量
 */
public interface RabbitConstant {

  /**
   * 日志队列
   */
  String QUEUE_INFORM_LOG = "queue_inform_log";

  /**
   * 日志路由key
   */
  String ROUTING_KEY_LOG = "inform.#.log.#";

  /**
   * 日志交换机
   */
  String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";

}
