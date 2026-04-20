package com.tongji.ele_store.service;

import com.tongji.ele_store.IResponse;
import com.tongji.ele_store.entity.Order;
import com.tongji.ele_store.entity.Role;
import com.tongji.ele_store.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void createUser(User user);
    Boolean checkPassword(String username, String password);
    String getUserRole(User user);
    void createRole(Role newRole);
    List<User> findAllUsers();
    void deleteUserById(Integer userId);

    void deleteUserByUsername(String username);

    User updateUser(Integer userId, User user, Integer roleId);

    //    void register(User user);
//    boolean validateUser(User user);
//    Boolean findByUserName2(String userName);
//    User register(User user);
//    User login(String userName, String password);
//    List<User> findAll();
//    User findById(Integer id);
//    void save(User user);
//    void update(User user);
//    void deleteById(Integer id);
//    List<Order> findOrdersByUserId(Integer userId); // 新增
//    User findByUserName(String userName);

}