package top.potat.spring.admin.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import top.potat.spring.admin.config.router.GatewayRouterConfig;
import top.potat.spring.admin.utils.AuthUtils;
import top.potat.spring.common.config.JwtUtils;
import top.potat.spring.common.constant.CommonConstant;
import top.potat.spring.common.enums.YesEnum;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.common.utils.result.CommonResult;
import top.potat.spring.db.mapper.SmsAdminMapper;
import top.potat.spring.db.model.SmsAdmin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义过滤器
 * 已弃用
 */
@Deprecated
//@Component
@RequiredArgsConstructor
public class CustomFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);
    private final SmsAdminMapper smsAdminMapper;

    /**
     * 路由配置
     */
    private final GatewayRouterConfig gatewayRouterConfig;
    /**
     * jwt工具类
     */
    private final JwtUtils jwtUtils;
    /**
     * 权限工具类
     */
    private final AuthUtils authUtils;

    @Value("${knife4jConfig.disableEnable:false}")
    private Boolean enable;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // 处理预检请求
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String requestURI = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();

        if (!enable) {
            if (pathMatcher.match("/doc.html", requestURI) ||
                    pathMatcher.match("/swagger-resources/**", requestURI) ||
                    pathMatcher.match("/swagger/**", requestURI) ||
                    pathMatcher.match("/**/v2/api-docs", requestURI)){
                //返回200,data:无权访问
                noPermissionResSend(servletResponse, null);
                return;
            }
        }

        //不需要token访问的路径
        List<String> noLog = gatewayRouterConfig.getNoLog();
        if (noLog == null) noLog = new ArrayList<>();

        //如果访问的是这些路径则直接通过
        for (String url : noLog) {
            if (pathMatcher.match(url, requestURI)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        String token = request.getHeader(CommonConstant.AUTHORIZATION);

        if (StringUtils.isEmpty(token)) {
            //没有token
            noPermissionResSend(servletResponse, null);
            return;
        }

        //解析 token
        SmsAdmin smsAdmin = null;
        try {
            smsAdmin = parseToken(token);
        } catch (Exception e) {
            noLoginResSend(servletResponse);
            return;
        }

        if (smsAdmin == null){
            noLoginResSend(servletResponse);
            return;
        }


        //需要token但是不用权限的路径
        List<String> whiteList = gatewayRouterConfig.getWhiteList();
        if (whiteList == null) whiteList = new ArrayList<>();

        for (String url : whiteList) {
            if (pathMatcher.match(url, requestURI)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }


        //校验权限
        List<String> authorityByUserId = authUtils.getAuthorityByUserId(smsAdmin.getId());
        if(CollectionUtil.isEmpty(authorityByUserId)){
            noPermissionResSend(servletResponse, "无权限访问");
            return;
        }

        for (String url : authorityByUserId) {
            if (!pathMatcher.match(url, requestURI)){
                noPermissionResSend(servletResponse, "无权限访问");
                return;
            }
        }


        // 继续执行下一个过滤器或请求处理逻辑
        filterChain.doFilter(servletRequest, servletResponse);
    }


    //没有权限
    private void noPermissionResSend(ServletResponse response, String errMsg) throws IOException {
        // 转换为HttpServletResponse以设置状态码
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 设置HTTP状态码为200
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        // 不设置前端会出现跨域操作
        // 2. 手动添加跨域响应头（与 GlobalCorsConfig 配置保持一致）
        httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有源（生产环境建议指定具体域名）
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // 允许的方法
        httpResponse.setHeader("Access-Control-Allow-Headers", "*"); // 允许的请求头
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true"); // 允许携带 Cookie


        response.setContentType("application/json;charset=utf-8");
        if (errMsg == null) {
            response.getWriter().write(JSONObject.toJSONString(CommonResult.forbidden("没有权限")));
        } else {
            response.getWriter().write(JSONObject.toJSONString(CommonResult.failed(errMsg)));
        }
    }

    //没有登录
    private void noLoginResSend(ServletResponse response) throws IOException {
        // 转换为HttpServletResponse以设置状态码
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 设置HTTP状态码为200
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        // 2. 手动添加跨域响应头（与 GlobalCorsConfig 配置保持一致）
        httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有源（生产环境建议指定具体域名）
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // 允许的方法
        httpResponse.setHeader("Access-Control-Allow-Headers", "*"); // 允许的请求头
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true"); // 允许携带 Cookie


        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(CommonResult.unauthorized("请登录")));
    }


    /**
     * 解析token
     * 返回角色id列表
     */
    private SmsAdmin parseToken(String token) {
        if (StringUtils.isEmpty(token))
            ErrorAsserts.loginException("暂未登录");
        Claims body = jwtUtils.extractAllClaims(token, CommonConstant.Token_ADMIN_SUBJECT);
        //获取用户id
        Object adminIdObj = body.get("id");
        if (StringUtils.isNull(adminIdObj) || StringUtils.isEmpty(String.valueOf(adminIdObj)))
            ErrorAsserts.loginException("暂未登录");
        Long adminId = null;
        try {
            adminId = Long.parseLong(String.valueOf(adminIdObj));
        } catch (Exception e) {
            ErrorAsserts.loginException("暂未登录");
        }
        SmsAdmin smsAdmin = smsAdminMapper.selectByPrimaryKey(adminId);
        if (smsAdmin == null || !Objects.equals(smsAdmin.getDeleteType(), YesEnum.NO.getCodeType())) {
            ErrorAsserts.loginException("用户不存在或已禁用");
        }

        return smsAdmin;
    }

}
