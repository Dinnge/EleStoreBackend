package com.tongji.ele_store.service.impl;

import com.tongji.ele_store.Mapper.OrderDetailMapper;
import com.tongji.ele_store.Mapper.OrderMapper;
import com.tongji.ele_store.Mapper.ProductMapper;
import com.tongji.ele_store.entity.Order;
import com.tongji.ele_store.entity.OrderDetail;
import com.tongji.ele_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
