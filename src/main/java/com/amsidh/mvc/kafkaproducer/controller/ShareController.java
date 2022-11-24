package com.amsidh.mvc.kafkaproducer.controller;

import com.amsidh.mvc.kafkaproducer.model.Share;
import com.amsidh.mvc.kafkaproducer.producer.TopicProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/kafka")
public class ShareController {

    private final TopicProducer topicProducer;
    private final ObjectMapper objectMapper;


    @PostMapping(value = "/share", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Share sendShareToKafka(@RequestBody Share share) throws ExecutionException, InterruptedException, IOException {
        log.info("Inside ShareController's sendShareToKafka method");
        String message = objectMapper.writeValueAsString(share);
        String kafkaResponse = topicProducer.send(message);
        return objectMapper.readValue(kafkaResponse, Share.class);
    }
}
