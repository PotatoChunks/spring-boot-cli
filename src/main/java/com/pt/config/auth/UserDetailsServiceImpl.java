package com.pt.config.auth;

import com.pt.dto.contant.MyConstant;
import com.pt.dto.contant.UserMsgDto;
import com.pt.service.app.ums.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 赋值用户信息实现类
 * 实现不同的client-id 赋值不同的用户信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserMsgDto userMsgDto = null;

        //
        if (MyConstant.MY_APP_CLIENT.equals(clientId)) {
            userMsgDto = memberService.getUserInfoByOauth(userId);
        }

        if (userMsgDto == null) {
            throw new UsernameNotFoundException("暂无此用户");
        }

        userMsgDto.setClientId(clientId);
        UserDetailsMsg userDetailsMsg = new UserDetailsMsg(userMsgDto);

        //账号状态判断
        if (!userDetailsMsg.isEnabled()) {
            throw new DisabledException("账号已禁用");
        }

        return userDetailsMsg;
    }
}
