package top.potat.spring.admin.service.sms.login;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.admin.model.AdminLoginReq;
import top.potat.spring.admin.service.sms.AdminLoginService;
import top.potat.spring.common.config.JwtUtils;
import top.potat.spring.common.constant.CommonConstant;
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.common.domain.Oauth2TokenDto;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.db.service.sms.SerAdminService;
import top.potat.spring.common.utils.encryption.PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户名密码登录
 */
@Service
@RequiredArgsConstructor
public class UserNamePasswordImpl implements AdminLoginService {
    private final SerAdminService serAdminService;
    private final JwtUtils jwtUtils;
    @Override
    public Oauth2TokenDto adminLogin(AdminLoginReq loginReq) {
        List<SmsAdmin> adminList = serAdminService.getAdminListByLoginName(loginReq.getUserName());
        if(CollectionUtil.isEmpty(adminList))
            ErrorAsserts.dataNotFount("登录账户不存在");
        SmsAdmin smsAdmin = adminList.get(0);
        if(!PasswordEncoder.verify(loginReq.getPassword(), smsAdmin.getPassword()))
            ErrorAsserts.fail("密码错误");

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", smsAdmin.getId());
        String token = jwtUtils.createToken(claims, CommonConstant.Token_ADMIN_SUBJECT);
        return Oauth2TokenDto.build(token, jwtUtils.getExpiration());
    }
}
