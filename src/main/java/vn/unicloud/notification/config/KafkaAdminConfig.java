package vn.unicloud.notification.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaAdminConfig {
    @Bean
    public NewTopic websiteMessageTopic() {
        return TopicBuilder.name("emailPaygate")
                .partitions(20)
                .replicas(1)
                .build();
    }
}
