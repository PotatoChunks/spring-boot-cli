package top.potat.spring.admin.service.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.admin.utils.AuthUtils;
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.db.models.sms.SerMenuModel;
import top.potat.spring.db.service.sms.SerMenuService;
import top.potat.spring.db.service.sms.SerRoleResourceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleResourceAuthService {
    private final AuthUtils authUtils;

    private final SerRoleResourceService serRoleResourceService;
    private final SerMenuService serMenuService;
    private final AdminLoginFactory adminLoginFactory;


    /**
     * 初始化权限
     */
    public void initAuthority() {
        authUtils.initAuthority(serRoleResourceService.getRoleResourceList(), serRoleResourceService.getMemberRoleList(1));
    }


    /**
     * 获取当前用户的菜单树数据
     */
    public List<SerMenuModel.MenuTreeModel> getCurrentUserMenuTree() {
        SmsAdmin adminInfo = adminLoginFactory.getAdminInfo();

        return serMenuService.getMenuTreeByAdminId(adminInfo.getId());
    }

}
