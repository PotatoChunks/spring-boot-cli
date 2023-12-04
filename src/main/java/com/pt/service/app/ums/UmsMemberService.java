package com.pt.service.app.ums;

import com.pt.db.model.UmsMemberUser;
import com.pt.dto.contant.UserMsgDto;

import java.util.List;

public interface UmsMemberService {

    //用户列表
    List<UmsMemberUser> getUserList(Integer pageNum, Integer pageSize);

    //根据用户code查询用户信息返回给oauth
    UserMsgDto getUserInfoByOauth(String userId);

    //验证账号密码
    Boolean checkedUserCodePwd(String userCode,String password);

}
