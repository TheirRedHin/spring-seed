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

  @KafkaListener(topics = "sysLogQueue", containerFactory = "kafkaListenerContainerFactory")
  public void receiveQueue(ConsumerRecord<?, ?> record) {
    String info = record.value().toString();
    LOGGER.info("消息日志");
    LOGGER.info(info);
  }

}
