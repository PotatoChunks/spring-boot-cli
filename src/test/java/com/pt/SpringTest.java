package com.pt;

import com.pt.db.mapper.UmsMemberUserMapper;
import com.pt.db.model.UmsMemberUser;
import com.pt.db.model.UmsMemberUserExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringTest {

    @Autowired
    private UmsMemberUserMapper memberUserMapper;
    @Test
    void test() {
        UmsMemberUserExample umsMemberUserExample = new UmsMemberUserExample();
        umsMemberUserExample.createCriteria();
        List<UmsMemberUser> umsMemberUsers = memberUserMapper.selectByExample(umsMemberUserExample);
        System.out.println(umsMemberUsers);
    }
}
