package org.mostafa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mostafa.model.OrderPlaceEventModel;
import org.mostafa.entity.Order;
import org.mostafa.excption.InventoryServiceUnavailableException;
import org.mostafa.model.OrderReqModel;
import org.mostafa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/11/2024 3:54 PM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;
    private final KafkaTemplate<String, OrderPlaceEventModel> kafkaTemplate;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Value("${cluster.topic.name}")
    private String topicName;

    @Override
    public void placeOrder(OrderReqModel orderRequest) {
        if (this.isInStock(orderRequest.skuCode(), orderRequest.quantity())) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
            order.setQuantity(orderRequest.quantity());
            Order placedOrder = orderRepository.save(order);

            if(null != placedOrder && placedOrder.getId() != 0L){
                OrderPlaceEventModel eventModel = new OrderPlaceEventModel("receiver@email.com", placedOrder.getOrderNumber());
                kafkaTemplate.send(topicName, eventModel);
            }
        } else {
            throw new RuntimeException("Inventory not in stock");
        }
    }

    public boolean isInStock(String skuCode, Integer quantity) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventoryServiceCircuitBreaker");
        String url = inventoryServiceUrl + "/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity;
        return circuitBreaker.run(() -> restTemplate.getForObject(url, Boolean.class),
                throwable -> {
                    throw new InventoryServiceUnavailableException("Inventory Service Unavailable. Please retry.");
                });
    }
}
