package top.potat.spring.admin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 统一日志处理切面
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * top.potat.spring.admin.controller.*.*.*(..))||execution(public * top.potat.spring.admin.*.controller.*.*(..))||execution(public * top.potat.spring.admin.controller.*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取请求方式
        String method = request.getMethod();
        logger.debug("请求URL: {} ,请求方式: {}", request.getRequestURL(),method);
        //logger.debug("开始执行方法：{}", joinPoint.getSignature().getName());

        Object result = null;
        try {
            result = joinPoint.proceed();
        }finally {
            // 处理请求日志
            handleRequestLog(request, joinPoint);
            // 处理响应日志
            handleResponseLog(request, result);

            long endTime = System.currentTimeMillis();
            logger.debug("方法执行结束，耗时：{} ms", endTime - startTime);
        }

        return result;
    }

    // 处理请求日志
    private void handleRequestLog(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
        // 获取请求基本信息
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String contentType = request.getContentType();

        // 构建日志内容
        Map<String, Object> logInfo = new LinkedHashMap<>();
        logInfo.put("地址", uri);
        logInfo.put("方法", method);
        logInfo.put("来源", request.getRemoteAddr());

        // 判断是否为文件上传请求（multipart/form-data）
        boolean isFileUpload = contentType != null && contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE);

        // 处理请求参数
        if (isFileUpload) {
            logInfo.put("参数", "[图片]");
        } else {
            // 普通请求参数
            Map<String, Object> params = new LinkedHashMap<>();

            // 处理URL参数
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                params.put(paramName, request.getParameter(paramName));
            }

            // 处理方法参数（可能包含@RequestBody）
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    if (arg != null) {
                        // 简单类型直接添加
                        if (isSimpleType(arg.getClass())) {
                            params.put(arg.getClass().getSimpleName(), arg.toString());
                        }
                        // 其他类型转换为JSON字符串
                        else {
                            params.put("requestBody", arg.toString());
                        }
                    }
                }
            }

            logInfo.put("参数", params);
        }

        // 打印请求日志
        logger.debug("请求详情: {}", logInfo);
    }


    // 处理响应日志
    private void handleResponseLog(HttpServletRequest request, Object result) {
        Map<String, Object> logInfo = new LinkedHashMap<>();

        // 响应内容（实际项目中可根据需要格式化）
        logInfo.put("结果", result != null ? result.toString() : null);

        logger.debug("方法返回详情:  {}", logInfo);
    }

    // 判断是否为简单类型
    private boolean isSimpleType(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == String.class ||
                clazz == Boolean.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class ||
                clazz == Integer.class ||
                clazz == Long.class ||
                clazz == Float.class ||
                clazz == Double.class ||
                clazz == Date.class;
    }

}
