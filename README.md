# 电商系统后端服务 (ele_store)

基于 Spring Boot + MyBatis Plus 构建的在线商城后端系统。

## 技术栈

- **Java**: 8
- **框架**: Spring Boot 2.7.6
- **ORM**: MyBatis Plus 3.5.1
- **数据库**: MySQL 8.0+
- **数据连接池**: Druid
- **API文档**: SpringDoc OpenAPI
- **密码加密**: BCrypt

## 功能模块

- ✅ 用户管理（注册、登录、查询、删除）
- ✅ 商品管理（CRUD 操作）
- ✅ 订单管理（创建、查询、更新、删除）
- ✅ 角色管理

## 项目结构

```
ele_store/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/tongji/ele_store/
│       │       ├── config/          # 配置类
│       │       │   ├── CorsConfig.java          # 跨域配置
│       │       │   ├── GlobalExceptionHandler.java  # 全局异常处理
│       │       │   ├── OpenApiConfig.java       # API文档配置
│       │       │   └── SecurityConfig.java      # 安全配置
│       │       ├── controller/     # 控制层
│       │       │   ├── OrderController.java
│       │       │   ├── ProductController.java
│       │       │   └── UserController.java
│       │       ├── entity/         # 实体类
│       │       │   ├── Order.java
│       │       │   ├── OrderDetail.java
│       │       │   ├── Product.java
│       │       │   ├── Role.java
│       │       │   ├── Type.java
│       │       │   └── User.java
│       │       ├── Mapper/         # MyBatis Mapper
│       │       │   ├── OrderDetailMapper.java
│       │       │   ├── OrderMapper.java
│       │       │   ├── ProductMapper.java
│       │       │   ├── RoleMapper.java
│       │       │   └── UserMapper.java
│       │       ├── service/        # 服务层
│       │       │   ├── impl/
│       │       │   ├── OrderService.java
│       │       │   ├── ProductService.java
│       │       │   └── UserService.java
│       │       ├── ElecShopApplication.java  # 启动类
│       │       └── IResponse.java             # 统一响应封装
│       └── resources/
│           ├── application.properties   # 应用配置
│           └── schema.sql              # 数据库初始化脚本
└── pom.xml  # Maven 依赖管理
```

## 快速开始

### 环境要求

- JDK 8+
- MySQL 8.0+
- Maven 3.6+

### 数据库配置

1. 创建数据库：

```sql
CREATE DATABASE ele_store DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 执行数据库初始化脚本：

```bash
mysql -u root -p ele_store < src/main/resources/schema.sql
```

### 运行项目

```bash
cd ele_store
mvn clean spring-boot:run
```

### 访问地址

- API 基础路径: `http://localhost:9001/api/`
- Swagger UI: `http://localhost:9001/swagger-ui.html`
- OpenAPI 文档: `http://localhost:9001/api-docs`

## API 接口

### 用户管理

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/users/register` | 用户注册 |
| POST | `/api/users/login` | 用户登录 |
| GET | `/api/users/all` | 获取所有用户 |
| DELETE | `/api/users/{username}` | 删除用户 |
| PUT | `/api/users/{userId}` | 更新用户信息 |

### 商品管理

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/products` | 创建商品 |
| GET | `/api/products` | 获取所有商品 |
| GET | `/api/products/{id}` | 获取商品详情 |
| PUT | `/api/products/{id}` | 更新商品 |
| DELETE | `/api/products/{id}` | 删除商品 |

### 订单管理

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/orders` | 创建订单 |
| GET | `/api/orders` | 获取所有订单 |
| GET | `/api/orders/{id}` | 获取订单详情 |
| PUT | `/api/orders/{id}` | 更新订单 |
| DELETE | `/api/orders/{id}` | 删除订单 |

## 安全说明

- ✅ 密码使用 BCrypt 加密存储
- ✅ 支持跨域配置
- ✅ 全局异常处理
- ✅ SQL 注入防护（MyBatis 参数化查询）

## License

MIT License