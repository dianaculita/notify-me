package com.project.notifyme.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${running-wishes.topic-name}")
    private String topicName;

    @Bean
    public NewTopic upcomingCompetitionsTopic() {
        return TopicBuilder.name(topicName)
                           .partitions(2)
                           .replicas(1)
                           .build();
    }
}
