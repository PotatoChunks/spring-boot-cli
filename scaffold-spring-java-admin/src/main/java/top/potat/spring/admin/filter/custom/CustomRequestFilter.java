package top.potat.spring.admin.filter.custom;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义请求过滤器
 */
@Component
@RequiredArgsConstructor
public class CustomRequestFilter extends OncePerRequestFilter {
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
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();

        if (!enable) {
            if (pathMatcher.match("/doc.html", requestURI) ||
                    pathMatcher.match("/swagger-resources/**", requestURI) ||
                    pathMatcher.match("/swagger/**", requestURI) ||
                    pathMatcher.match("/**/v2/api-docs", requestURI)){
                //返回200,data:无权访问
                setResponse(response, CommonResult.forbidden("无权访问"));
                return;
            }
        }

        //不需要token访问的路径
        List<String> noLog = gatewayRouterConfig.getNoLog();
        if (noLog == null) noLog = new ArrayList<>();

        //如果访问的是这些路径则直接通过
        for (String url : noLog) {
            if (pathMatcher.match(url, requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String token = request.getHeader(CommonConstant.AUTHORIZATION);

        if (StringUtils.isEmpty(token)) {
            //没有token
            setResponse(response, CommonResult.unauthorized("暂未登录"));
            return;
        }

        //解析 token
        SmsAdmin smsAdmin = null;
        try {
            smsAdmin = parseToken(token);
        } catch (Exception e) {
            setResponse(response, CommonResult.unauthorized("暂未登录"));
            return;
        }

        if (smsAdmin == null){
            setResponse(response, CommonResult.unauthorized("暂未登录"));
            return;
        }

        //需要token但是不用权限的路径
        List<String> whiteList = gatewayRouterConfig.getWhiteList();
        if (whiteList == null) whiteList = new ArrayList<>();

        for (String url : whiteList) {
            if (pathMatcher.match(url, requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        //校验权限
        List<String> authorityByUserId = authUtils.getAuthorityByUserId(smsAdmin.getId());
        if(CollectionUtil.isEmpty(authorityByUserId)){
            setResponse(response, CommonResult.forbidden("无权访问"));
            return;
        }

        for (String url : authorityByUserId) {
            if (pathMatcher.match(url, requestURI)){
                //继续执行下一个过滤器或请求处理逻辑
                filterChain.doFilter(request, response);
                return;
            }
        }

        //无权限
        setResponse(response, CommonResult.forbidden("无权访问"));
    }


    /**
     * 设置返回结果
     */
    private void setResponse(HttpServletResponse response, CommonResult<String> commonResult) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSONObject.toJSONString(commonResult));
        response.getWriter().flush();
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
        if (smsAdmin == null
                || !Objects.equals(smsAdmin.getDeleteType(), YesEnum.NO.getCodeType())
                || !Objects.equals(smsAdmin.getDisableType(), YesEnum.YES.getCodeType())) {
            ErrorAsserts.loginException("用户不存在或已禁用");
        }

        return smsAdmin;
    }

}
