package com.tongji.ele_store.service.impl;

import com.tongji.ele_store.entity.Order;
import com.tongji.ele_store.repository.OrderRepository;
import com.tongji.ele_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Integer id, Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(orderDetails.getStatus());
            order.setOrdertime(orderDetails.getOrdertime());
            order.setOrderprice(orderDetails.getOrderprice());
            // 更新其他字段
            return orderRepository.save(order);
        }).orElse(null);
    }

    @Override
    public boolean deleteOrder(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
