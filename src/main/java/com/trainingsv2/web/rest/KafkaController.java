package com.trainingsv2.web.rest;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.common.utils.KafkaUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    private final KafkaUtils kafkaUtils;
    private final ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    public KafkaController(KafkaUtils kafkaUtils, ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory) {
        this.kafkaUtils = kafkaUtils;
        this.kafkaListenerContainerFactory = kafkaListenerContainerFactory;
    }

    @PostMapping("publish")
    public ResponseEntity<String> createAccount(@RequestParam(required = false, defaultValue = "HELLO WORLD") String message) {

        kafkaUtils.sendMessage("amaris-training", message);

        return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);
    }

    @GetMapping("message")
    public List<String> receiveMessage() {
        List<String> messages = new ArrayList<>();
        ConsumerFactory<String, String> consumerFactory = (ConsumerFactory<String, String>) kafkaListenerContainerFactory.getConsumerFactory();
        Consumer<String, String> consumer = consumerFactory.createConsumer();
        try {
            consumer.subscribe(Arrays.asList("amaris-training"));
            ConsumerRecords<String, String> consumerRecords = consumer.poll(10000);
            Iterable<ConsumerRecord<String, String>> records = consumerRecords.records("amaris-training");
            Iterator<ConsumerRecord<String, String>> iterator = records.iterator();

            while (iterator.hasNext()) {
                messages.add(iterator.next().value());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
}
