package top.potat.spring.common.utils.result;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * 通用返回对象
 */
@Data
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param resultCode 错误码
     */
    public static <T> CommonResult<T> failed(ResultCode resultCode) {
        return new CommonResult<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }
    /**
     * 失败返回结果
     * @param resultCode 错误码
     * @param message 错误信息
     */
    public static <T> CommonResult<T> failed(ResultCode resultCode,String message) {
        return new CommonResult<T>(resultCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    public static <T> CommonResult<T> failed(T data,String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 查询的数据不存在返回结果(表示查询到了不属于自己的数据)
     */
    public static <T> CommonResult<T> dataNotFount(String message) {
        return new CommonResult<T>(ResultCode.DATA_NOT_FOUNT.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 账号密码错误
     */
    public static <T> CommonResult<T> accountPasswordException(String message) {
        return new CommonResult<T>(ResultCode.AccountPasswaordException.getCode(), message, null);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
