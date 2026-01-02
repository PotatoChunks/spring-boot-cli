package top.potat.spring.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 管理员登录参数
 */
@Data
public class AdminLoginReq {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String password;

    /**
     * 登录方式枚举
     */
    public enum LoginType {
        /**
         * 账号密码登录
         */
        PASSWORD,
        /**
         * 手机验证码登录
         */
        PHONE
    }

}
