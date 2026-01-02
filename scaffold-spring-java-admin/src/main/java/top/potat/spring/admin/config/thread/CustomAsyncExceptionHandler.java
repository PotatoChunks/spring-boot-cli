package top.potat.spring.admin.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        // 自定义异常处理逻辑
        log.error("异常的方法名称是-> {}, 参数是->: {}", method.getName(), Arrays.toString(params));
        log.error("异常信息是->: ", ex);
    }
}
