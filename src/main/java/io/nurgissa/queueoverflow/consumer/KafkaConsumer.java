package io.nurgissa.queueoverflow.consumer;


import io.nurgissa.queueoverflow.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

//    @KafkaListener(topics = "queueOverFlowTopicTest", groupId = "myGroup")
    public void consumeMessages(String message){
      log.info(String.format("Consuming the message from queueOverFlowTest Topic:: %s", message));
    }

    @KafkaListener(topics = "queueOverFlowTopicTest", groupId = "myGroup")
    public void consumeJsonMessages(UserDto userDto){
        log.info(String.format("Consuming the message from queueOverFlowTest Topic:: %s", userDto.toString()));
    }
}
