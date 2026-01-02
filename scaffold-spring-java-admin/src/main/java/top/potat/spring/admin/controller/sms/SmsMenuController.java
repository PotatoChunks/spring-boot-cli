package top.potat.spring.admin.controller.sms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.potat.spring.admin.service.sms.RoleResourceAuthService;
import top.potat.spring.admin.service.sms.SmsMenuService;
import top.potat.spring.common.utils.result.CommonPage;
import top.potat.spring.common.utils.result.CommonResult;
import top.potat.spring.db.model.SmsMenu;
import top.potat.spring.db.models.sms.SerMenuModel;
import top.potat.spring.db.service.sms.SerMenuService;

import java.util.List;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/sms/menu")
@RequiredArgsConstructor
public class SmsMenuController {
    private final SerMenuService serMenuService;
    private final SmsMenuService smsMenuService;
    private final RoleResourceAuthService roleResourceAuthService;


    @ApiOperation("获取菜单列表")
    @PostMapping("/get/menuList")
    public CommonResult<CommonPage<SerMenuModel.MenuTreeModel>> getMenuList(@RequestBody(required = false) SerMenuModel.MenuListReqModel reqModel){
        if (reqModel == null)
            reqModel = new SerMenuModel.MenuListReqModel();

        List<SmsMenu> menuList = serMenuService.getMenuList(reqModel);

        CommonPage commonPage = CommonPage.restPage(menuList);

        List<SerMenuModel.MenuTreeModel> menuTreeModels = serMenuService.setMenuList(menuList);
        commonPage.setList(menuTreeModels);

        return CommonResult.success(commonPage);
    }


    @ApiOperation("获取菜单树")
    @GetMapping("/get/menuTree")
    public CommonResult<List<SerMenuModel.MenuTreeModel>> getMenuTree(){
        List<SerMenuModel.MenuTreeModel> menuTree = serMenuService.getMenuTree();
        return CommonResult.success(menuTree);
    }


    @ApiOperation("新增菜单")
    @PostMapping("/add/addMenu")
    public CommonResult<String> addMenu(@RequestBody SerMenuModel.AddUpdateMenuReqModel reqModel){
        reqModel.check();
        serMenuService.addMenu(reqModel.buildModel());
        //关联关系
        if(reqModel.getMenuResourceRelationModel() != null){
            reqModel.getMenuResourceRelationModel().setMenuId(reqModel.getId());
            smsMenuService.updateMenuResource(reqModel.getMenuResourceRelationModel());
        }
        //重新初始化权限
        roleResourceAuthService.initAuthority();
        return CommonResult.success("添加成功");
    }


    @ApiOperation("修改菜单")
    @PostMapping("/update/updateMenu")
    public CommonResult<String> updateMenu(@RequestBody SerMenuModel.AddUpdateMenuReqModel reqModel){
        reqModel.check();
        if(reqModel.getId() == null)
            return CommonResult.failed("请选择要修改的菜单");
        serMenuService.updateMenu(reqModel.buildModel());
        //删除菜单和资源接口的关系
        serMenuService.deleteMenuResource(reqModel.getId());
        //添加菜单和资源接口的关系
        if(reqModel.getMenuResourceRelationModel() != null){
            reqModel.getMenuResourceRelationModel().setMenuId(reqModel.getId());
            smsMenuService.updateMenuResource(reqModel.getMenuResourceRelationModel());
        }
        //重新初始化权限
        roleResourceAuthService.initAuthority();
        return CommonResult.success("修改成功");
    }


    @ApiOperation("删除菜单")
    @PostMapping("/delete/deleteMenu")
    public CommonResult<String> deleteMenu(@RequestBody SmsMenu smsMenu){
        if(smsMenu.getId() == null)
            return CommonResult.failed("请选择要删除的菜单");
        serMenuService.deleteMenu(smsMenu.getId());
        //重新初始化权限
        roleResourceAuthService.initAuthority();
        return CommonResult.success("删除成功");
    }


    @ApiOperation("获取菜单详情")
    @GetMapping("/get/menuDetail")
    public CommonResult<SerMenuModel.AddUpdateMenuReqModel> getMenuDetail(Long id){
        if(id == null)
            return CommonResult.failed("请选择要查看的菜单");
        SmsMenu smsMenu = serMenuService.getMenuDetail(id);
        SerMenuModel.AddUpdateMenuReqModel menuDetail = new SerMenuModel.AddUpdateMenuReqModel(smsMenu);
        //获取菜单关联资源数据
        List<Long> menuResource = serMenuService.getMenuResource(id);
        SerMenuModel.MenuResourceRelationModel menuResourceRelationModel = new SerMenuModel.MenuResourceRelationModel();
        menuResourceRelationModel.setResourceIdList(menuResource);
        menuResourceRelationModel.setMenuId(id);
        menuDetail.setMenuResourceRelationModel(menuResourceRelationModel);
        return CommonResult.success(menuDetail);
    }


    @ApiOperation("修改菜单启用状态")
    @PostMapping("/update/updateMenuStateType")
    public CommonResult<String> updateMenuStateType(@RequestBody SerMenuModel.AddUpdateMenuReqModel menuModel){
        if(menuModel.getId() == null)
            return CommonResult.failed("请选择要修改的菜单");
        if(menuModel.getMenuStatus() == null)
            return CommonResult.failed("请选择要修改的菜单状态");

        SmsMenu updateMenu = new SmsMenu();
        updateMenu.setId(menuModel.getId());
        updateMenu.setMenuStatus(menuModel.getMenuStatus());
        serMenuService.updateMenu(updateMenu);

        return CommonResult.success("修改成功");
    }


    @ApiOperation("菜单绑定资源Api")
    @PostMapping("/update/updateMenuResource")
    public CommonResult<String> updateMenuResource(@RequestBody SerMenuModel.MenuResourceRelationModel reqModel){
        reqModel.check();

        smsMenuService.updateMenuResource(reqModel);

        return CommonResult.success("修改成功");
    }


    @ApiOperation("获取当前用户拥有的菜单数据树")
    @GetMapping("/get/currentUserMenuTree")
    public CommonResult<List<SerMenuModel.MenuTreeModel>> getCurrentUserMenuTree(){
        List<SerMenuModel.MenuTreeModel> menuTreeModels = roleResourceAuthService.getCurrentUserMenuTree();
        return CommonResult.success(menuTreeModels);
    }


}
