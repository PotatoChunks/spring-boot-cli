package com.pt.config.auth.granter;

import com.pt.config.auth.UserDetailsMsg;
import com.pt.dto.contant.MyConstant;
import com.pt.dto.contant.UserMsgDto;
import com.pt.service.app.ums.UmsMemberService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证的主要逻辑
 */
public class MyPasswordAuthenticationProvide implements AuthenticationProvider {
    private HttpServletRequest request;
    private UmsMemberService memberService;

    public MyPasswordAuthenticationProvide(HttpServletRequest request,UmsMemberService memberService){
        this.request = request;
        this.memberService = memberService;
    }

    @Override
    public Authentication authenticate(Authentication authentication){
        String clientId = request.getParameter("client_id");
        MyPasswordAuthenticationToken authenticationToken = (MyPasswordAuthenticationToken) authentication;
        String userCode = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();

        if (!MyConstant.MY_APP_CLIENT.equals(clientId)) {
            throw new BadCredentialsException("无权访问");
        }

        //账号密码是否正确
        Boolean checkedUserCodePwd = memberService.checkedUserCodePwd(userCode, password);

        if(!checkedUserCodePwd) throw new BadCredentialsException("账号或密码错误");

        UserMsgDto userMsgDto = memberService.getUserInfoByOauth(userCode);

        if (userMsgDto == null) {
            throw new BadCredentialsException("该用户不存在！");
        }
        userMsgDto.setClientId(clientId);

        UserDetailsMsg userDetailsMsg = new UserDetailsMsg(userMsgDto);
        if (!userDetailsMsg.isEnabled()) {
            throw new DisabledException("账号已被禁用");
        }

        MyPasswordAuthenticationToken authenticationResult = new MyPasswordAuthenticationToken(userDetailsMsg, password, userDetailsMsg.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MyPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
