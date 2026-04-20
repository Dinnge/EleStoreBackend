package com.tongji.ele_store.service;

import com.tongji.ele_store.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Integer id);
    List<Order> getAllOrders();
    Order updateOrder(Integer id, Order orderDetails);
    boolean deleteOrder(Integer id);
}
