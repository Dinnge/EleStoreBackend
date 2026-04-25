-- 电子商城数据库初始化脚本
-- 设计理念：高内聚、复杂关联、支持RBAC权限体系

CREATE DATABASE IF NOT EXISTS ele_store DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE ele_store;

-- 用户表
CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码（BCrypt加密）',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    ismember BOOLEAN DEFAULT FALSE COMMENT '是否会员',
    status INT DEFAULT 1 COMMENT '用户状态（1-正常，0-禁用）',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_username (username),  -- 用户名索引
    INDEX idx_email (email)          -- 邮箱索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 角色表
CREATE TABLE IF NOT EXISTS Role (
    roleid INT AUTO_INCREMENT PRIMARY KEY COMMENT '角色ID',
    name VARCHAR(50) NOT NULL UNIQUE COMMENT '角色名称（admin-管理员，user-普通用户，vip-VIP用户）',
    description VARCHAR(255) COMMENT '角色描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_name (name)  -- 角色名索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- 用户角色关联表（多对多）
CREATE TABLE IF NOT EXISTS user_role (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    user_id INT NOT NULL COMMENT '用户ID',
    role_id INT NOT NULL COMMENT '角色ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Role(roleid) ON DELETE CASCADE,
    UNIQUE KEY uk_user_role (user_id, role_id),  -- 唯一约束，避免重复关联
    INDEX idx_user_id (user_id),   -- 用户ID索引
    INDEX idx_role_id (role_id)    -- 角色ID索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

-- 权限表
CREATE TABLE IF NOT EXISTS Permission (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID',
    name VARCHAR(100) NOT NULL UNIQUE COMMENT '权限名称（格式：模块:操作，如user:create）',
    description VARCHAR(255) COMMENT '权限描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_name (name)  -- 权限名索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

-- 角色权限关联表（多对多）
CREATE TABLE IF NOT EXISTS role_permission (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    role_id INT NOT NULL COMMENT '角色ID',
    permission_id INT NOT NULL COMMENT '权限ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (role_id) REFERENCES Role(roleid) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES Permission(id) ON DELETE CASCADE,
    UNIQUE KEY uk_role_permission (role_id, permission_id),  -- 唯一约束
    INDEX idx_role_id (role_id),       -- 角色ID索引
    INDEX idx_permission_id (permission_id)  -- 权限ID索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限关联表';

-- 商品类型表
CREATE TABLE IF NOT EXISTS type_info (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '类型ID',
    name VARCHAR(50) NOT NULL COMMENT '类型名称',
    description VARCHAR(255) COMMENT '类型描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_name (name)  -- 类型名索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品类型表';

-- 商品信息表
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
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (tid) REFERENCES type_info(id) ON DELETE SET NULL,
    INDEX idx_code (code),        -- 商品编码索引
    INDEX idx_name (name),        -- 商品名称索引（全文搜索）
    INDEX idx_tid (tid),          -- 类型ID索引
    INDEX idx_status (status),    -- 状态索引
    INDEX idx_brand (brand)       -- 品牌索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品信息表';

-- 标签表
CREATE TABLE IF NOT EXISTS tag (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '标签ID',
    name VARCHAR(50) NOT NULL UNIQUE COMMENT '标签名称',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_name (name)  -- 标签名索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- 商品标签关联表（多对多）
CREATE TABLE IF NOT EXISTS product_tag (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    product_id INT NOT NULL COMMENT '商品ID',
    tag_id INT NOT NULL COMMENT '标签ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (product_id) REFERENCES product_info(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE,
    UNIQUE KEY uk_product_tag (product_id, tag_id),  -- 唯一约束
    INDEX idx_product_id (product_id),  -- 商品ID索引
    INDEX idx_tag_id (tag_id)           -- 标签ID索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品标签关联表';

-- 订单表
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
    user_id INT NOT NULL COMMENT '用户ID',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status INT DEFAULT 1 COMMENT '订单状态（1-待支付，2-已支付，3-已发货，4-已完成，5-已取消）',
    address VARCHAR(255) COMMENT '收货地址',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE RESTRICT,
    INDEX idx_order_no (order_no),  -- 订单号索引
    INDEX idx_user_id (user_id),    -- 用户ID索引
    INDEX idx_status (status),      -- 状态索引
    INDEX idx_create_time (create_time)  -- 创建时间索引（用于排序）
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- 订单详情表
CREATE TABLE IF NOT EXISTS order_detail (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '订单详情ID',
    order_id INT NOT NULL COMMENT '订单ID',
    product_id INT NOT NULL COMMENT '商品ID',
    quantity INT NOT NULL DEFAULT 1 COMMENT '购买数量',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价（下单时的价格快照）',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product_info(id) ON DELETE RESTRICT,
    INDEX idx_order_id (order_id),   -- 订单ID索引
    INDEX idx_product_id (product_id)  -- 商品ID索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单详情表';

-- ==================== 初始化数据 ====================

-- 插入角色数据
INSERT INTO Role (name, description) VALUES
('admin', '管理员，拥有所有权限'),
('user', '普通用户，基本操作权限'),
('vip', 'VIP用户，享有优惠权限');

-- 插入权限数据
INSERT INTO Permission (name, description) VALUES
('user:create', '创建用户'),
('user:read', '查看用户'),
('user:update', '更新用户'),
('user:delete', '删除用户'),
('product:create', '创建商品'),
('product:read', '查看商品'),
('product:update', '更新商品'),
('product:delete', '删除商品'),
('order:create', '创建订单'),
('order:read', '查看订单'),
('order:update', '更新订单'),
('order:delete', '删除订单'),
('order:process', '处理订单（发货等）');

-- 管理员角色拥有所有权限
INSERT INTO role_permission (role_id, permission_id)
SELECT r.roleid, p.id FROM Role r, Permission p WHERE r.name = 'admin';

-- 普通用户角色拥有关于用户、商品、订单的基本权限
INSERT INTO role_permission (role_id, permission_id)
SELECT r.roleid, p.id FROM Role r, Permission p 
WHERE r.name = 'user' 
AND p.name IN ('user:create', 'user:read', 'user:update', 
               'product:read', 
               'order:create', 'order:read', 'order:update');

-- VIP用户角色拥有多一些的权限
INSERT INTO role_permission (role_id, permission_id)
SELECT r.roleid, p.id FROM Role r, Permission p 
WHERE r.name = 'vip' 
AND p.name IN ('user:create', 'user:read', 'user:update', 
               'product:read', 
               'order:create', 'order:read', 'order:update', 'order:delete');

-- 插入商品类型数据
INSERT INTO type_info (name, description) VALUES 
('电子产品', '手机、电脑、数码等电子产品'),
('服装', '男装、女装、童装等'),
('食品', '零食、饮料、生鲜等'),
('日用品', '家居、厨卫、清洁等');

-- 插入商品数据
INSERT INTO product_info (code, name, tid, brand, pic, num, price, intro, status) VALUES
('P001', 'iPhone 15 Pro', 1, 'Apple', 'iphone.jpg', 100, 8999.00, 'Apple iPhone 15 Pro 智能手机', 1),
('P002', 'MacBook Pro', 1, 'Apple', 'macbook.jpg', 50, 14999.00, 'Apple MacBook Pro 笔记本电脑', 1),
('P003', 'Nike Air Max', 2, 'Nike', 'nike.jpg', 200, 899.00, 'Nike Air Max 运动鞋', 1),
('P004', '可口可乐', 3, 'Coca-Cola', 'coke.jpg', 500, 3.50, '可口可乐 330ml', 1),
('P005', '毛巾套装', 4, '洁丽雅', 'towel.jpg', 300, 59.00, '纯棉毛巾套装 三条装', 1);

-- 插入标签数据
INSERT INTO tag (name) VALUES 
('热门'), ('新品'), ('促销'), ('爆款'), ('进口'), ('有机');

-- 为商品关联标签（多对多关系）
INSERT INTO product_tag (product_id, tag_id) VALUES
(1, 1), (1, 2),  -- iPhone是热门和新品
(2, 1),          -- MacBook是热门
(3, 3), (3, 4),  -- Nike是促销和爆款
(4, 5),          -- 可口可乐是进口
(5, 6);          -- 毛巾是有机

-- 插入测试用户数据（密码都是 123456，BCrypt加密后不同）
INSERT INTO User (username, password, email, ismember, status) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'admin@example.com', TRUE, 1),
('user1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'user1@example.com', FALSE, 1),
('vip1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'vip1@example.com', TRUE, 1);

-- 为用户关联角色
INSERT INTO user_role (user_id, role_id) VALUES
(1, 1),  -- admin用户 -> admin角色
(2, 2),  -- user1用户 -> user角色
(3, 3);  -- vip1用户 -> vip角色
