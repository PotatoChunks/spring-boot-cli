package top.potat.spring.admin.config.router;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域配置
 * 已弃用
 */
@Deprecated
//@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 设置允许跨域的路径
                .allowedOriginPatterns("*") // 设置允许跨域请求的源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 设置允许的方法
                .allowedHeaders("*") // 设置允许的头
                .allowCredentials(true); // 是否允许证书（Cookie）
    }

}
