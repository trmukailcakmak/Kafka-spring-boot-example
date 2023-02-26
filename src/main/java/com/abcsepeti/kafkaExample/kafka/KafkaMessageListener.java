package com.abcsepeti.kafkaExample.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaMessageListener {
    final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "${spring.kafka.topic.message}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String kafkaMessagePayload) {
        try {
            log.debug("received:" + kafkaMessagePayload);

            KafkaMessage request = mapper.readValue(kafkaMessagePayload, KafkaMessage.class);
            log.debug("received:" + request.getMessage());

            // log.debug("consumed:" + kafkaMessagePayload);
        } catch (JsonProcessingException e) {
            log.error("mapping failed", e);
        } catch (Exception e) {
            log.error("send mail failed", e);
        }
    }

}