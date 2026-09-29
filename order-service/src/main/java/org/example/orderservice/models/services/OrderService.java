package org.example.orderservice.models.services;

import org.example.orderservice.clients.ProductClient;
import org.example.orderservice.models.dto.requests.CreateOrderRequest;
import org.example.orderservice.models.dto.responses.OrderResponse;
import org.example.orderservice.models.services.impl.OrderServiceImpl;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

}
