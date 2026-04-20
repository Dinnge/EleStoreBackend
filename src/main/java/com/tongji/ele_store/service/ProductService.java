package com.tongji.ele_store.service;

import com.tongji.ele_store.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Integer id);
    List<Product> getAllProducts();
    Product updateProduct(Integer id, Product productDetails);
    boolean deleteProduct(Integer id);
}
