package com.project.kafka.producer;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.project.base.jms.JmsProducer;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

@Primary
@Service
@ConditionalOnProperty(value = "jms.type", havingValue = "kafka")
public class KafkaProducer implements JmsProducer {


  private static final Log logger = LogFactory.getLog(KafkaProducer.class);
  @Resource
  KafkaTemplate<Integer, Object> kafkaTemplate;
  //  @Value("${jms.topic.name:eipTopic}")
//  private String topicName;
  @Resource
  private ProducerListener producerListener;

  @Override
  public void send(Object object) {
    kafkaTemplate.setProducerListener(producerListener);
    kafkaTemplate.send("sysLogQueue", object.toString());
  }
}
