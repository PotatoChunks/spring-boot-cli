package com.pt.config.auth;

import com.pt.config.auth.granter.MyPasswordAuthenticationProvide;
import com.pt.service.app.ums.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UmsMemberService memberService;

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

        //http.authorizeRequests()
                //.antMatchers("/**").permitAll();
        //.anyRequest().authenticated();//所有资源 都必须登录才能访问
        // 禁止跨域攻击
        //http.csrf().disable();
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                //关闭跨站请求防护
                .csrf().disable();
    }

    private void builderConfigProvider(HttpSecurity http){
        //自定义验证注入类
        //账号密码登录
        MyPasswordAuthenticationProvide passwordAuthenticationProvide = new MyPasswordAuthenticationProvide(request,memberService);
        http.authenticationProvider(passwordAuthenticationProvide);

    }

}
