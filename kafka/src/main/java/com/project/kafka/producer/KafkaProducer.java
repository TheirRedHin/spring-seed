package com.project.kafka.producer;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.project.base.jms.JmsProducer;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Primary
@Service
@ConditionalOnProperty(value = "jms.type", havingValue = "kafka")
public class KafkaProducer implements JmsProducer {

  private static final Log logger = LogFactory.getLog(KafkaProducer.class);

  @Resource
  KafkaTemplate<String, String> kafkaTemplate;

  @Override
  public void send(Object object) {
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate
        .send("test", object.toString());
    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onSuccess(SendResult<String, String> result) {
        logger.info("生产者-发送消息成功：" + result.toString());
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.info("生产者-发送消息失败：" + ex.getMessage());
      }
    });
  }
}
