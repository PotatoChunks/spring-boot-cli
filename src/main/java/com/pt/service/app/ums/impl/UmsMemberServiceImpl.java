package com.pt.service.app.ums.impl;

import com.github.pagehelper.PageHelper;
import com.pt.db.mapper.UmsMemberUserInfoMapper;
import com.pt.db.mapper.UmsMemberUserMapper;
import com.pt.db.model.UmsMemberUser;
import com.pt.db.model.UmsMemberUserExample;
import com.pt.dto.contant.UserMsgDto;
import com.pt.service.app.ums.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberUserMapper memberUserMapper;
    @Autowired
    private UmsMemberUserInfoMapper memberUserInfoMapper;


    public List<UmsMemberUser> getUserList(Integer pageNum,Integer pageSize){
        UmsMemberUserExample umsMemberUserExample = new UmsMemberUserExample();
        umsMemberUserExample.createCriteria();
        PageHelper.startPage(pageNum,pageSize);
        List<UmsMemberUser> umsMemberUsers = memberUserMapper.selectByExample(umsMemberUserExample);

        return umsMemberUsers;
    }

    public UserMsgDto getUserInfoByOauth(String userId){
        UmsMemberUserExample umsMemberUserExample = new UmsMemberUserExample();
        umsMemberUserExample.createCriteria().andUserCodeEqualTo(userId);
        List<UmsMemberUser> umsMemberUsers = memberUserMapper.selectByExample(umsMemberUserExample);
        if (umsMemberUsers == null) return null;
        UmsMemberUser umsMemberUser = umsMemberUsers.get(0);

        UserMsgDto userMsgDto = new UserMsgDto();
        userMsgDto.setId(umsMemberUser.getId())
                .setPassword(umsMemberUser.getPassword())
                .setStatus(umsMemberUser.getStatus())
                .setUserCode(umsMemberUser.getUserCode());

        //查询权限
        // userDto.setRoles(CollUtil.toList("前台会员"));

        return userMsgDto;
    }


}
