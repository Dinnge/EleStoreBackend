package com.tongji.ele_store.repository;

import com.tongji.ele_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByUserName(String userName);
      User findByUsername(String username);
}
