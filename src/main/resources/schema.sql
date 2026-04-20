CREATE DATABASE IF NOT EXISTS ele_store DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE ele_store;

CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码（BCrypt加密）',
    email VARCHAR(100) COMMENT '邮箱',
    ismember BOOLEAN DEFAULT FALSE COMMENT '是否会员',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

CREATE TABLE IF NOT EXISTS Role (
    roleid INT AUTO_INCREMENT PRIMARY KEY COMMENT '角色ID',
    name VARCHAR(50) NOT NULL COMMENT '角色名称',
    userid INT COMMENT '用户ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (userid) REFERENCES User(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

CREATE TABLE IF NOT EXISTS product_info (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '商品ID',
    code VARCHAR(50) NOT NULL UNIQUE COMMENT '商品编码',
    name VARCHAR(100) NOT NULL COMMENT '商品名称',
    tid INT COMMENT '商品类型ID',
    brand VARCHAR(50) COMMENT '品牌',
    pic VARCHAR(255) COMMENT '商品图片路径',
    num INT DEFAULT 0 COMMENT '库存数量',
    price DECIMAL(10,2) NOT NULL COMMENT '商品价格',
    intro TEXT COMMENT '商品介绍',
    status INT DEFAULT 1 COMMENT '商品状态（1-上架，0-下架）',
    bigpic VARCHAR(255) COMMENT '商品大图路径',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品信息表';

CREATE TABLE IF NOT EXISTS type_info (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '类型ID',
    name VARCHAR(50) NOT NULL COMMENT '类型名称',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品类型表';

CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
    user_id INT NOT NULL COMMENT '用户ID',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status INT DEFAULT 1 COMMENT '订单状态（1-待支付，2-已支付，3-已发货，4-已完成，5-已取消）',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

CREATE TABLE IF NOT EXISTS order_detail (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '订单详情ID',
    order_id INT NOT NULL COMMENT '订单ID',
    product_id INT NOT NULL COMMENT '商品ID',
    quantity INT NOT NULL DEFAULT 1 COMMENT '购买数量',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product_info(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单详情表';

INSERT INTO type_info (name) VALUES ('电子产品'), ('服装'), ('食品'), ('日用品');

INSERT INTO product_info (code, name, tid, brand, pic, num, price, intro, status) VALUES
('P001', 'iPhone 15 Pro', 1, 'Apple', 'iphone.jpg', 100, 8999.00, 'Apple iPhone 15 Pro 智能手机', 1),
('P002', 'MacBook Pro', 1, 'Apple', 'macbook.jpg', 50, 14999.00, 'Apple MacBook Pro 笔记本电脑', 1),
('P003', 'Nike Air Max', 2, 'Nike', 'nike.jpg', 200, 899.00, 'Nike Air Max 运动鞋', 1),
('P004', '可口可乐', 3, 'Coca-Cola', 'coke.jpg', 500, 3.50, '可口可乐 330ml', 1),
('P005', '毛巾套装', 4, '洁丽雅', 'towel.jpg', 300, 59.00, '纯棉毛巾套装 三条装', 1);