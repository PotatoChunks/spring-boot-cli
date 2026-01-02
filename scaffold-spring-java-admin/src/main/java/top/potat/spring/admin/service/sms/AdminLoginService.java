package top.potat.spring.admin.service.sms;

import top.potat.spring.admin.model.AdminLoginReq;
import top.potat.spring.common.domain.Oauth2TokenDto;

/**
 * 管理员登录抽象接口
 * 下方有不同的实现方法
 * 可以有 账密登录或者验证码登录
 * @author Potato
 */
public interface AdminLoginService {

    /**
     * 管理员登录 创建token
     */
    Oauth2TokenDto adminLogin(AdminLoginReq loginReq);

}
