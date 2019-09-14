# PangolinCarryCode

## 项目介绍
* ### ☘️介绍
     * 基于FreeMarker，根据MySQL 数据库一键生成Model、Service、Impl、Controller等代码
        
* ### ☘️目的
     * 将更多的时间专注于数据库表设计。
     * 降低编写重复代码时间。
     * 提高代码编写效率。
     
* ### ☘️功能版本
     * V1.0
         * 依据MySQL 对应的数据表，一键生成指定模板的Model、Service、Impl、Controller代码。（进行中......）
         * 依据MySQL 对应的数据表，一键生成Mapper.xml、MapperInterface.java 文件。（进行中......）
         
* ### ☘️使用方式(正在完善中......)     
     
* ### ☘️TODO🌴    
     * 不使用Mybatis 插件方式生成JavaDTO。
     * 对于路径错误进行相关测试。
     * 完成使用方式的文档编写
     
* ### ☘️项目结构
     * 项目结构与包名注释🌴
     ```
    ├── README.md
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── zhengjianbin
    │   │   │           ├── PangolinApplication.java
    │   │   │           └── generatecode-----异常处理类
    │   │   │               ├── exception
    │   │   │               │   └── PangolinCarryException.java
    │   │   │               ├── field
    │   │   │               ├── kernel-----核心包
    │   │   │               │   └── CodeGenerator.java
    │   │   │               ├── mysql-----MySQL 相关包
    │   │   │               │   └── MySQLConnect.java
    │   │   │               ├── template-----Template 文件
    │   │   │               │   ├── ModelDto.ftl
    │   │   │               │   ├── Service.ftl
    │   │   │               │   └── ServiceImpl.ftl
    │   │   │               ├── testfile-----使用模板生成的对应Model、Service、Impl、Controller类
    │   │   │               │   ├── controller
    │   │   │               │   ├── model
    │   │   │               │   │   └── AdminUser.java
    │   │   │               │   └── service
    │   │   │               │       ├── AdminUserService.java
    │   │   │               │       └── impl
    │   │   │               └── util-----相关Utils、常量类
    │   │   │                   ├── Constants.java
    │   │   │                   ├── FileUtils.java
    │   │   │                   ├── MysqlFieldConvertJavaField.java
    │   │   │                   └── StrUtils.java
    │   │   └── resources
    │   │       └── application.properties
    │   └── test-----对相关代码测试
    │       └── java
    │           └── com
    │               └── zhengjianbin
    │                   ├── PangolinApplicationTests.java
    │                   └── test
    │                       └── Main.java
    

     ```  