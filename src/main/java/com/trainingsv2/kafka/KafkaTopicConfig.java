package com.trainingsv2.kafka;

import com.trainingsv2.common.utils.GlobalConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(GlobalConstants.KAFKA_BASE_TOPIC)
                .replicas(1)
                .partitions(10)
                .build();
    }
}
