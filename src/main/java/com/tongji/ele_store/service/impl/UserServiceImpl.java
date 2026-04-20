package com.tongji.ele_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ele_store.Mapper.RoleMapper;
import com.tongji.ele_store.Mapper.UserMapper;
import com.tongji.ele_store.entity.Role;
import com.tongji.ele_store.entity.User;
import com.tongji.ele_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        if (userMapper == null) {
            throw new IllegalStateException("userMapper is not injected: check your configuration.");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public Boolean checkPassword(String username, String password) {
        User user = findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public String getUserRole(User user) {
        Integer userId = user.getId(); // 确保这里的 getter 方法与您的 User 实体类中的方法匹配

        // 构建查询用户角色的 QueryWrapper
        QueryWrapper<Role> userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("userId", userId);
        List<Role> userRoles = roleMapper.selectList(userRoleWrapper);

        if (!userRoles.isEmpty()) {
            // 假定每个用户只有一个角色
            Integer roleId = userRoles.get(0).getRoleid();

            // 构建查询角色的 QueryWrapper
            QueryWrapper<Role> roleWrapper = new QueryWrapper<>();
            roleWrapper.eq("roleid", roleId);
            Role role = roleMapper.selectOne(roleWrapper);

            if (role != null) {
                return role.getName(); // 确保这里的 getter 方法与您的 Role 实体类中的方法匹配
            }
        }

        return null; // 返回一个默认消息或null，根据您的需求
    }
    @Override
    public List<User> findAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public void createRole(Role Role) {
        roleMapper.insert(Role);
    }

    @Override
    public void deleteUserById(Integer userId) {
        // 有外键都要删
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getUserid, userId);
        roleMapper.delete(queryWrapper);
        userMapper.deleteById(userId);
    }
    @Override
    public void deleteUserByUsername(String username) {
        // 首先，根据用户名查找用户
        User user = findByUsername(username); // 或者使用UserMapper的findByUsername方法
        if (user != null) {
            // 如果找到用户，先删除用户相关的角色或其他外键约束
            LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Role::getUserid, user.getId()); // 注意属性名称可能需要调整以匹配你的实体类
            roleMapper.delete(queryWrapper); // 或者使用RoleMapper的delete方法
            // 最后，删除用户
            userMapper.deleteById(user.getId()); // 或者使用UserMapper的deleteById方法
//            return IResponse.ok("", "用户删除成功");
        }
//        else{
////            return IResponse.error("此用户不存在");
//        }
    }

    @Override
    public User updateUser(Integer id, User user, Integer roleId) {
        user.setId(id);
        userMapper.updateById(user);

        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getUserid, id);
        Role userRole = roleMapper.selectOne(queryWrapper);
        userRole.setRoleid(roleId);
        roleMapper.update(userRole, queryWrapper);

        return user;
    }



    //
//    @Override
//    public User register(User user) {
//        return userRepository.save(user);
//    }

//    @Override
////    public User findByUserName(String userName) {
////        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
////        queryWrapper.eq("userName", userName);
////        User user = userMapper.selectOne(queryWrapper);
////
////        if(user == null) {
////            System.out.println("未找到用户名为 " + userName + " 的用户");
////        } else {
////            System.out.println("找到用户名为 " + userName + " 的用户");
////        }
////
////        return user;
//////        return userMapper.selectOne(queryWrapper);
////    }
//    public User findByUserName(String userName) {
//        if (userName == null) {
//            // userName 为 null，可直接返回 null 或抛出一个自定义的异常，取决于你的业务需求
//            return null; // 或者 throw new IllegalArgumentException("userName cannot be null");
//        }
//
//        // 假设 userRepository 是进行数据库操作的对象
//        User user = userRepository.findByUserName(userName);
//
//        if (user == null) {
//            // 根据用户名未找到用户，根据你的业务逻辑处理，例如返回 null 或者抛出一个自定义的异常
//            return null; // 或者 throw new UserNotFoundException("User not found with userName: " + userName);
//        }
//
//        return user;
//    }
//    @Override
//    public Boolean findByUserName2(String userName) {
//        if (userMapper == null) {
//            // 添加日志输出，查看是否为 null
//            System.out.println("userMapper is null!");
//            return null;
//        }
//        else
//            System.out.println("userMapper is not null!");
//        if (userName == null) {
//            // userName 为 null，可直接返回 null 或抛出一个自定义的异常，取决于你的业务需求
//            return null; // 或者 throw new IllegalArgumentException("userName cannot be null");
//        }
//
//        // 假设 userRepository 是进行数据库操作的对象
//        User user = findByUserName(userName);
//
//        if (user == null) {
//            // 根据用户名未找到用户，根据你的业务逻辑处理，例如返回 null 或者抛出一个自定义的异常
//            return false; // 或者 throw new UserNotFoundException("User not found with userName: " + userName);
//        }
//
//        return true;
//    }
//    @Override
//    public User register(User user) {
////        userMapper.insert(user);
//        return userRepository.save(user);
//    }
//    @Override
//    public User login(String userName, String password) {
//        User user = findByUsername(userName);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//        return null;
//    }


//    @Override
//    public boolean validateUser(User user) {
//        User existingUser = userMapper.findById(user.getId());
//        return existingUser != null && existingUser.getPassword().equals(user.getPassword());
//    }
//    @Override
//    public List<User> findAll() {
//        return userMapper.findAll();
//    }
//
//    @Override
//    public User findById(Integer id) {
//        return userMapper.findById(id);
//    }

//    @Override
//    public void save(User user) {
//        userMapper.insert(user);
//    }
//
//    @Override
//    public void update(User user) {
//        userMapper.updateById(user);
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        userMapper.deleteById(id);
//    }

//    @Override
//    public List<Order> findOrdersByUserId(Integer userId) {
//        return userMapper.findOrdersByUserId(userId);
//    }

}