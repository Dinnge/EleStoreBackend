package com.tongji.ele_store.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
@Builder
public class User {
    @TableId(type=IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @TableField(insertStrategy = FieldStrategy.NEVER)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "ismember")
    private Boolean ismember;
}
