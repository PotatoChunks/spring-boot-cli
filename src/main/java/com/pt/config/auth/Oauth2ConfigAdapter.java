package com.pt.config.auth;

import com.pt.config.auth.granter.MyPasswordGranter;
import com.pt.dto.contant.MyConstant;
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
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
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
    private final JwtTokenEnhancer jwtTokenEnhancer;
    //用户信息
    private final UserDetailsServiceImpl userDetailsService;

    //定义客户端可以携带那些信息进行认证
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //客户端的标识 client_id
                .withClient(MyConstant.MY_APP_CLIENT)
                //客户端秘钥
                .secret(passwordEncoder.encode("123456"))
                //满足认证类型条件的 都通过认证
                .scopes("all")
                //定义允许认证类型 允许那些数据进行认证
                .authorizedGrantTypes("authorization_code", "refresh_token", "my_password")
                //token过期时间7天
                .accessTokenValiditySeconds(3600*24*7)
                //刷新token时间15天
                .refreshTokenValiditySeconds(3600*24*15);
    }

    //定义有谁来完成认证 认证成功后怎样的形式 颁发令牌
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        addAutoOauth2Granter(endpoints);
        //内容增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        //多个内容增强器
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        //delegates.add(jwtAccessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates); //配置JWT的内容增强器
        //
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter())
                //.tokenStore(jwtTokenStore())
                //.accessTokenConverter(jwtAccessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }

    //定义 公开认证的地址
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();//允许通过表单进行认证
    }


    /**
     * 此方法为用自己的签名证书，去生成token
     * 需要生成一个自己的签名证书
     * 命令教程
     * https://dzone.com/articles/creating-self-signed-certificate
     * 参数说明
     * https://zhuanlan.zhihu.com/p/508886081
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
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mykeystore.jks"), "123456789".toCharArray());
        return keyStoreKeyFactory.getKeyPair("servercert", "123456789".toCharArray());
    }

    /**
     * 用于OAuth2生成的token和JWT生成的token进行一个转换
     */
    /*@Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey( Base64.getEncoder().encodeToString("secret".getBytes(StandardCharsets.UTF_8)) );
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }*/


    // 添加自定义OAuth2模式
    private void addAutoOauth2Granter(AuthorizationServerEndpointsConfigurer endpoints){
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));

        granters.add(new MyPasswordGranter(authenticationManager,endpoints.getTokenServices(),endpoints.getClientDetailsService(),endpoints.getOAuth2RequestFactory()));

        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granters);
        endpoints.tokenGranter(compositeTokenGranter);
    }


}
