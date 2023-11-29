package com.pt.config.handler;

import com.pt.api.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理机制
 */
@ControllerAdvice
public class SystemExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult handleException(Exception e){
        e.printStackTrace();
        return CommonResult.failed("系统发生错误");
    }

}
