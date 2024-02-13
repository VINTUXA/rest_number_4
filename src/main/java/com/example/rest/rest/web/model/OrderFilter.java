package com.example.rest.rest.web.model;

import com.example.rest.rest.validation.OrderFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@OrderFilterValid
public class OrderFilter {
    private String productName;
    private Integer pageNumber;
    private Integer pageSize;
    private BigDecimal minCost;
    private BigDecimal maxCost;
    private Instant createdBefore;
    private Instant updatedBefore;
    private Long clientId;
}
