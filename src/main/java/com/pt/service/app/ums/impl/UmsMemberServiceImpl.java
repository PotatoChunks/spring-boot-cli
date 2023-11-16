package com.pt.service.app.ums.impl;

import com.github.pagehelper.PageHelper;
import com.pt.api.xssh.XSSUtils;
import com.pt.db.mapper.SmsMemberRoleRelationMapper;
import com.pt.db.mapper.UmsMemberUserInfoMapper;
import com.pt.db.mapper.UmsMemberUserMapper;
import com.pt.db.model.UmsMemberUser;
import com.pt.db.model.UmsMemberUserExample;
import com.pt.dto.contant.UserMsgDto;
import com.pt.service.app.ums.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberUserMapper memberUserMapper;
    @Autowired
    private UmsMemberUserInfoMapper memberUserInfoMapper;
    @Autowired
    private SmsMemberRoleRelationMapper memberRoleRelationMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<UmsMemberUser> getUserList(Integer pageNum,Integer pageSize){
        UmsMemberUserExample umsMemberUserExample = new UmsMemberUserExample();
        umsMemberUserExample.createCriteria();
        PageHelper.startPage(pageNum,pageSize);
        List<UmsMemberUser> umsMemberUsers = memberUserMapper.selectByExample(umsMemberUserExample);

        return umsMemberUsers;
    }


    public UserMsgDto getUserInfoByOauth(String userId){
        userId = XSSUtils.stripXSS(userId);
        if (StringUtils.isEmpty(userId)) return null;

        UmsMemberUserExample umsMemberUserExample = new UmsMemberUserExample();
        umsMemberUserExample.createCriteria().andUserCodeEqualTo(userId);
        List<UmsMemberUser> umsMemberUsers = memberUserMapper.selectByExample(umsMemberUserExample);
        if (umsMemberUsers == null || umsMemberUsers.size() <= 0) return null;
        UmsMemberUser umsMemberUser = umsMemberUsers.get(0);

        UserMsgDto userMsgDto = new UserMsgDto();
        userMsgDto.setId(umsMemberUser.getId())
                .setPassword(umsMemberUser.getPassword())
                .setStatus(umsMemberUser.getStatus())
                .setUserCode(umsMemberUser.getUserCode());
        //用户身份关联
        userMsgDto.setRoles(new ArrayList<String>(Collections.singleton("普通用户")));

        return userMsgDto;
    }


    public Boolean checkedUserCodePwd(String userCode,String password){
        userCode = XSSUtils.stripXSS(userCode);
        password = XSSUtils.stripXSS(password);

        //密码加密
        String encode = passwordEncoder.encode(password);

        if (StringUtils.isEmpty(userCode) || StringUtils.isEmpty(password)) return false;
        UmsMemberUserExample umsMemberUserExample = new UmsMemberUserExample();
        umsMemberUserExample.createCriteria().andUserCodeEqualTo(userCode).andPasswordEqualTo(encode);
        long userCont = memberUserMapper.countByExample(umsMemberUserExample);
        return userCont > 0L;
    }

}
