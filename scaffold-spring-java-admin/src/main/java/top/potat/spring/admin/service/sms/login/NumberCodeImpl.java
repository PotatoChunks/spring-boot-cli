package top.potat.spring.admin.service.sms.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.admin.model.AdminLoginReq;
import top.potat.spring.admin.service.sms.AdminLoginService;
import top.potat.spring.common.domain.Oauth2TokenDto;

/**
 * 验证码登录
 */
@Service
@RequiredArgsConstructor
public class NumberCodeImpl implements AdminLoginService {
    @Override
    public Oauth2TokenDto adminLogin(AdminLoginReq loginReq) {
        //验证码登录的逻辑
        return null;
    }
}
