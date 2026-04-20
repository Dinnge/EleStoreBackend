package com.tongji.ele_store.controller;

import com.tongji.ele_store.IResponse;
import com.tongji.ele_store.Mapper.UserMapper;
import com.tongji.ele_store.entity.Order;
import com.tongji.ele_store.entity.Role;
import com.tongji.ele_store.entity.User;
import com.tongji.ele_store.repository.UserRepository;
import com.tongji.ele_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/register")
    public IResponse register(String username, String password, String email, String name, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return IResponse.error("两次输入的密码不匹配");
        }
        // 判断用户名是否重复
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            return IResponse.error("用户名已存在");
        }

        User new_user = User.builder().username(username).password(password).email(email).ismember(true)
                .build();
        userService.createUser(new_user);

        // 获取新创建用户的ID
        Integer userId = new_user.getId();
//
//        // 创建新的UserRole实体
        Role newRole = Role.builder()
//                .roleid(1)
                .name(name)
                .userid(userId)
                .build();
        userService.createRole(newRole);

        return IResponse.ok("", "注册成功");
    }

    @CrossOrigin
    @PostMapping("/login")
    public IResponse login(String username, String password) {
        User user = userService.findByUsername(username);
        if (user == null)
            return IResponse.error("用户不存在");

        // 这里的 user != null 判断是多余的，因为如果 user 为 null，已经在前面的判断中返回了。
        if (user.getIsmember() != null && user.getIsmember().equals(true)) {
            Boolean ret = userService.checkPassword(username, password);
            if (ret != null && ret) {
                HashMap<String, String> hashMap = new HashMap<>();
                String role = userService.getUserRole(user);
                if(role != null) {
                    hashMap.put("role", role);
                }
                log.info("登录成功", username);
                return IResponse.ok(hashMap, "登录成功");
            } else {
                return IResponse.error("用户名或密码错误");
            }
        } else {
            return IResponse.error("用户未激活，无法登录");
        }
//        return IResponse.ok("用户未激活，无法登录");
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin
    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }

    @CrossOrigin
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody User user, String username) {
        // 检查用户是否存在
//        User existingUser = userService.findById(userId);
        User user2 = userService.findByUsername(username);
        if (user2 == null) {
            return ResponseEntity.notFound().build();
        }
        // 更新用户信息逻辑
//        userService.updateUser(userId, user);
        return ResponseEntity.ok("用户信息更新成功");
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User loginUser) {
//        User user = userRepository.findByUserName(loginUser.getUserName());
//        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
//            return ResponseEntity.ok("登录成功");
//        } else {
//            return ResponseEntity.badRequest().body("用户名或密码错误");
//        }
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User newUser) {
//        User existingUser = userRepository.findByUserName(newUser.getUserName());
//        if (existingUser == null) {
//            userRepository.save(newUser);
//            return ResponseEntity.ok("注册成功");
//        } else {
//            return ResponseEntity.badRequest().body("用户已存在");
//        }
//    }
//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestParam String userName, @RequestParam String password) {
//        User user = userService.login(userName, password);
//        System.out.println(user);
//        if (user != null) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }

//    @GetMapping("")
//    public String hello() {
//        return "Hello, Swagger!";
//    }

//    @GetMapping("/user")
//    public


//    @Autowired
//    private UserMapper userMapper;
//    @GetMapping( "/user")
//    public String query() {
//        List<User> list = userMapper.findAll();
//        System.out.println(list);
//        return "查询用户";
//    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.findAll();
//    }
//    UserService userService;
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Integer id) {
//        return userService.findById(id);
//    }
//
//    @GetMapping("/{id}/orders")
//    public List<Order> getOrdersByUserId(@PathVariable Integer id) {
//        return userService.findOrdersByUserId(id);
//    }
//
//    @PostMapping(consumes = "multipart/form-data")
//    public void createUser(@RequestBody User user) {
//        userService.save(user);
//    }
//
//    @PutMapping("/{id}")
//    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
//        user.setId(id);
//        userService.update(user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Integer id) {
//        userService.deleteById(id);
//    }
}