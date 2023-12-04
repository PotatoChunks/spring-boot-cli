package com.pt;

import com.pt.db.mapper.UmsMemberUserMapper;
import com.pt.db.model.UmsMemberUser;
import com.pt.db.model.UmsMemberUserExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class SpringTest {

    @Autowired
    private UmsMemberUserMapper memberUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void test() {
        UmsMemberUserExample umsMemberUserExample = new UmsMemberUserExample();
        umsMemberUserExample.createCriteria();
        List<UmsMemberUser> umsMemberUsers = memberUserMapper.selectByExample(umsMemberUserExample);
        System.out.println(umsMemberUsers);
    }

    @Test
    void test1(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        //验证密码 前端传的密码和 数据库加密的密码进行比对
        System.out.println(passwordEncoder.matches("123456","$2a$10$VT4/8OfL1m9GUP0i1wTt2O08xfwIEo7/rTSm8rdsUN7jPbnrBqEu2"));
    }
}
