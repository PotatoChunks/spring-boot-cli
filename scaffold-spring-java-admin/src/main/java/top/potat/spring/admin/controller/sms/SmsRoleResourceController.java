package top.potat.spring.admin.controller.sms;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.potat.spring.admin.service.sms.RoleResourceAuthService;
import top.potat.spring.admin.service.sms.SmsRoleResourceService;
import top.potat.spring.common.enums.sms.role.RoleTypeEnum;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.common.utils.result.CommonPage;
import top.potat.spring.common.utils.result.CommonResult;
import top.potat.spring.db.model.SmsResource;
import top.potat.spring.db.model.SmsResourceCategory;
import top.potat.spring.db.model.SmsRole;
import top.potat.spring.db.models.sms.RoleResourceModel;
import top.potat.spring.db.service.sms.SerRoleResourceService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "角色资源Api管理")
@RestController
@RequestMapping("/sms/roleResource")
@RequiredArgsConstructor
public class SmsRoleResourceController {
    private final SerRoleResourceService serRoleResourceService;
    private final SmsRoleResourceService smsRoleResourceService;

    private final RoleResourceAuthService roleResourceAuthService;


    @ApiOperation("获取角色列表")
    @PostMapping("/get/roleList")
    public CommonResult<CommonPage<RoleResourceModel.RoleModel>> getRoleList(@RequestBody RoleResourceModel.RoleListReqModel reqModel){
        List<SmsRole> roleList = serRoleResourceService.getRoleList(reqModel);
        CommonPage commonPage = CommonPage.restPage(roleList);
        List<RoleResourceModel.RoleModel> roleModels = serRoleResourceService.setRoleList(roleList);
        commonPage.setList(roleModels);
        return CommonResult.success(commonPage);
    }


    @ApiOperation("获取角色类型列表")
    @GetMapping("/get/roleTypeList")
    public CommonResult<List<RoleResourceModel.RoleTypeModel>> getRoleTypeList(){
        RoleTypeEnum[] values = RoleTypeEnum.values();
        List<RoleResourceModel.RoleTypeModel> roleModels = Arrays.stream(values).map(roleTypeEnum -> {
            RoleResourceModel.RoleTypeModel roleTypeModel = new RoleResourceModel.RoleTypeModel();
            roleTypeModel.setValue(roleTypeEnum.getCodeType());
            roleTypeModel.setLabel(roleTypeEnum.getCodeName());
            return roleTypeModel;
        }).collect(Collectors.toList());
        return CommonResult.success(roleModels);
    }


    @ApiOperation("修改角色启用禁用状态")
    @PostMapping("/update/updateRoleStateType")
    public CommonResult<String> updateRoleStateType(@RequestBody RoleResourceModel.RoleModel roleModel){
        if(roleModel.getId() == null)
            return CommonResult.validateFailed("角色ID不能为空");
        if(roleModel.getStatus() == null || roleModel.getStatus() <= 0 || roleModel.getStatus() > 2)
            roleModel.setStatus(1);

        SmsRole updateRole = new SmsRole();
        updateRole.setId(roleModel.getId());
        updateRole.setStatus(roleModel.getStatus());
        serRoleResourceService.updateRole(updateRole);

        return CommonResult.success("修改成功");
    }


    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色ID",required = true)})
    @ApiOperation("获取角色详情")
    @GetMapping("/get/roleDetail")
    public CommonResult<RoleResourceModel.RoleModel> getRoleDetail(Long id){
        if(id == null)
            return CommonResult.validateFailed("角色ID不能为空");
        RoleResourceModel.RoleModel smsRole = serRoleResourceService.getRoleDetail(id);
        if(smsRole == null)
            return CommonResult.dataNotFount("角色不存在");

        List<Long> roleMenuIdList = serRoleResourceService.getRoleMenuIdList(id);
        smsRole.setMenuIdList(roleMenuIdList);

        return CommonResult.success(smsRole);
    }


    @ApiOperation("获取资源接口列表")
    @PostMapping("/get/resourceApiList")
    public CommonResult<CommonPage<RoleResourceModel.ResourceModel>> getResourceApiList(@RequestBody(required = false) RoleResourceModel.ResourceListReqModel reqModel){
        if(reqModel == null)
            reqModel = new RoleResourceModel.ResourceListReqModel();
        List<SmsResource> resourceApiList = serRoleResourceService.getResourceList(reqModel);
        CommonPage restPage = CommonPage.restPage(resourceApiList);
        PageHelper.clearPage();
        List<RoleResourceModel.ResourceModel> resourceModels = serRoleResourceService.setResourceList(resourceApiList);
        restPage.setList(resourceModels);
        return CommonResult.success(restPage);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "资源接口ID",required = true)})
    @ApiOperation("获取资源接口详情")
    @GetMapping("/get/resourceApiDetail")
    public CommonResult<RoleResourceModel.ResourceModel> getResourceApiDetail(Long id){
        if(id == null)
            return CommonResult.validateFailed("资源接口 ID不能为空");
        RoleResourceModel.ResourceModel resourceModel = serRoleResourceService.getResourceDetail(id);
        return CommonResult.success(resourceModel);
    }


    @ApiOperation("获取资源接口分类列表")
    @PostMapping("/get/resourceCategoryList")
    public CommonResult<CommonPage<RoleResourceModel.ResourceCategoryModel>> getResourceApiCategoryList(@RequestBody RoleResourceModel.ResourceCategoryListReqModel reqModel){
        List<SmsResourceCategory> resourceApiCategoryList = serRoleResourceService.getResourceCategoryList(reqModel);
        CommonPage commonPage = CommonPage.restPage(resourceApiCategoryList);
        List<RoleResourceModel.ResourceCategoryModel> resourceCategoryList = serRoleResourceService.setResourceCategoryList(resourceApiCategoryList);
        commonPage.setList(resourceCategoryList);
        return CommonResult.success(commonPage);
    }


    @ApiOperation("获取资源分类和资源接口数据对应关系")
    @GetMapping("/get/resourceCategoryAndResourceApiRelation")
    public CommonResult<List<RoleResourceModel.ResourceCategoryAndResourceApiRelationModel>> getResourceCategoryAndResourceApiRelation(){
        List<RoleResourceModel.ResourceCategoryAndResourceApiRelationModel> relationModelList = serRoleResourceService.getResourceCategoryAndResourceApiRelation();
        return CommonResult.success(relationModelList);
    }


    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "资源接口分类ID",required = true)})
    @ApiOperation("获取资源接口分类详情")
    @GetMapping("/get/resourceCategoryDetail")
    public CommonResult<SmsResourceCategory> getResourceApiCategoryDetail(Long id){
        if(id == null)
            return CommonResult.validateFailed("资源分类ID不能为空");
        SmsResourceCategory smsResourceCategory = serRoleResourceService.getResourceCategoryDetail(id);
        return CommonResult.success(smsResourceCategory);
    }


    @ApiOperation("添加资源分类")
    @PostMapping("/add/addResourceCategory")
    public CommonResult<String> addResourceCategory(@RequestBody SmsResourceCategory smsResourceCategory){
        if(StringUtils.isEmpty(smsResourceCategory.getName()))
            return CommonResult.validateFailed("资源分类名称不能为空");
        serRoleResourceService.insertResourceCategory(smsResourceCategory);
        return CommonResult.success("添加成功");
    }

    @ApiOperation("修改资源分类")
    @PostMapping("/update/updateResourceCategory")
    public CommonResult<String> updateResourceCategory(@RequestBody SmsResourceCategory smsResourceCategory){
        if(smsResourceCategory.getId() == null)
            return CommonResult.validateFailed("资源分类ID不能为空");
        if(StringUtils.isEmpty(smsResourceCategory.getName()))
            return CommonResult.validateFailed("资源分类名称不能为空");
        serRoleResourceService.updateResourceCategory(smsResourceCategory);
        return CommonResult.success("修改成功");
    }


    @ApiOperation("添加资源接口")
    @PostMapping("/add/addResourceApi")
    public CommonResult<String> addResourceApi(@RequestBody RoleResourceModel.ResourceModel resourceModel){
        resourceModel.check();
        serRoleResourceService.insertResource(resourceModel);
        return CommonResult.success("添加成功");
    }

    @ApiOperation("修改资源接口")
    @PostMapping("/update/updateResourceApi")
    public CommonResult<String> updateResourceApi(@RequestBody RoleResourceModel.ResourceModel resourceModel){
        resourceModel.check();
        serRoleResourceService.updateResource(resourceModel);
        return CommonResult.success("修改成功");
    }


    @ApiOperation("新增角色")
    @PostMapping("/add/addRole")
    public CommonResult<String> addRole(@RequestBody RoleResourceModel.AddRoleReqModel roleReqModel){
        roleReqModel.check();
        serRoleResourceService.insertRole(roleReqModel);
        smsRoleResourceService.insertRoleMenuIdList(roleReqModel.getId(),roleReqModel.getMenuIdList());

        //重新生成权限
        roleResourceAuthService.initAuthority();
        return CommonResult.success("添加成功");
    }

    @ApiOperation("修改角色")
    @PostMapping("/update/updateRole")
    public CommonResult<String> updateRole(@RequestBody RoleResourceModel.AddRoleReqModel roleReqModel){
        roleReqModel.check();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
        }
        if(roleReqModel.getId() == null) return CommonResult.failed("角色ID不能为空");
        SmsRole smsRole = roleReqModel.buildModel();
        serRoleResourceService.updateRole(smsRole);
        serRoleResourceService.deleteRoleMenuList(roleReqModel.getId());
        smsRoleResourceService.insertRoleMenuIdList(roleReqModel.getId(),roleReqModel.getMenuIdList());

        //重新生成权限
        roleResourceAuthService.initAuthority();
        return CommonResult.success("修改成功");
    }


}
