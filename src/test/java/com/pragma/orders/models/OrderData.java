package com.pragma.orders.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderData(
    String orderId,
    String customerId,
    List<OrderItem> items,
    String status,
    Instant createdAt
) {
    public record OrderItem(
        String productId,
        Integer quantity,
        BigDecimal price
    ) {}
}