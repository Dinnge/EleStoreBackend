package com.tongji.ele_store.service.impl;

import com.tongji.ele_store.Mapper.OrderMapper;
import com.tongji.ele_store.entity.Order;
import com.tongji.ele_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order createOrder(Order order) {
        orderMapper.insert(order);
        return order;
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectList(null);
    }

    @Override
    public Order updateOrder(Integer id, Order orderDetails) {
        orderDetails.setId(id);
        orderMapper.updateById(orderDetails);
        return orderMapper.selectById(id);
    }

    @Override
    public boolean deleteOrder(Integer id) {
        return orderMapper.deleteById(id) > 0;
    }
}
