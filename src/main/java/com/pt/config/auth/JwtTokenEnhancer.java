package com.pt.config.auth;

import com.pt.api.http.AESUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JWT内容增强器
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        UserDetailsMsg securityUser = (UserDetailsMsg) oAuth2Authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        //把用户ID设置到JWT中
        info.put("user_code", securityUser.getUserId());
        info.put("client_id",securityUser.getClientId());
        // AES 加密信息
        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        String rolesStr = authorities.stream().map(String::valueOf).collect(Collectors.joining(","));
        //clientId 用户编号 权限身份
        String clientJwtCode = securityUser.getClientId() + securityUser.getUserId() + rolesStr;
        //加密
        info.put("jtc", AESUtils.encrypt(clientJwtCode));
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
