package com.pragma.orders.tasks;

import com.pragma.orders.interactions.DynamoDBClient;
import com.pragma.orders.models.OrderData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderTask implements Task {

    private final String orderId;
    private final String customerId;
    private final List<OrderData.OrderItem> items;
    private final String status;
    private final Instant createdAt;
    private final String tableName;

    public CreateOrderTask(String orderId, String customerId, List<OrderData.OrderItem> items, 
                           String status, Instant createdAt, String tableName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
        this.tableName = tableName;
    }

    public static CreateOrderBuilder withOrderId(String orderId) {
        return new CreateOrderBuilder(orderId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DynamoDBClient dynamoDBClient = new DynamoDBClient(tableName);
        
        OrderData orderData = new OrderData(orderId, customerId, items, status, createdAt);
        dynamoDBClient.saveOrder(orderData);
    }

    public static class CreateOrderBuilder {
        private final String orderId;
        private String customerId;
        private List<OrderData.OrderItem> items = new ArrayList<>();
        private String status = "PENDING";
        private Instant createdAt = Instant.now();
        private String tableName = "orders";

        public CreateOrderBuilder(String orderId) {
            this.orderId = orderId;
        }

        public CreateOrderBuilder forCustomer(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public CreateOrderBuilder withItems(List<OrderData.OrderItem> items) {
            this.items = items;
            return this;
        }

        public CreateOrderBuilder withItem(String productId, int quantity, BigDecimal price) {
            this.items.add(new OrderData.OrderItem(productId, quantity, price));
            return this;
        }

        public CreateOrderBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public CreateOrderBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CreateOrderBuilder inTable(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public CreateOrderTask build() {
            return new CreateOrderTask(orderId, customerId, items, status, createdAt, tableName);
        }
    }
}