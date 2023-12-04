基于SpringBoot开发的项目框架

项目目录结构
```markdown
|-- com
    |-- MyApplication.java  启动文件
    |-- api  API工具
    |   |-- common  常用工具
    |   |   |-- CommonPage.java  分页
    |   |   |-- CommonResult.java  接口返回信息封装类
    |   |   |-- ResultCode.java  接口状态码枚举
    |   |-- http  HTTP工具类
    |   |   |-- AESUtils.java  AES加密工具类
    |   |   |-- HttpUtil.java  http链接封装类
    |   |   |-- MD5Utils.java  MD5加密方法
    |   |   |-- RSAUtils.java  RSA加密解密工具
    |   |-- xssh  xssh工具类
    |       |-- XSSUtils.java  xssh过滤工具
    |-- config  全局配置
    |   |-- auth  网管配置
    |   |   |-- JwtTokenEnhancer.java  JWT内容增强器
    |   |   |-- MyAuthenticationFailHandler.java  自定义登陆失败处理
    |   |   |-- Oauth2ConfigAdapter.java  oauth2启动认证和授权
    |   |   |-- Oauth2ExceptionHandler.java  全局处理Oauth2抛出的异常
    |   |   |-- SecurityConfig.java  资源服务认证配置
    |   |   |-- UserDetailsMsg.java  jwt需要存储的用户信息
    |   |   |-- UserDetailsServiceImpl.java  用户信息实现类
    |   |   |-- granter  自定义登陆逻辑
    |   |       |-- MyPasswordAuthenticationProvide.java  验证的主要逻辑
    |   |       |-- MyPasswordAuthenticationToken.java  封装类
    |   |       |-- MyPasswordGranter.java  客户端id
    |   |-- exception  自定义异常
    |   |   |-- IdentityMismatchException.java  身份不匹配异常
    |   |-- gateway  全局过滤器
    |   |   |-- CustomFilter.java  自定义过滤器
    |   |   |-- IgnoreUrlConfig.java  读取配置文件类
    |   |   |-- UserMsgDetails.java  jwt用户信息类
    |   |-- handler 异常处理机制
    |   |   |-- SystemExceptionHandler.java  全局异常处理
    |   |-- mybatis  数据库代码生成工具
    |       |-- CommentGenerator.java  自定义注释生成器
    |       |-- Generator.java  生成代码主启动类
    |       |-- MyBatisConfig.java  MyBatis相关配置
    |-- controller  接口
    |   |-- IndexController.java
    |   |-- app
    |   |   |-- ums
    |   |       |-- MemberController.java
    |   |-- oauth
    |       |-- AuthController.java  获取token(不能对外暴露)
    |-- db  数据库生成类
    |   |-- mapper
    |   |   |-- SmsMemberRoleRelationMapper.java
    |   |   |-- SmsResourceCategoryMapper.java
    |   |   |-- SmsResourceMapper.java
    |   |   |-- SmsRoleMapper.java
    |   |   |-- SmsRoleResourceRelationMapper.java
    |   |   |-- UmsMemberUserInfoMapper.java
    |   |   |-- UmsMemberUserMapper.java
    |   |-- model
    |       |-- SmsMemberRoleRelation.java
    |       |-- SmsMemberRoleRelationExample.java
    |       |-- SmsResource.java
    |       |-- SmsResourceCategory.java
    |       |-- SmsResourceCategoryExample.java
    |       |-- SmsResourceExample.java
    |       |-- SmsRole.java
    |       |-- SmsRoleExample.java
    |       |-- SmsRoleResourceRelation.java
    |       |-- SmsRoleResourceRelationExample.java
    |       |-- UmsMemberUser.java
    |       |-- UmsMemberUserExample.java
    |       |-- UmsMemberUserInfo.java
    |       |-- UmsMemberUserInfoExample.java
    |-- dto  自定义封装类
    |   |-- Oauth2TokenDto.java  token返回信息
    |   |-- Oauth2TokenReq.java  token请求信息
    |   |-- contant  常量类
    |       |-- MyConstant.java  自定义常量
    |       |-- UserMsgDto.java  用户信息
    |-- service  接口实现类
        |-- admin
        |   |-- member
        |       |-- MemberService.java
        |       |-- impl
        |           |-- MemberServiceImpl.java
        |-- app
            |-- ums
                |-- UmsMemberService.java
                |-- impl
                    |-- UmsMemberServiceImpl.java




```