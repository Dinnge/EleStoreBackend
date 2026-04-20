package com.tongji.ele_store.service.impl;//package com.example.ele_store.service.impl;
//
//import com.example.ele_store.entity.Product;
//import com.example.ele_store.repository.ProductRepository;
//import com.example.ele_store.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductServiceImpl implements ProductService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Override
//    public Product createProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    @Override
//    public Product getProductById(Integer id) {
//        return productRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public Product updateProduct(Integer id, Product productDetails) {
//        return productRepository.findById(id).map(product -> {
//            product.setName(productDetails.getName());
//            product.setPrice(productDetails.getPrice());
//            // 更新其他字段
//            return productRepository.save(product);
//        }).orElse(null);
//    }
//
//    @Override
//    public boolean deleteProduct(Integer id) {
//        if (productRepository.existsById(id)) {
//            productRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//}

import com.tongji.ele_store.entity.Product;
import com.tongji.ele_store.repository.ProductRepository;
import com.tongji.ele_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Integer id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setCode(productDetails.getCode());
            product.setType(productDetails.getType());
            product.setBrand(productDetails.getBrand());
            product.setPic(productDetails.getPic());
            product.setNum(productDetails.getNum());
            product.setIntro(productDetails.getIntro());
            product.setStatus(productDetails.getStatus());
            product.setBigpic(productDetails.getBigpic());
            return productRepository.save(product);
        }).orElse(null);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}