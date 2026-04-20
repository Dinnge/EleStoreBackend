package com.tongji.ele_store.entity;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // Getters and Setters
}
