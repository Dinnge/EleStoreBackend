package com.tongji.ele_store.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongji.ele_store.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(@Param("id") Integer id);

    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Insert("INSERT INTO product(name, description, price, stock) VALUES(#{name}, #{description}, #{price}, #{stock})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Update("UPDATE product SET name=#{name}, description=#{description}, price=#{price}, stock=#{stock} WHERE id = #{id}")
    void update(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
}