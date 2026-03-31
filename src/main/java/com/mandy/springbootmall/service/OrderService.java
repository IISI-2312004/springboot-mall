package com.mandy.springbootmall.service;

import com.mandy.springbootmall.dto.CreateOrderRequest;
import com.mandy.springbootmall.dto.OrderQueryParam;
import com.mandy.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParam orderQueryParam);

    Integer countOrders(OrderQueryParam orderQueryParam);
}
