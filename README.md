# Cloud Knowledge Checkout

### 简介
一个在线知识总结网站，可用于错题整理，难点总结。
### 技术栈
+ 前台：Extjs 6
+ 后台：JDK8, Spring boot, Spring Data, Spring Security, JJWT, Lombok
+ 数据库：Mysql
+ CI: git, maven, jenkins, nginx, docker

### HOW-TO RUN
+ 导入checkout文件夹下面的checkout-db.sql初始化数据库 
+ 修改checkout/checkout-web-app/src/main/resources/application.yml中的数据库配置
+ 配置sencha cmd
+ 在checkout件文件夹下执行
 ```sh
       mvn clean install
```
+ 将生成的war包部署到J2EE容器

### License
---
MIT
