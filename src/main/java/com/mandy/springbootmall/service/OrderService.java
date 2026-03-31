package com.mandy.springbootmall.service;

import com.mandy.springbootmall.dto.CreateOrderRequest;
import com.mandy.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer OrderId);
}
