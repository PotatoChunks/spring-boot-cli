基于SpringBoot开发的项目框架

```markdown
|-- com
    |-- MyApplication.java
    |-- api
    |   |-- common
    |   |   |-- CommonPage.java
    |   |   |-- CommonResult.java
    |   |   |-- ResultCode.java
    |   |-- http
    |   |   |-- HttpUtil.java
    |   |-- xssh
    |       |-- XSSUtils.java
    |-- config
    |   |-- auth
    |   |   |-- JwtTokenEnhancer.java
    |   |   |-- MyResourceServerConfig.java
    |   |   |-- Oauth2ConfigAdapter.java
    |   |   |-- SecurityConfig.java
    |   |   |-- UserDetailsMsg.java
    |   |   |-- UserDetailsServiceImpl.java
    |   |   |-- granter
    |   |       |-- MyPasswordAuthenticationProvide.java
    |   |       |-- MyPasswordAuthenticationToken.java
    |   |       |-- MyPasswordGranter.java
    |   |-- mybatis
    |       |-- CommentGenerator.java
    |       |-- Generator.java
    |       |-- MyBatisConfig.java
    |-- controller
    |   |-- IndexController.java
    |-- db
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
    |-- dto
    |   |-- contant
    |       |-- MyConstant.java
    |       |-- UserMsgDto.java
    |-- service
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