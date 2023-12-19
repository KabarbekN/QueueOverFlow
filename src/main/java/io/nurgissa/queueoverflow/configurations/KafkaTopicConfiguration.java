package io.nurgissa.queueoverflow.configurations;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic myTopicTest(){
        return TopicBuilder
                .name("queueOverFlowTopicTest")
                .build();
    }
}
