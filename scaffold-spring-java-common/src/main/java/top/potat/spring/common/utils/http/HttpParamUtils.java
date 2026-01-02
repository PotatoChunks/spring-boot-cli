package top.potat.spring.common.utils.http;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数工具类
 */
@Slf4j
public class HttpParamUtils {

    /**
     * 获取 POST Form 表单（application/x-www-form-urlencoded）的所有参数
     * @param request HttpServletRequest 对象
     * @return 包含所有表单参数的 Map<String, Object>，键为参数名，值为参数值
     */
    public static Map<String, Object> getPostFormParams(HttpServletRequest request) {
        // 1. 校验请求方式是否为 POST
        String method = request.getMethod();
        if (!"POST".equalsIgnoreCase(method)) {
            return null;
        }
        // 2. 校验内容类型是否为 Form 表单（application/x-www-form-urlencoded）
        String contentType = request.getContentType();
        if (contentType == null || !contentType.contains("application/x-www-form-urlencoded")) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        // 遍历所有参数名，获取对应的值
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            // 注意：getParameterValues() 可获取多值参数（如 checkbox），这里简化为单值，如需多值可修改为 Object[]
            String paramValue = request.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }
        return paramMap;
    }

    /**
     * 重载方法：支持多值参数（如 checkbox 多选），值为 String[]
     * 适用于需要获取同一参数多个值的场景
     */
    public static Map<String, Object> getPostFormParamsWithMultiValue(HttpServletRequest request) {
        // 1. 校验请求方式是否为 POST
        String method = request.getMethod();
        if (!"POST".equalsIgnoreCase(method)) {
            return null;
        }
        // 2. 校验内容类型是否为 Form 表单（application/x-www-form-urlencoded）
        String contentType = request.getContentType();
        if (contentType == null || !contentType.contains("application/x-www-form-urlencoded")) {
            return null;
        }

        // 2. 提取多值参数（如 ?hobby=game&hobby=music 会被解析为 String[] {"game", "music"}）
        Map<String, Object> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            paramMap.put(paramName, paramValues);
        }

        return paramMap;
    }


    /**
     * 获取 接口中通过 URL 查询参数（Params）传递的所有参数
     * （即 URL 中 ? 后面的 key=value 形式参数）
     *
     * @param request HttpServletRequest 对象
     * @return 包含所有查询参数的 Map<String, Object>，键为参数名，值为参数值（单值为 String，多值为 String[]）
     */
    public static Map<String, Object> getUrlParams(HttpServletRequest request) {

        // 2. 提取 URL 中的所有查询参数
        Map<String, Object> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            // 获取该参数的所有值（支持多值参数，如 ?ids=1&ids=2）
            String[] paramValues = request.getParameterValues(paramName);

            // 处理单值/多值参数：单值直接存 String，多值存 String[]
            if (paramValues != null && paramValues.length > 0) {
                if (paramValues.length == 1) {
                    paramMap.put(paramName, paramValues[0]);
                } else {
                    paramMap.put(paramName, paramValues);
                }
            }
        }

        return paramMap;
    }


    /**
     * 获取 POST 请求中 JSON 格式（application/json）的所有参数
     * @param request HttpServletRequest 对象
     * @return String
     */
    public static String getPostJsonParams(HttpServletRequest request) {
        // 1. 校验请求方式是否为 POST
        String method = request.getMethod();
        if (!"POST".equalsIgnoreCase(method)) {
            return null;
        }

        // 2. 校验 Content-Type 是否为 JSON
        String contentType = request.getContentType();
        if (contentType == null || !contentType.contains("application/json")) {
            return null;
        }

        // 3. 读取请求体中的 JSON 字符串
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonSb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonSb.append(line);
            }

            return jsonSb.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
