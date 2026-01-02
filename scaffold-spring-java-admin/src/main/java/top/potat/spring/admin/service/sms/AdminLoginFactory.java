package top.potat.spring.admin.service.sms;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.potat.spring.admin.model.AdminLoginReq;
import top.potat.spring.admin.service.sms.login.NumberCodeImpl;
import top.potat.spring.admin.service.sms.login.UserNamePasswordImpl;
import top.potat.spring.common.config.JwtUtils;
import top.potat.spring.common.constant.CommonConstant;
import top.potat.spring.db.mapper.SmsAdminMapper;
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.common.enums.YesEnum;
import top.potat.spring.common.exception.AutoCustomizeException;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 管理员登录工厂
 */
@Component
@RequiredArgsConstructor
public class AdminLoginFactory {
    /**
     * 用户名密码登录逻辑
     */
    private final UserNamePasswordImpl userNamePasswordImpl;
    /**
     * 手机验证码登录逻辑
     */
    private final NumberCodeImpl numberCodeImpl;

    /**
     * jwt 工具类
     */
    private final JwtUtils jwtUtils;
    /**
     * 请求对象
     */
    private final HttpServletRequest requestBean;

    /**
     *  数据库操作Mapper
     */
    private final SmsAdminMapper smsAdminMapper;

    /**
     * 获取登录方式
     */
    public AdminLoginService getService(AdminLoginReq.LoginType loginType) {
        if (loginType == AdminLoginReq.LoginType.PASSWORD) {
            return userNamePasswordImpl;
        } else if (loginType == AdminLoginReq.LoginType.PHONE) {
            return numberCodeImpl;
        }
        throw new AutoCustomizeException("登录方式错误");
    }


    /**
     * 从请求头中获取token并获取信息
     */
    public SmsAdmin getAdminInfo(HttpServletRequest request){
        // 获取token
        String token = request.getHeader(CommonConstant.AUTHORIZATION);
        //解析 token
        Claims body = jwtUtils.extractAllClaims(token, CommonConstant.Token_ADMIN_SUBJECT);
        //获取用户id
        Object adminIdObj = body.get("id");
        if (StringUtils.isNull(adminIdObj) || StringUtils.isEmpty(String.valueOf(adminIdObj)))
            ErrorAsserts.loginException("暂未登录");
        Long adminId = Long.parseLong(adminIdObj.toString());
        //获取管理员用户信息
        SmsAdmin smsAdmin = smsAdminMapper.selectByPrimaryKey(adminId);
        //判断用户是否存在
        if (StringUtils.isNull(smsAdmin) || !Objects.equals(smsAdmin.getDeleteType(), YesEnum.NO.getCodeType()))
            ErrorAsserts.loginException("暂未登录");
        return smsAdmin;
    }
    public SmsAdmin getAdminInfo(){
        return getAdminInfo(requestBean);
    }



}
