package top.potat.spring.common.exception;

import io.jsonwebtoken.MalformedJwtException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import top.potat.spring.common.utils.result.CommonResult;
import top.potat.spring.common.utils.result.ResultCode;

import javax.servlet.http.HttpServletRequest;

public class GlobalCommonExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalCommonExceptionHandler.class);


    /**
     * 数据类型不匹配
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        //logger.error("数据类型不匹配", e);
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }


    /**
     * 参数错误
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public CommonResult<String> handleMethodArgumentTypeMismatchException(Exception e) {
        logger.error("参数错误->{}", e.getMessage());
        return CommonResult.validateFailed();
    }


    /**
     * 文件大小超出
     */
    @ExceptionHandler(MultipartException.class)
    public CommonResult<String> handleMultipartException(MultipartException e) {
        // 这里可以根据实际情况调整返回的信息
        String errorMessage = "文件大小超出允许范围。请确保您的文件不超过10M";
        return CommonResult.validateFailed(errorMessage);
    }

    /**
     * 没有识别到图片
     */
    @ExceptionHandler(FileUploadException.class)
    public CommonResult<String> fileUploadException(FileUploadException e){
        return CommonResult.validateFailed("没有识别到图片");
    }


    /**
     * 自定义错误
     */
    @ExceptionHandler(AutoCustomizeException.class)
    public CommonResult<String> handleException(AutoCustomizeException e) {
        return CommonResult.failed(e.getMessage());
    }


    /**
     * 登录异常
     */
    @ExceptionHandler(AutoCustomizeException.LoginException.class)
    public CommonResult<String> handleException(AutoCustomizeException.LoginException e) {
        return CommonResult.unauthorized(e.getMessage());
    }

    /**
     * 登录异常
     */
    @ExceptionHandler({MalformedJwtException.class})
    public CommonResult<String> handleException(Exception e) {
        return CommonResult.unauthorized("登录过期");
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(AutoCustomizeException.PermissionException.class)
    public CommonResult<String> handleException(AutoCustomizeException.PermissionException e) {
        return CommonResult.forbidden("权限不足");
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(AutoCustomizeException.ParamException.class)
    public CommonResult<String> handleException(AutoCustomizeException.ParamException e) {
        return CommonResult.validateFailed(e.getMessage());
    }

    /**
     * 数据不存在异常
     */
    @ExceptionHandler(AutoCustomizeException.DataNotExistException.class)
    public CommonResult<String> handleException(AutoCustomizeException.DataNotExistException e) {
        return CommonResult.dataNotFount(e.getMessage());
    }


    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<String> handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        logger.error("请求地址'{}',发生系统异常.", requestURI, e);
        return CommonResult.failed("后台发生错误");
    }

}
