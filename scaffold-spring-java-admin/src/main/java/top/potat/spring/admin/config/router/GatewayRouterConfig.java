package top.potat.spring.admin.config.router;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 路由配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ignore")
public class GatewayRouterConfig {

    //不需要token访问的路径
    private List<String> noLog;

    //要token但是不用权限的路径
    private List<String> whiteList;

}
