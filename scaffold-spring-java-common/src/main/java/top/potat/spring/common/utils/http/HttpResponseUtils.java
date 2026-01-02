package top.potat.spring.common.utils.http;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Http响应工具类
 */
@Slf4j
public class HttpResponseUtils {

    /**
     * 设置响应状态码和文本返回值（纯文本格式）
     * @param response HttpServletResponse 对象
     * @param status 响应状态码（如 200/400/500）
     * @param content 文本返回内容（如 "success"、"参数错误"）
     * @throws IOException 输出响应时发生 IO 异常
     */
    public static void writeText(HttpServletResponse response, int status, String content) throws IOException {
        // 设置响应状态码
        response.setStatus(status);
        // 设置内容类型（纯文本 + UTF-8 编码，避免中文乱码）
        response.setContentType("text/plain;charset=" + StandardCharsets.UTF_8.name());
        // 输出响应内容
        response.getWriter().write(content);
        response.getWriter().flush();
    }

    /**
     * 设置响应状态码和 JSON 返回值（自动将对象转为 JSON 字符串）
     * @param response HttpServletResponse 对象
     * @param status 响应状态码（如 200/400/500）
     * @param data 要返回的对象（会被转为 JSON，支持 Map、实体类、集合等）
     * @throws IOException 输出响应或 JSON 序列化时发生异常
     */
    public static void writeJson(HttpServletResponse response, int status, Object data) throws IOException {
        // 设置响应状态码
        response.setStatus(status);
        // 设置内容类型（JSON + UTF-8 编码）
        response.setContentType("application/json;charset=" + StandardCharsets.UTF_8.name());
        // 将对象转为 JSON 字符串并输出
        String json = JSONObject.toJSONString(data);
        response.getWriter().write(json);
        response.getWriter().flush();
    }

    /**
     * 简化方法：返回成功状态（200）和文本内容
     */
    public static void successText(HttpServletResponse response, String content) throws IOException {
        writeText(response, HttpServletResponse.SC_OK, content);
    }

    /**
     * 简化方法：返回成功状态（200）和 JSON 数据
     */
    public static void successJson(HttpServletResponse response, Object data) throws IOException {
        writeJson(response, HttpServletResponse.SC_OK, data);
    }

    /**
     * 简化方法：返回客户端错误（400）和文本提示
     */
    public static void badRequestText(HttpServletResponse response, String errorMsg) throws IOException {
        writeText(response, HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }

    /**
     * 简化方法：返回服务器错误（500）和文本提示
     */
    public static void serverErrorText(HttpServletResponse response, String errorMsg) throws IOException {
        writeText(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg);
    }


    /**
     * 无返回异常的文本响应方法（内部捕获异常，适合无需处理异常的场景）
     * @param response HttpServletResponse 对象
     * @param status 响应状态码
     * @param content 文本内容
     */
    public static void writeTextQuietly(HttpServletResponse response, int status, String content) {
        try {
            // 复用原有 writeText 方法的逻辑
            writeText(response, status, content);
        } catch (IOException e) {
            // 异常静默处理（或记录日志）
            log.error("文本响应输出失败", e);
        }
    }

    /**
     * 无返回异常的 JSON 响应方法（内部捕获异常）
     * @param response HttpServletResponse 对象
     * @param status 响应状态码
     * @param data 要转为 JSON 的对象
     */
    public static void writeJsonQuietly(HttpServletResponse response, int status, Object data) {
        try {
            // 复用原有 writeJson 方法的逻辑
            writeJson(response, status, data);
        } catch (IOException e) {
            log.error("JSON 响应输出失败", e);
        }
    }

    /**
     * 简化：无异常的成功文本响应
     */
    public static void successTextQuietly(HttpServletResponse response, String content) {
        writeTextQuietly(response, HttpServletResponse.SC_OK, content);
    }

    /**
     * 简化：无异常的成功 JSON 响应
     */
    public static void successJsonQuietly(HttpServletResponse response, Object data) {
        writeJsonQuietly(response, HttpServletResponse.SC_OK, data);
    }

    /**
     * 简化：无异常的客户端错误响应
     */
    public static void badRequestTextQuietly(HttpServletResponse response, String errorMsg) {
        writeTextQuietly(response, HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }

}
