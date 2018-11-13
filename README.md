Cake_Shop
================

#### 简介：
Cake_Shop是一个基于Springboot的糕点类主题电商项目。为什么要做这个项目，其实是因为某天晚上在饿了么看见可口的蛋糕，一时饥饿难耐于是想写个网页的..(其实主要是想练练手)。

#### 技术栈：
Springboot、Mybatis、thymeleaf、spring-security、Mysql、druid、Bootstrap、NotfyJs、AmazeUI、ECharts..
此外还有用到一些小工具:tk.mybatis、lombok、IdGenrator...

#### 一些问题
Cake_Shop的前台部分鱼龙混杂，基本是哪个好看就用哪个，没有工程性。后台倒是用了Amaze，比较简洁也好看
—————————————————————

**近期更新（2018.11.13）：**

2018.11.13
1. 增加后台用户、商品管理


**计划中：**

1. 用Angular重构前端...
             
### 一、外观

#### 首页

![iOgZVS.png](https://s1.ax1x.com/2018/11/13/iOgZVS.png)

#### 全部商品

![iOgADf.png](https://s1.ax1x.com/2018/11/13/iOgADf.png)

#### 我的订单

 ![iOgivt.png](https://s1.ax1x.com/2018/11/13/iOgivt.png)                 

#### 后台订单管理
![iOgEb8.png](https://s1.ax1x.com/2018/11/13/iOgEb8.png)


#### 后台订单管理

![iOgkKP.png](https://s1.ax1x.com/2018/11/13/iOgkKP.png)  


### 二、使用

#### 安装

``` bash
$ git clone https://github.com/Wushiyii/cake_shop.git
```

#### 配置

执行DB文件夹下的sql，数据库的用户密码配置在application.yml中，项目中使用的是druid连接池。

#### 使用

``` bash
	mvn spring-boot:run
```
* 项目访问地址：http://127.0.0.1:8080/cake/ 
* 后台访问地址: http://127.0.0.1:8080/manage/  (需要将数据库中user_group 改为'ROLE_ADMIN')