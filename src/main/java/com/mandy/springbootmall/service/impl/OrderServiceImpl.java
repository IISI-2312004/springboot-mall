package com.mandy.springbootmall.service.impl;

import com.mandy.springbootmall.dao.OrderDao;
import com.mandy.springbootmall.dao.ProductDao;
import com.mandy.springbootmall.dao.UserDao;
import com.mandy.springbootmall.dto.BuyItem;
import com.mandy.springbootmall.dto.CreateOrderRequest;
import com.mandy.springbootmall.dto.OrderQueryParam;
import com.mandy.springbootmall.model.Order;
import com.mandy.springbootmall.model.OrderItem;
import com.mandy.springbootmall.model.Product;
import com.mandy.springbootmall.model.User;
import com.mandy.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);
        if (user == null) {
            log.warn("userid{}不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<OrderItem> orderItemList = new ArrayList<>();
        int totalAmount = 0;
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            if (product == null) {
                log.warn("商品不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品{} 庫存數量不足，剩餘庫存{}，欲購買數量{}", buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
            } else {

//            扣除商品庫存
                productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());
//            計算總價錢
                int amount = buyItem.getQuantity() * product.getPrice();
                totalAmount = totalAmount + amount;

//            轉換BuyItem to OrderItem
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(buyItem.getProductId());
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setAmount(amount);
                orderItemList.add(orderItem);

            }
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);
        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }

    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    public List<Order> getOrders(OrderQueryParam orderQueryParam) {
        List<Order> orderList = orderDao.getOrders(orderQueryParam);
        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    public Integer countOrders(OrderQueryParam orderQueryParam) {
        return orderDao.countOrders(orderQueryParam);
    }
}
