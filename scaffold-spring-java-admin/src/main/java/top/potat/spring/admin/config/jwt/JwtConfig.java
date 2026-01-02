package top.potat.spring.admin.config.jwt;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.potat.spring.common.config.JwtUtils;

/**
 * JWT配置
 */
@Slf4j
@Configuration
public class JwtConfig {

    @Getter
    @Value("${jwt.secret:1234567890}")
    private String secret;

    @Getter
    @Value("${jwt.expiration:86400}")
    private Long expiration;


    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(secret, expiration);
    }

}
