package com.pt.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Oauth2TokenDto {
    //访问令牌
    private String token;
    //刷令牌
    private String refreshToken;
    //访问令牌头前缀
    private String tokenHead;
    //有效时间(秒)
    private int expiresIn;
}
