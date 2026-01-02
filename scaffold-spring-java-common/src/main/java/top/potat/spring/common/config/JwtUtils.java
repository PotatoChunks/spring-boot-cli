package top.potat.spring.common.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.common.utils.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * JWT工具类
 */
public class JwtUtils {
    // 密钥
    private final String secret;
    // 过期时间 (秒)
    @Getter
    private final Long expiration;

    public JwtUtils(String secret, Long expiration){
        if(StringUtils.isEmpty(secret))
            throw new RuntimeException("JWT 密钥不能为空");
        if(expiration <= 0)
            throw new RuntimeException("JWT 过期时间不能小于等于0");
        this.secret = secret;
        this.expiration = expiration;
    }

    // 生成安全密钥（只需执行一次，然后保存生成的密钥）
    //使用方法
    //System.out.println( generateSecretKey() );
    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    // 生成签名密钥（安全方式）
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    /**
     * 创建生成token
     */
    public String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .subject(subject) // 设置相同的主题，以便于关联
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(),Jwts.SIG.HS256)
                .compact();
    }


    /**
     * 解析token
     */
    public Claims extractAllClaims(String token){
        if(StringUtils.isEmpty(token))
            ErrorAsserts.loginException("暂未登录");
        Claims payload = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        if(payload == null)
            ErrorAsserts.loginException("暂未登录");

        if(StringUtils.isEmpty(payload.getSubject()))
            ErrorAsserts.loginException("登录端错误");

        return payload;
    }
    public Claims extractAllClaims(String token,String subject){
        if(StringUtils.isEmpty(token))
            ErrorAsserts.loginException("暂未登录");
        Claims payload = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        if(payload == null)
            ErrorAsserts.loginException("暂未登录");

        if(StringUtils.isEmpty(payload.getSubject()) || !Objects.equals(payload.getSubject(), subject))
            ErrorAsserts.permissionException("登录端错误");

        //验证token是否过期
        //Expiration 是否早于 当前时间
        if(payload.getExpiration().before(new Date())){
            ErrorAsserts.loginException("登录已过期");
        }

        return payload;
    }


}
