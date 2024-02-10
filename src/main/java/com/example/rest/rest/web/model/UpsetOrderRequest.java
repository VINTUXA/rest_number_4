package com.example.rest.rest.web.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpsetOrderRequest {
    @NotNull(message = "Client ID should be specified")
    @Positive(message = "Client ID should be more than zero")
    private Long clientId;
    private String product;
    private BigDecimal cost;
}
