package top.potat.spring.admin.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "404")
@RestController
public class Custom404ErrorController implements ErrorController {
    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handle404Error(HttpServletRequest request){
        WebRequest webRequest = new ServletWebRequest(request);
        ErrorAttributeOptions options = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE);
        Map<String, Object> errorAttributesMap = this.errorAttributes.getErrorAttributes(webRequest, options);

        Integer statusCode = (Integer) errorAttributesMap.get("status");
        String requestUri = (String) errorAttributesMap.get("path");

        if (statusCode != null && statusCode == HttpStatus.NOT_FOUND.value()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.NOT_FOUND.value());
            errorResponse.put("message", "你请求的资源未找到，请检查路径。");
            errorResponse.put("path", requestUri);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        // 非 404 错误不处理，交由全局异常拦截器处理
        return null;
    }
}
