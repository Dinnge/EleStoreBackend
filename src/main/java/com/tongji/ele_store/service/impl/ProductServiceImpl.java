package com.tongji.ele_store.service.impl;

import com.tongji.ele_store.Mapper.ProductMapper;
import com.tongji.ele_store.entity.Product;
import com.tongji.ele_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product createProduct(Product product) {
        productMapper.insert(product);
        return product;
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectList(null);
    }

    @Override
    public Product updateProduct(Integer id, Product productDetails) {
        productDetails.setId(id);
        productMapper.updateById(productDetails);
        return productMapper.selectById(id);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return productMapper.deleteById(id) > 0;
    }
}