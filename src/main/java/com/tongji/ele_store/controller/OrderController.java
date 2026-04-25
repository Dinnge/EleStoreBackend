package com.tongji.ele_store.controller;

import com.tongji.ele_store.entity.Order;
import com.tongji.ele_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "订单管理", description = "订单增删改查等接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @Operation(summary = "创建订单", description = "创建新的订单信息")
    public ResponseEntity<Order> createOrder(
            @Parameter(description = "订单信息") @RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取订单", description = "根据ID获取订单详细信息")
    public ResponseEntity<Order> getOrderById(
            @Parameter(description = "订单ID") @PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "获取所有订单", description = "查询系统中所有订单信息")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新订单", description = "根据ID更新订单信息")
    public ResponseEntity<Order> updateOrder(
            @Parameter(description = "订单ID") @PathVariable Integer id,
            @Parameter(description = "订单信息") @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(id, orderDetails);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除订单", description = "根据ID删除订单")
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "订单ID") @PathVariable Integer id) {
        if (orderService.deleteOrder(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}