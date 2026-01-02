package top.potat.spring.admin.controller.sms;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.potat.spring.admin.model.AdminLoginReq;
import top.potat.spring.admin.service.sms.AdminLoginFactory;
import top.potat.spring.admin.service.sms.RoleResourceAuthService;
import top.potat.spring.admin.service.sms.SmsAdminService;
import top.potat.spring.common.domain.Oauth2TokenDto;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.common.utils.result.CommonPage;
import top.potat.spring.common.utils.result.CommonResult;
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.db.model.SmsRole;
import top.potat.spring.db.models.sms.AdminModel;
import top.potat.spring.db.service.sms.SerAdminService;
import top.potat.spring.db.service.sms.SerRoleResourceService;

import java.util.List;

@Api(tags = "管理员管理")
@RestController
@RequestMapping("/sms/admin")
@RequiredArgsConstructor
public class SmsAdminController {
    private final AdminLoginFactory adminLoginFactory;
    private final SmsAdminService smsAdminService;
    private final SerAdminService serAdminService;
    private final SerRoleResourceService serRoleResourceService;
    private final RoleResourceAuthService roleResourceAuthService;


    @ApiOperation("管理员登录")
    @PostMapping("/login/adminLogin")
    public CommonResult<Oauth2TokenDto> adminLogin(@RequestBody AdminLoginReq loginReq){
        //账密登录
        Oauth2TokenDto tokenDto = adminLoginFactory.getService(AdminLoginReq.LoginType.PASSWORD).adminLogin(loginReq);

        return CommonResult.success(tokenDto);
    }


    @ApiOperation("管理员登出")
    @GetMapping("/get/adminLogout")
    public CommonResult<String> adminLogout(){

        return CommonResult.success(null);
    }


    @ApiOperation("获取管理员信息")
    @GetMapping("/get/getAdminInfo")
    public CommonResult<AdminModel.AdminInfoModel> getAdminInfo(){
        SmsAdmin adminInfo = adminLoginFactory.getAdminInfo();
        AdminModel.AdminInfoModel adminInfoModel = new AdminModel.AdminInfoModel(adminInfo);

        adminInfoModel.setPassword("***");

        return CommonResult.success(adminInfoModel);
    }



    @ApiOperation("获取管理员列表")
    @PostMapping("/get/getAdminList")
    public CommonResult<CommonPage<AdminModel.AdminListModel>> getAdminList(@RequestBody(required = false) AdminModel.ListParamModel paramModel){
        if(paramModel == null)
            paramModel = new AdminModel.ListParamModel();
        List<SmsAdmin> adminList = serAdminService.getAdminList(paramModel);


        adminList.stream().forEach(admin -> {
            admin.setPassword("***");
        });

        CommonPage commonPage = CommonPage.restPage(adminList);

        List<AdminModel.AdminListModel> adminListModels = serAdminService.setAdminListModel(adminList);

        commonPage.setList(adminListModels);

        return CommonResult.success(commonPage);
    }


    @ApiOperation("删除管理员")
    @GetMapping("/delete/deleteAdmin")
    public CommonResult<String> deleteAdmin(Long id){
        serAdminService.deleteAdmin(id);

        //初始化权限
        roleResourceAuthService.initAuthority();

        return CommonResult.success("success");
    }


    @ApiImplicitParams({@ApiImplicitParam(name = "loginName", value = "登录账号",required = true)})
    @ApiOperation("登录账号是否已存在")
    @GetMapping("/get/isLoginNameExist")
    public CommonResult<Boolean> isLoginNameExist(String loginName){
        if(StringUtils.isEmpty(loginName))
            return CommonResult.failed("登录账号不能为空");
        List<SmsAdmin> adminList = serAdminService.getAdminListByLoginName(loginName);

        //false->不存在;true->存在
        return CommonResult.success(CollectionUtil.isNotEmpty(adminList));
    }


    @ApiOperation("添加管理员")
    @PostMapping("/add/addAdmin")
    public CommonResult<String> addAdmin(@RequestBody AdminModel.AdminInfoModel adminInfoModel){
        adminInfoModel.check();
        adminInfoModel.setDeleteType(null);
        adminInfoModel.setDeleteTime(null);

        //查询是否有类似的的登陆账号
        List<SmsAdmin> adminList = serAdminService.getAdminListByLoginName(adminInfoModel.getLoginName());
        if (CollectionUtil.isNotEmpty(adminList)) {
            return CommonResult.failed("登录账号已存在");
        }

        serAdminService.insertAdmin(adminInfoModel);
        if (CollectionUtil.isNotEmpty(adminInfoModel.getRoleIdList())) {
            //添加角色
            smsAdminService.addAdminRole(adminInfoModel);
        }
        //初始化权限
        roleResourceAuthService.initAuthority();
        return CommonResult.success("success");
    }


    @ApiOperation("修改管理员")
    @PostMapping("/update/updateAdmin")
    public CommonResult<String> updateAdmin(@RequestBody AdminModel.UpdateParamModel adminInfoModel) {
        adminInfoModel.check();
        serAdminService.updateAdminDetail(adminInfoModel);
        //添加角色
        smsAdminService.addAdminRole(adminInfoModel);

        //初始化权限
        roleResourceAuthService.initAuthority();

        return CommonResult.success("success");
    }


    @ApiOperation("管理员详情")
    @GetMapping("/get/getAdminDetail")
    public CommonResult<AdminModel.AdminInfoModel> getAdminDetail(Long id) {
        if (id == null) return CommonResult.failed("管理员ID不能为空");
        SmsAdmin smsAdmin = serAdminService.getById(id);
        smsAdmin.setPassword("***");
        List<SmsRole> smsRoleList = serRoleResourceService.getRoleListByAdminMemberId(id);
        AdminModel.AdminInfoModel adminInfoModel = new AdminModel.AdminInfoModel(smsAdmin);

        adminInfoModel.setRoleIdList(smsRoleList);
        return CommonResult.success(adminInfoModel);
    }


    @ApiOperation("修改管理员启用状态")
    @PostMapping("/update/updateAdminDisableType")
    public CommonResult<String> updateAdminDisableType(@RequestBody AdminModel.AdminInfoModel adminInfoModel) {
        if(adminInfoModel.getId() == null)
            return CommonResult.failed("管理员ID不能为空");
        if(adminInfoModel.getDisableType() == null)
            adminInfoModel.setDeleteType(1);
        SmsAdmin updateAdmin = new SmsAdmin();
        updateAdmin.setId(adminInfoModel.getId());
        updateAdmin.setDisableType(adminInfoModel.getDisableType());
        serAdminService.updateAdmin(updateAdmin);
        return CommonResult.success("success");
    }

}
