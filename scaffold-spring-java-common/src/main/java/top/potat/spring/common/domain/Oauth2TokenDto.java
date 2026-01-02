package top.potat.spring.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Oauth2TokenDto {
    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("有效时间（秒）")
    private long expiresIn;

    public static Oauth2TokenDto build(String token, String refreshToken, long expiresIn) {
        Oauth2TokenDto oauth2TokenDto = new Oauth2TokenDto();
        oauth2TokenDto.setToken(token);
        oauth2TokenDto.setRefreshToken(refreshToken);
        oauth2TokenDto.setExpiresIn(expiresIn);
        return oauth2TokenDto;
    }

    public static Oauth2TokenDto build(String token, long expiresIn) {
        Oauth2TokenDto oauth2TokenDto = new Oauth2TokenDto();
        oauth2TokenDto.setToken(token);
        oauth2TokenDto.setExpiresIn(expiresIn);
        return oauth2TokenDto;
    }

    public static Oauth2TokenDto build(String token) {
        Oauth2TokenDto oauth2TokenDto = new Oauth2TokenDto();
        oauth2TokenDto.setToken(token);
        return oauth2TokenDto;
    }

}
