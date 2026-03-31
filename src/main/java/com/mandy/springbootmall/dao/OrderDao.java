package com.mandy.springbootmall.dao;

import com.mandy.springbootmall.dto.OrderQueryParam;
import com.mandy.springbootmall.model.Order;
import com.mandy.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    List<Order> getOrders(OrderQueryParam orderQueryParam);

    Integer countOrders(OrderQueryParam orderQueryParam);
}
