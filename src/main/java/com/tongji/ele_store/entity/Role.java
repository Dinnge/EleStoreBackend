package com.tongji.ele_store.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Role")
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Integer roleid;
    @Column(name = "name")
    private String name;
    // 添加用户ID
    @Column(name = "userid")
    private Integer userid;
}