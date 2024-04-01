package com.gstdev.cloud.message.kafka.autoconfigure.configuration;

import com.gstdev.cloud.message.kafka.autoconfigure.annotation.ConditionalOnKafkaEnabled;
import com.gstdev.cloud.message.kafka.autoconfigure.properties.KafkaProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * <p>Description: Kafka 配置 </p>
 * <p>
 * Spring Cloud Bus 默认配置参数 {@link org.springframework.cloud.bus.BusEnvironmentPostProcessor}
 *
 * @author : cc
 * @date : 2021/10/23 17:34
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnKafkaEnabled
@EnableConfigurationProperties({
  KafkaProperties.class
})
public class KafkaConfiguration {

  private static final Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- SDK [Event Message Kafka] Auto Configure.");
  }

  @Bean
  @ConditionalOnMissingBean(ConcurrentKafkaListenerContainerFactory.class)
  public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(KafkaProperties kafkaProperties, ConsumerFactory<String, String> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
    concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
    concurrentKafkaListenerContainerFactory.setAutoStartup(kafkaProperties.getEnabled());
    log.trace("[GstDev Cloud] |- Bean [Concurrent Kafka Listener ContainerFactory] Auto Configure.");
    return concurrentKafkaListenerContainerFactory;
  }
}
