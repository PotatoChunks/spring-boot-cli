### SpringBoot练手项目

这是一个springBoot的练手项目

有权限校验,角色管理,采用主流技术实现

`master`分支基于SpringBoot 2.7.18 + JDK8 + redis + MySQL

#### 项目结构


`scaffold-spring-java-admin` 管理后台服务

`scaffold-spring-java-common` 全局公共工具类

`scaffold-spring-java-db` 数据库工具类(简单的增删改查)自动生成

`scaffold-spring-java-service` 全局公共service服务类



### 启动项目

1. 需要mysql版大于5.7或者MariaDB 10.5以上
2. 需要redis

document文件夹中有sql文件

在`scaffold-spring-java-admin`模块中的资源文件里面`resources`修改配置文件对应当前的数据库地址用户名和密码
`application-druid.yml`

```text
scaffold-spring-java
    |
    |__scaffold-spring-java-admin
    |____src
    |     |__main
    |       |__resources
    |           |__application-druid.yml
```


启动类为
`ScaffoldAdminApplication`