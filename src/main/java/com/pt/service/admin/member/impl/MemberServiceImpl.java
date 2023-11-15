package com.pt.service.admin.member.impl;

import com.pt.db.model.SmsMemberRoleRelation;
import com.pt.service.admin.member.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {



    public void createMemberRole(Long userId){
        //
        SmsMemberRoleRelation smsMemberRoleRelation = new SmsMemberRoleRelation();
        smsMemberRoleRelation.setMemberId(userId);
        smsMemberRoleRelation.setRoleId(1L);
    }



}
