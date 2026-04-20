package com.tongji.ele_store.repository;

import com.tongji.ele_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}