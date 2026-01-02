package top.potat.spring.admin.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.potat.spring.common.exception.GlobalCommonExceptionHandler;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalAdminExceptionHandler extends GlobalCommonExceptionHandler {
}
