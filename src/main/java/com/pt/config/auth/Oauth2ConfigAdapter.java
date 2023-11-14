package com.pt.config.auth;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor //创建一个全参构造 spring自动去注入 私有属性
@Configuration
@EnableAuthorizationServer //在oauth2规范中启动认证和授权
public class Oauth2ConfigAdapter extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    //定义客户端可以携带那些信息进行认证
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //客户端的标识 client_id
                .withClient("my-client")
                //客户端秘钥
                .secret(passwordEncoder.encode(""))
                //满足认证类型条件的 都通过认证
                .scopes("all")
                //定义允许认证类型 允许那些数据进行认证
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                //token过期时间7天
                .accessTokenValiditySeconds(3600*24*7)
                //刷新token时间15天
                .refreshTokenValiditySeconds(3600*24*15);
    }

    //定义有谁来完成认证 认证成功后怎样的形式 颁发令牌
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        addAutoOauth2Granter(endpoints);
        //
        endpoints.authenticationManager(authenticationManager);
    }

    //定义 公开认证的地址
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();//允许通过表单进行认证
    }


    /**
     * 次方法为用自己的签名证书，去生成token
     * 需要生成一个自己的签名证书
     * https://dzone.com/articles/creating-self-signed-certificate
     * */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }
    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456789".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456789".toCharArray());
    }


    // 添加自定义OAuth2模式
    private void addAutoOauth2Granter(AuthorizationServerEndpointsConfigurer endpoints){
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));


        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granters);
        endpoints.tokenGranter(compositeTokenGranter);
    }

}