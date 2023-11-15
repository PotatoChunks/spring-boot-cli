package com.pt.config.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 需要存储的用户 信息
 */
@Data
public class UserDetailsMsg implements UserDetails {

    private Long id;
    private String username;
    private String password;
    //状态
    private Boolean enabled;
    //客户端登陆id
    private String clientId;
    //权限数组
    private Collection<SimpleGrantedAuthority> authorities;

    public UserDetailsMsg(){
        //
    }

    //调用构造方法赋值
    //status:1->启用；0->禁用；
    public UserDetailsMsg(Long id, String username, String password, Integer status, String clientId, List<String> rolesList){
        if (status == null) status = 0;
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setEnabled(status == 1);
        this.setClientId(clientId);
        if (rolesList != null) {
            authorities = new ArrayList<>();
            for (String rol : rolesList) authorities.add(new SimpleGrantedAuthority(rol));
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    //是否没有到期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否 禁用
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}