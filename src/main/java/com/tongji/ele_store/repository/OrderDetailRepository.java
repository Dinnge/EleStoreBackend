package com.tongji.ele_store.repository;

import com.tongji.ele_store.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}