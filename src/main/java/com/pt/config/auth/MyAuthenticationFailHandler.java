package com.pt.config.auth;

import com.alibaba.fastjson.JSONObject;
import com.pt.api.common.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登陆失败处理
 */
@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        //设置状态码
        response.setStatus(500);
        //返回格式
        response.setContentType("application/json;charset=UTF-8");
        //内容
        response.getWriter().write(JSONObject.toJSONString(CommonResult.failed("登陆失败或token已过期")));
    }

}
