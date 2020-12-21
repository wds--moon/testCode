package com.moon.kafka;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order  implements Serializable {

    private Long id;

    private Long orderId;

    private String productName;

    private Double amount;

    public Order(Long id, Long orderId, String productName, Double amount) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.amount = amount;
    }
}
