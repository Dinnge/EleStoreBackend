package com.tongji.ele_store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tongji.ele_store.Mapper")
public class ElecShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElecShopApplication.class, args);
    }
}