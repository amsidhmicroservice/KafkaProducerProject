package com.amsidh.mvc.kafkaproducerconsumerproject.controller;

import com.amsidh.mvc.kafkaproducerconsumerproject.model.Share;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/kafka")
public class ShareController {

    private final KafkaTemplate<Integer, Share> kafkaTemplate;

    @PostMapping(value = "/share", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Share sendShareToKafka(@RequestBody Share share) throws ExecutionException, InterruptedException {
        log.info("Inside ShareController's sendShareToKafka method");
        SendResult<Integer, Share> sendResult = kafkaTemplate.send("test", share).get();
        return sendResult.getProducerRecord().value();
    }
}
