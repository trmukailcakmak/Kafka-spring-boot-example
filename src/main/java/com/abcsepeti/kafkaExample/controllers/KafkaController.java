package com.abcsepeti.kafkaExample.controllers;

import com.abcsepeti.kafkaExample.kafka.KafkaMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Value("${spring.kafka.topic.message}")
    private String topic;
    private KafkaTemplate<String,KafkaMessage> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @PostMapping("/send")
    @ApiOperation("Sending data on apache kafka")
    public ResponseEntity<Object> send() {
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setMessage("Hello kafka");
        kafkaTemplate.send(topic,kafkaMessage);
        return new ResponseEntity<>("data sended to apache kafka", HttpStatus.OK);
    }

}
