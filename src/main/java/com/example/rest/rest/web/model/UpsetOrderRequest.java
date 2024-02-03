package com.example.rest.rest.web.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpsetOrderRequest {
    private Long clientId;
    private String product;
    private BigDecimal cost;
}
