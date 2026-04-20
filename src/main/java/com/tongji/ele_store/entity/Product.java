package com.tongji.ele_store.entity;
import javax.persistence.*;
//import java.lang.reflect.Type;
import java.math.*;

@Entity
@Table(name = "product_info")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "tid")
    private Type type;

    @Column(name = "brand")
    private String brand;

    @Column(name = "pic")
    private String pic;

    @Column(name = "num")
    private Integer num;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "intro")
    private String intro;

    @Column(name = "status")
    private Integer status;

    @Column(name = "bigpic")
    private String bigpic;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBigpic() {
        return bigpic;
    }

    public void setBigpic(String bigpic) {
        this.bigpic = bigpic;
    }
}