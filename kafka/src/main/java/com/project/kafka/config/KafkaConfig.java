package com.project.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@EnableKafka
@Configuration
@ConditionalOnProperty(value = "jms.type", havingValue = "kafka")
public class KafkaConfig {

  @Value("${server.port}")
  public String applicationPort;
  //@Value("${spring.kafka.bootstrap-servers}")
  private String servers = "192.168.31.95:9092,192.168.31.95:9093";
//  @Value("${spring.kafka.group-id:eipGroup}")
//  private String groupId;

  public Map<String, Object> producerConfigs() {

    Map<String, Object> props = new HashMap<>(10);
    // 集群的服务器地址
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
    //  消息缓存
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
    // 生产者空间不足时，send()被阻塞的时间，默认60s
    props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 6000);
    // 生产者重试次数
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    // 指定ProducerBatch（消息累加器中BufferPool中的）可复用大小
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
    // 生产者会在ProducerBatch被填满或者等待超过LINGER_MS_CONFIG时发送
    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    // key 和 value 的序列化
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    // 客户端id
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.topinfo");
    return props;
  }


  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }


  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap(8);
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
    // 消费者组
    props.put(ConsumerConfig.GROUP_ID_CONFIG, applicationPort);
    // 自动位移提交
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
    // 自动位移提交间隔时间
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
    props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000);
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
    // 消费组失效超时时间
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
    // 位移丢失和位移越界后的恢复起始位置
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    // key 和 value 的反序列化
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");

    return props;
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    // 设置消费者工厂
    factory.setConsumerFactory(consumerFactory());
    // 要创建的消费者数量(10 个线程并发处理)
    factory.setConcurrency(10);
    return factory;
  }

}
