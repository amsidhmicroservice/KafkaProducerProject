package com.amsidh.mvc.kafkaproducer.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    @Value("${topic.name.producer:test}")
    private String topicName;

    public String send(String message) throws ExecutionException, InterruptedException {
        log.info("Payload sent: {}", message);
        SendResult<Integer, String> sendResult = kafkaTemplate.send(topicName, message).get();
        return sendResult.getProducerRecord().value();
    }
}
