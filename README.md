# PangolinCarryCode

## 项目介绍
* ### ☘️介绍
     * 基于FreeMarker，根据MySQL 数据库一键生成Mybatis Mapper、MapperInterface、Model、Service、Impl、Controller等代码
        
* ### ☘️目的
     * 降低，降低，降低编写重复代码时间。
     * 提高代码编写有价值代码的效率。
     * 将更多的时间专注于数据库表设计。
     
* ### ☘️功能版本
     * V1.0
         * 依据MySQL 对应的数据表，一键生成指定模板的Model、Service、Impl、Controller代码。（2019.9.16 完成）
         * 依据MySQL 对应的数据表，一键生成Mapper.xml、MapperInterface.java 文件。（2019.9.14 完成）
         * 依据MySQL 对应的数据表，将Mybatis Mapper、MapperInterface与Model、Service、Impl、Controller整合到一起。（进行中...）
         
* ### ☘️使用方式(正在完善中......)     
     
     
* ### ☘️TODO🌴  
     * 完成Dto、Service、Impl、Controller 模板以及代码生成编写与测试。(2019.9.16 完成)
     * 该用Free Marker方式，生成JavaDto。而不是使用Mybatis 插件。(进行中...)
     * 对于路径错误进行相关测试。(进行中...)
     * 完成从Mapper、MapperInterface、Dto、Service、Impl整体流程的测试。(进行中...)
     * 完成使用方式的文档编写。(进行中...)
     
* ### ☘️项目结构
     * 项目结构与包名注释🌴
     ```
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── zhengjianbin
    │   │           ├── PangolinApplication.java
    │   │           └── generatecode
    │   │               ├── exception-----异常处理类
    │   │               │   └── PangolinCarryException.java
    │   │               ├── field
    │   │               ├── generatefile-----测试生成文件
    │   │               │   ├── controller
    │   │               │   │   └── AdminUserController.java
    │   │               │   ├── model
    │   │               │   │   └── AdminUser.java
    │   │               │   └── service
    │   │               │       ├── AdminUserService.java
    │   │               │       └── impl
    │   │               │           └── AdminUserImpl.java
    │   │               ├── kernel-----核心包
    │   │               │   └── CodeGenerator.java
    │   │               ├── mybatis-----Mybatis生成器相关配置
    │   │               │   └── MyBatisConfiguration.java
    │   │               ├── mysql-----MySQL 相关包
    │   │               │   └── MySQLConnect.java
    │   │               ├── template-----模板文件
    │   │               │   ├── Controller.ftl
    │   │               │   ├── ModelDto.ftl
    │   │               │   ├── Service.ftl
    │   │               │   └── ServiceImpl.ftl
    │   │               └── util-----相关Utils、常量类
    │   │                   ├── Constants.java
    │   │                   ├── FileUtils.java
    │   │                   ├── MysqlFieldConvertJavaField.java
    │   │                   ├── StrUtils.java
    │   │                   └── result-----Controller 返回包装类
    │   │                       ├── WrapMapper.java
    │   │                       └── Wrapper.java
    │   └── resources
    │       └── application.properties
    └── test-----测试包
        └── java
            └── com
                └── zhengjianbin
                    ├── PangolinApplicationTests.java
                    └── test-----测试启动类
                        └── Main.java
     ```  