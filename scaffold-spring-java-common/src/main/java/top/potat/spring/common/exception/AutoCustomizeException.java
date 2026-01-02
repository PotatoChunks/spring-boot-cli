package top.potat.spring.common.exception;

/**
 * 自定义异常
 */
public class AutoCustomizeException extends RuntimeException {
    /**
     * 自定义异常
     */
    public AutoCustomizeException(String message) {
        super(message);
    }


    /**
     * 登录异常
     */
    public static class LoginException extends AutoCustomizeException {
        public LoginException(String message) {
            super(message);
        }
    }

    /**
     * 权限异常
     */
    public static class PermissionException extends AutoCustomizeException {
        public PermissionException(String message) {
            super(message);
        }
    }

    /**
     * 参数异常
     */
    public static class ParamException extends AutoCustomizeException {
        public ParamException(String message) {
            super(message);
        }
    }

    /**
     * 数据不存在异常
     */
    public static class DataNotExistException extends AutoCustomizeException {
        public DataNotExistException(String message) {
            super(message);
        }
    }

}
