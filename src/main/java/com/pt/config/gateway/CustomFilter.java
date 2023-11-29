package com.pt.config.gateway;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nimbusds.jose.JWSObject;
import com.pt.api.common.CommonResult;
import com.pt.dto.contant.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义过滤器
 */
@Component
public class CustomFilter implements Filter {
    @Autowired
    private IgnoreUrlConfig ignoreUrlConfig;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 在请求处理前执行自定义逻辑
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //请求头
        String token = request.getHeader("Authorization");
        //请求路径
        String requestURI = request.getRequestURI();

        //白名单
        List<String> urls = ignoreUrlConfig.getUrls();
        if (urls == null) urls = new ArrayList<>();
        //
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String url : urls) {
            if (pathMatcher.match(url, requestURI)) {
                //通过白名单
                // 继续执行下一个过滤器或请求处理逻辑
                chain.doFilter(servletRequest, response);
                return;
            }
        }
        //没有通过白名单 验证身份
        if (token == null || "".equals(token)) {
            //没有token
            noTokenResSend(response);
            return;
        }
        //去掉Bearer 前缀
        String realToken = token.replace(MyConstant.JWT_TOKEN_PREFIX, "");

        //解析jwt
        JWSObject jwtObject;
        try {
            jwtObject = JWSObject.parse(realToken);
        } catch (ParseException e) {
            //token错误
            noTokenResSend(response);
            return;
        }
        if (jwtObject == null) {
            noTokenResSend(response);
            return;
        }
        String userStr = jwtObject.getPayload().toString();
        System.out.println(userStr);
        System.out.println();
        TypeReference<Map<String, String>> typeReference = new TypeReference<Map<String, String>>() {};
        Map<String, String> userMsg = JSONObject.parseObject(userStr, typeReference.getType());
        System.out.println(userMsg);

        //验证身份
        //如果是管理员 需要验证管理员的资源地址


        // ...
        // 继续执行下一个过滤器或请求处理逻辑
        chain.doFilter(servletRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}

    //token异常 返回的信息
    private void noTokenResSend(ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(CommonResult.unauthorized("未登陆")));
    }
}
