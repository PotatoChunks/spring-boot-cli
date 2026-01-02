package top.potat.spring.common.exception;

/**
 * 错误断言
 */
public class ErrorAsserts {

    /**
     * 自定义错误
     */
    public static void fail(String message) {
        throw new AutoCustomizeException(message);
    }

    /**
     * 登录异常
     */
    public static void loginException(String message) {
        throw new AutoCustomizeException.LoginException(message);
    }

    /**
     * 权限异常
     */
    public static void permissionException(String message) {
        throw new AutoCustomizeException.PermissionException(message);
    }

    /**
     * 参数验证失败
     */
    public static void validateFailed() {
        throw new AutoCustomizeException.ParamException("参数验证失败");
    }

    /**
     * 数据不存在异常
     */
    public static void dataNotFount(String message) {
        throw new AutoCustomizeException.DataNotExistException(message);
    }

}
