package com.pt.service.app.ums;

import com.pt.db.model.UmsMemberUser;

import java.util.List;

public interface UmsMemberService {

    List<UmsMemberUser> getUserList(Integer pageNum, Integer pageSize);

}
