package com.project.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

  private String servers = "192.168.31.95:9092,192.168.31.95:9093";


  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> props = new HashMap<>();
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
    KafkaAdmin kafkaAdmin = new KafkaAdmin(props);
    return kafkaAdmin;
  }

  @Bean
  public AdminClient adminClient() {
    return AdminClient.create(kafkaAdmin().getConfig());
  }

  @Bean
  public NewTopic topicInfo(){
    return new NewTopic("test",3, (short) 3);
  }

}
