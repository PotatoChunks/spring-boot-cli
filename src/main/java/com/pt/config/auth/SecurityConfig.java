package com.pt.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //方法返回的对象为后续的oauth2的配置提供服务
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 初始化加密对象
     * 此对象提供了一种不可逆的加密方式,相对于md5方式会更加安全
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 将自定义provider注入
        builderConfigProvider(http);

        http.authorizeRequests()
                .antMatchers("/**").permitAll();
                //.anyRequest().authenticated();//所有资源 都必须登录才能访问
    }

    private void builderConfigProvider(HttpSecurity http){
        //自定义验证注入类


        //http.authenticationProvider(quickLoginAuthenticationProvider);

    }

}
