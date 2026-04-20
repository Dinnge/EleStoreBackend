package com.tongji.ele_store.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongji.ele_store.entity.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    @Select("SELECT * FROM order_detail WHERE id = #{id}")
    OrderDetail findById(@Param("id") Integer id);

    @Select("SELECT * FROM order_detail")
    List<OrderDetail> findAll();

    @Insert("INSERT INTO order_detail(orderId, productId, quantity, price) VALUES(#{orderId}, #{productId}, #{quantity}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OrderDetail orderDetail);

    @Update("UPDATE order_detail SET orderId=#{orderId}, productId=#{productId}, quantity=#{quantity}, price=#{price} WHERE id = #{id}")
    void update(OrderDetail orderDetail);

    @Delete("DELETE FROM order_detail WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
}