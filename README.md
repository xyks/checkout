# Cloud Knowledge Checkout

### 简介
一个在线知识总结网站，可用于错题整理，难点总结。

### 基本功能（已完成）
+ 用户登录
+ 知识点高级查询
+ 新增、修改知识点（基于权限）
+ Review功能以及记录查看和数据统计
+ 知识点关注列表

### TODO
+ 用户管理
+ 知识点类别管理
+ 评分功能（打分和评论）

### 技术栈
+ 前台：Ext JS 6
+ 后台：Java 8、Spring Boot、Spring Data、Spring Security、JJWT、Lombok
+ 数据库：MySQL
+ CI: git、maven、jenkins、nginx、docker、tomcat

### HOW-TO RUN
+ 导入checkout文件夹下面的checkout-db.sql初始化数据库 
+ 修改checkout/checkout-web-app/src/main/resources/application.yml中的数据库配置
+ 在checkout件文件夹下执行
 ```sh
       mvn clean install
```
+ 将生成的war包部署到J2EE容器

### License
---
MIT
