package com.trainingsv2.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Component
@Slf4j
public class KafkaUtils {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaUtils(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

    @KafkaListener(topics = "amaris-training")
    void listener(String message) {
        log.info("RECEIVE MESSAGE FROM KAFKA " + message);
    }

    @KafkaListener(topics = "amaris-training", groupId = "group-1")
    void commonListenerForMultipleTopics(String message) {
        log.info("RECEIVE MESSAGE FROM KAFKA GROUP 1 " + message);
    }
}
