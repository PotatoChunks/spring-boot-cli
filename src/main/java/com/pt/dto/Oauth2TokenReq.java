package com.pt.dto;

import lombok.Data;
import lombok.NonNull;

/**
 *grant_type 授权模式
 *client_id Oauth2客户端ID
 *client_secret Oauth2客户端秘钥
 *refresh_token 刷新token
 *username 登录用户名
 *password 登录密码
 *source_type 授权登录类型
 */
@Data
public class Oauth2TokenReq {
    //授权模式
    @NonNull
    private String grant_type;
    //Oauth2客户端ID
    @NonNull
    private String client_id;
    //Oauth2客户端秘钥
    @NonNull
    private String client_secret;
    //刷新token
    private String refresh_token;
    //登录用户名
    private String username;
    //登录密码
    private String password;

}
