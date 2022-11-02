package com.trainingsv2.web.rest;

import com.trainingsv2.common.exception.impl.NotFoundException;
import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.domain.Cart;
import com.trainingsv2.kafka.KafkaProducer;
import com.trainingsv2.repository.CartRepository;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    private final KafkaProducer kafkaProducer;
    private final CartRepository cartRepository;

    public KafkaController(KafkaProducer kafkaProducer, CartRepository cartRepository) {
        this.kafkaProducer = kafkaProducer;
        this.cartRepository = cartRepository;
    }


    @PostMapping("publish")
    public ResponseEntity<String> publishMessage(@RequestParam(required = false, defaultValue = "HELLO WORLD") String message) {

        kafkaProducer.sendMessage(GlobalConstants.KAFKA_BASE_TOPIC, message);

        return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);
    }

    @PostMapping("publishCart")
    public ResponseEntity<String> sendCart(@RequestParam(required = false, defaultValue = "1") Integer cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND)
        );

        kafkaProducer.sendMessage(GlobalConstants.KAFKA_BASE_TOPIC, cart.getItemSales().get(0));

        return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);
    }
}
