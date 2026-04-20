package com.tongji.ele_store.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongji.ele_store.entity.User;
import com.tongji.ele_store.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
//
//    @Select("SELECT * FROM user_info WHERE id = #{id}")
//    User findById(@Param("id") Integer id);
//
//    @Select("SELECT * FROM user_info")
//    List<User> findAll();
//
//    @Insert("INSERT INTO user(userName, password, realName, sex, address, question, answer, email, favorate, score, regDate) VALUES(#{userName}, #{password}, #{realName}, #{sex}, #{address}, #{question}, #{answer}, #{email}, #{favorate}, #{score}, #{regDate})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int insert(User user);
//
//    @Update("UPDATE user SET userName=#{userName}, password=#{password}, realName=#{realName}, sex=#{sex}, address=#{address}, question=#{question}, answer=#{answer}, email=#{email}, favorate=#{favorate}, score=#{score}, regDate=#{regDate} WHERE id = #{id}")
//    void update(User user);
//
//    @Delete("DELETE FROM user WHERE id = #{id}")
//    void deleteById(@Param("id") Integer id);
//
//    @Select("SELECT o.* FROM orders o WHERE o.userId = #{userId}")
//    List<Order> findOrdersByUserId(Integer userId);
}