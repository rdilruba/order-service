package com.customer.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("customer_number")
    private String customerNumber;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

}
