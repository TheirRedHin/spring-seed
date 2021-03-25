package com.project.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "jms.type", havingValue = "kafka")
public class KafkaLogConsumer {

  protected static final Logger LOGGER = LoggerFactory.getLogger(KafkaLogConsumer.class);

  @KafkaListener(topics = "test", containerFactory = "kafkaListenerContainerFactory")
  public void testQueue(ConsumerRecord<?, ?> record) {
    String info = record.value().toString();
    LOGGER.info("消费者-消息处理成功：" +info);
  }

}
