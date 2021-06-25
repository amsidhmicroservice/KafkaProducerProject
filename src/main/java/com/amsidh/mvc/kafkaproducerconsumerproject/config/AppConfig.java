package com.amsidh.mvc.kafkaproducerconsumerproject.config;

import com.amsidh.mvc.kafkaproducerconsumerproject.model.Share;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    //In oder to use kafka we have to beans ProducerFactory and KafkaTemplate

    @Bean
    public ProducerFactory<Integer, Share> getProducerFactory() {
        Map<String, Object> producerConfigMap = new HashMap<>();
        producerConfigMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        producerConfigMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerConfigMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(producerConfigMap);
    }

    @Bean
    public KafkaTemplate<Integer, Share> getKafkaTemplate() {
        return new KafkaTemplate<>(getProducerFactory());
    }
}
