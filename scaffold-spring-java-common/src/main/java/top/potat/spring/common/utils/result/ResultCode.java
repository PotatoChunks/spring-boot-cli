package top.potat.spring.common.utils.result;

import lombok.Getter;

/**
 * 枚举了一些常用API操作码
 */
@Getter
public enum ResultCode {
    DATA_NOT_FOUNT(1001, "数据不存在"),
    SUCCESS(200, "操作成功"),
    FAILED(5000, "操作失败"),
    VALIDATE_FAILED(4100, "参数检验失败"),
    UNAUTHORIZED(4001, "暂未登录或登录已经过期"),
    IPLimitExceeded(4002, "IP已超出访问限制"),
    AccountPasswaordException(4005, "账号密码错误"),
    FORBIDDEN(4003, "没有相关权限");
    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
