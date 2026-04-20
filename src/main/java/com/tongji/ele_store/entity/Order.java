package com.tongji.ele_store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_info")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "ordertime", nullable = false)
    private LocalDateTime ordertime;

    @Column(name = "orderprice", nullable = false)
    private BigDecimal orderprice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(LocalDateTime ordertime) {
        this.ordertime = ordertime;
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}