package com.amsidh.mvc.kafkaproducer.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Share implements Serializable {
    private Integer id;
    private String name;
    private Double price;
}
