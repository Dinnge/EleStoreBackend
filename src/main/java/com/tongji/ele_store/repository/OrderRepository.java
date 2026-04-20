package com.tongji.ele_store.repository;

import com.tongji.ele_store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}