package io.nurgissa.queueoverflow.producer;


import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaJsonProducer {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public void sendMessage(UserDto userDto){
        Message<UserDto> message = MessageBuilder
                .withPayload(userDto)
                .setHeader(KafkaHeaders.TOPIC, "queueOverFlowTopicTest")
                .build();

        kafkaTemplate.send(message);
    }
}
