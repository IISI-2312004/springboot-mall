package com.mandy.springbootmall.service;

import com.mandy.springbootmall.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
