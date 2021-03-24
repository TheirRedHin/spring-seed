package com.project.rabbitmq.config;

import com.project.base.constants.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "jms.type", havingValue = "rabbitmq")
public class RabbitmqConfig {

  /**
   * 声明TOPICS工作模式的交换机
   */
  @Bean(RabbitConstant.EXCHANGE_TOPICS_INFORM)
  public Exchange exchangeTopicsInform() {
    // durable(true) 表明重启之后交换机还在
    return ExchangeBuilder.topicExchange(RabbitConstant.EXCHANGE_TOPICS_INFORM).durable(true)
        .build();
  }

  /**
   * 声明QUEUE_INFORM_LOG队列
   *
   * @return 消息队列
   */
  @Bean(RabbitConstant.QUEUE_INFORM_LOG)
  public Queue queueInformLog() {
    return new Queue(RabbitConstant.QUEUE_INFORM_LOG);
  }

  /**
   * 交换机与QUEUE_INFORM_LOG队列绑定
   *
   * @param queue    队列
   * @param exchange 交换机
   * @return 绑定信息
   */
  @Bean
  public Binding bindingQueueInformLog(@Qualifier(RabbitConstant.QUEUE_INFORM_LOG) Queue queue,
      @Qualifier(RabbitConstant.EXCHANGE_TOPICS_INFORM) Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(RabbitConstant.ROUTING_KEY_LOG).noargs();
  }

}
