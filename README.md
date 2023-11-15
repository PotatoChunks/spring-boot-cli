基于SpringBoot开发的项目框架

```markdown
|-- com
    |-- MyApplication.java
    |-- api
    |   |-- common
    |   |   |-- CommonResult.java
    |   |   |-- ResultCode.java
    |   |-- http
    |       |-- HttpUtil.java
    |-- config
    |   |-- auth
    |   |   |-- JwtTokenEnhancer.java
    |   |   |-- Oauth2ConfigAdapter.java
    |   |   |-- SecurityConfig.java
    |   |   |-- UserDetailsMsg.java
    |   |   |-- UserDetailsServiceImpl.java
    |   |   |-- granter
    |   |-- mybatis
    |       |-- CommentGenerator.java
    |       |-- Generator.java
    |       |-- MyBatisConfig.java
    |-- controller
    |   |-- IndexController.java
    |   |-- app
    |       |-- sms
    |       |-- ums
    |           |-- UmsMemberController.java
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
    |       |-- UserMsgDto.java
    |-- service
        |-- admin
        |   |-- admin
        |   |-- member
        |       |-- MemberService.java
        |       |-- impl
        |           |-- MemberServiceImpl.java
        |-- app
            |-- sms
            |-- ums
                |-- UmsMemberService.java
                |-- impl
                    |-- UmsMemberServiceImpl.java
|-- com
    |-- MyApplication.java
    |-- api
    |   |-- common
    |   |   |-- CommonResult.java
    |   |   |-- ResultCode.java
    |   |-- http
    |       |-- HttpUtil.java
    |-- config
    |   |-- auth
    |   |   |-- JwtTokenEnhancer.java
    |   |   |-- Oauth2ConfigAdapter.java
    |   |   |-- SecurityConfig.java
    |   |   |-- UserDetailsMsg.java
    |   |   |-- UserDetailsServiceImpl.java
    |   |   |-- granter
    |   |-- mybatis
    |       |-- CommentGenerator.java
    |       |-- Generator.java
    |       |-- MyBatisConfig.java
    |-- controller
    |   |-- IndexController.java
    |   |-- app
    |       |-- sms
    |       |-- ums
    |           |-- UmsMemberController.java
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
    |       |-- UserMsgDto.java
    |-- service
        |-- admin
        |   |-- admin
        |   |-- member
        |       |-- MemberService.java
        |       |-- impl
        |           |-- MemberServiceImpl.java
        |-- app
            |-- sms
            |-- ums
                |-- UmsMemberService.java
                |-- impl
                    |-- UmsMemberServiceImpl.java

```