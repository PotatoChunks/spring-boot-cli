package com.pt.config.gateway;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义过滤器
 */
@Component
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 在请求处理前执行自定义逻辑
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("Authorization");
        // ...
        // 继续执行下一个过滤器或请求处理逻辑
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
