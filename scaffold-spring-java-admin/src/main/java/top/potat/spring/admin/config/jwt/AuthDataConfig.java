package top.potat.spring.admin.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import top.potat.spring.admin.utils.AuthUtils;
import top.potat.spring.db.models.sms.RoleResourceModel;
import top.potat.spring.db.service.sms.SerRoleResourceService;

import java.util.List;

/**
 * 认证数据配置
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthDataConfig {
    private final SerRoleResourceService serRoleResourceService;



    @Bean
    @Scope("singleton")  // 显式声明单例（默认）
    @Lazy(false)  // 显式声明不延迟加载（默认）
    public AuthUtils initAuthData() {
        log.debug("初始化权限数据");
        AuthUtils authUtils = new AuthUtils();

        List<RoleResourceModel.RoleResourceRelationModel> roleResourceList = serRoleResourceService.getRoleResourceList();
        List<RoleResourceModel.UserRoleRelationModel> memberRoleList = serRoleResourceService.getMemberRoleList(2);

        authUtils.initAuthority(roleResourceList,memberRoleList);

        return authUtils;
    }

}
