package com.pragma.orders.interactions;

import com.pragma.orders.models.OrderData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class DynamoDBClient implements Interaction {

    private final String tableName;
    private final DynamoDbClient dynamoDbClient;

    public DynamoDBClient(String tableName) {
        this.tableName = tableName;
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        throw new UnsupportedOperationException("TODO(reto): implementa");
    }

    public void saveOrder(OrderData order) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(order.orderId()).build());
        item.put("customerId", AttributeValue.builder().s(order.customerId()).build());
        item.put("status", AttributeValue.builder().s(order.status()).build());
        item.put("createdAt", AttributeValue.builder().s(order.createdAt().toString()).build());

        List<Map<String, AttributeValue>> itemsList = new ArrayList<>();
        for (OrderData.OrderItem orderItem : order.items()) {
            Map<String, AttributeValue> itemMap = new HashMap<>();
            itemMap.put("productId", AttributeValue.builder().s(orderItem.productId()).build());
            itemMap.put("quantity", AttributeValue.builder().n(orderItem.quantity().toString()).build());
            itemMap.put("price", AttributeValue.builder().n(orderItem.price().toString()).build());
            itemsList.add(itemMap);
        }

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public OrderData getOrder(String orderId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        Map<String, AttributeValue> result = dynamoDbClient.getItem(request).item();

        if (result == null || result.isEmpty()) {
            return null;
        }

        String customerId = result.get("customerId").s();
        String status = result.get("status").s();
        Instant createdAt = Instant.parse(result.get("createdAt").s());

        List<OrderData.OrderItem> items = new ArrayList<>();
        if (result.containsKey("items")) {
            List<AttributeValue> itemsAttr = result.get("items").l();
            for (AttributeValue itemAttr : itemsAttr) {
                Map<String, AttributeValue> itemMap = itemAttr.m();
                String productId = itemMap.get("productId").s();
                Integer quantity = Integer.parseInt(itemMap.get("quantity").n());
                BigDecimal price = new BigDecimal(itemMap.get("price").n());
                items.add(new OrderData.OrderItem(productId, quantity, price));
            }
        }

        return new OrderData(orderId, customerId, items, status, createdAt);
    }

    public void deleteOrder(String orderId) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        dynamoDbClient.deleteItem(request);
    }

    public boolean tableExists() {
        try {
            DescribeTableRequest request = DescribeTableRequest.builder()
                    .tableName(tableName)
                    .build();
            dynamoDbClient.describeTable(request);
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    public void createTableIfNotExists() {
        if (!tableExists()) {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName(tableName)
                    .keySchema(KeySchemaElement.builder()
                            .attributeName("orderId")
                            .keyType(KeyType.HASH)
                            .build())
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName("orderId")
                            .attributeType(ScalarAttributeType.S)
                            .build())
                    .billingMode(BillingMode.PAY_PER_REQUEST)
                    .build();

            dynamoDbClient.createTable(request);

            waitForTableToBeActive();
        }
    }

    private void waitForTableToBeActive() {
        int maxRetries = 30;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                DescribeTableRequest request = DescribeTableRequest.builder()
                        .tableName(tableName)
                        .build();
                String status = dynamoDbClient.describeTable(request).table().tableStatusAsString();

                if ("ACTIVE".equals(status)) {
                    return;
                }

                Thread.sleep(1000);
                retryCount++;
            } catch (Exception e) {
                retryCount++;
                if (retryCount >= maxRetries) {
                    throw new RuntimeException("Table did not become active: " + tableName, e);
                }
            }
        }
    }

    public void cleanupTable() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();

        ScanResponse response = dynamoDbClient.scan(scanRequest);

        for (Map<String, AttributeValue> item : response.items()) {
            String orderId = item.get("orderId").s();
            deleteOrder(orderId);
        }
    }
}