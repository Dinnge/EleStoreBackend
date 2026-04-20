package com.tongji.ele_store.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongji.ele_store.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order findById(@Param("id") Integer id);

    @Select("SELECT * FROM orders")
    List<Order> findAll();

    @Insert("INSERT INTO orders(userId, totalPrice, orderDate) VALUES(#{userId}, #{totalPrice}, #{orderDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Order order);

    @Update("UPDATE orders SET userId=#{userId}, totalPrice=#{totalPrice}, orderDate=#{orderDate} WHERE id = #{id}")
    void update(Order order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
}