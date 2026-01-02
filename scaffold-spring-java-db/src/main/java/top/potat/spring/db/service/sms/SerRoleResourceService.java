package top.potat.spring.db.service.sms;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.common.enums.YesEnum;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.db.mapper.*;
import top.potat.spring.db.model.*;
import top.potat.spring.db.models.sms.RoleResourceModel;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SerRoleResourceService {
    private final SmsRoleMapper smsRoleMapper;
    private final SmsResourceMapper smsResourceMapper;
    private final SmsResourceCategoryMapper smsResourceCategoryMapper;
    private final SmsMemberRoleRelationMapper smsMemberRoleRelationMapper;
    private final SmsRoleMenuRelationMapper smsRoleMenuRelationMapper;
    private final SmsMenuMapper smsMenuMapper;
    private final SmsMenuResourceRelationMapper smsMenuResourceRelationMapper;


    /**
     * 获取角色列表
     */
    public List<SmsRole> getRoleList(RoleResourceModel.RoleListReqModel reqModel){
        SmsRoleExample smsRoleExample = new SmsRoleExample();
        SmsRoleExample.Criteria criteria = smsRoleExample.createCriteria().andDeleteTypeEqualTo(YesEnum.NO.getCodeType());

        if(StringUtils.isNotEmpty(reqModel.getName()))
            criteria.andNameLike("%" + reqModel.getName() + "%");


        smsRoleExample.setOrderByClause("sort asc, create_time desc, id desc");
        if(reqModel.getPageNum() != null && reqModel.getPageSize() != null)
            PageHelper.startPage(reqModel.getPageNum(), reqModel.getPageSize());
        return smsRoleMapper.selectByExample(smsRoleExample);
    }

    /**
     * 封装角色列表Model
     */
    public List<RoleResourceModel.RoleModel> setRoleList(List<SmsRole> roleList){
        if(CollectionUtil.isEmpty(roleList))
            return Collections.emptyList();
        return roleList.stream().map(role -> {
            RoleResourceModel.RoleModel roleModel = new RoleResourceModel.RoleModel(role);

            return roleModel;
        }).collect(Collectors.toList());
    }


    /**
     * 角色详情
     */
    public RoleResourceModel.RoleModel getRoleDetail(Long id){
        SmsRole smsRole = smsRoleMapper.selectByPrimaryKey(id);
        if(smsRole == null)
            return null;
        return new RoleResourceModel.RoleModel(smsRole);
    }


    /**
     * 获取资源接口列表
     */
    public List<SmsResource> getResourceList(RoleResourceModel.ResourceListReqModel reqModel){
        SmsResourceExample smsResourceExample = new SmsResourceExample();
        SmsResourceExample.Criteria criteria = smsResourceExample.createCriteria();

        if (reqModel.getCategoryId() != null) {
            criteria.andCategoryIdEqualTo(reqModel.getCategoryId());
        }

        if(StringUtils.isNotEmpty(reqModel.getName()))
            criteria.andNameLike("%" + reqModel.getName() + "%");

        if(StringUtils.isNotEmpty(reqModel.getUrl()))
            criteria.andUrlLike("%" + reqModel.getUrl() + "%");



        smsResourceExample.setOrderByClause("create_time desc, id desc");
        if(reqModel.getPageNum() != null && reqModel.getPageSize() != null)
            PageHelper.startPage(reqModel.getPageNum(), reqModel.getPageSize());
        return smsResourceMapper.selectByExample(smsResourceExample);
    }

    /**
     * 封装资源Api列表返回参数
     */
    public List<RoleResourceModel.ResourceModel> setResourceList(List<SmsResource> resourceList){
        if(CollectionUtil.isEmpty(resourceList))
            return Collections.emptyList();
        //查询资源分类信息
        List<SmsResourceCategory> resourceCategoryList = getResourceCategoryListByIds(resourceList.stream()
                .map(SmsResource::getCategoryId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList()));


        return resourceList.stream().map(resource -> {
            RoleResourceModel.ResourceModel resourceModel = new RoleResourceModel.ResourceModel(resource);

            resourceModel.setCategoryName(resourceCategoryList.stream()
                    .filter(resourceCategory -> Objects.equals(resourceCategory.getId(), resource.getCategoryId()))
                    .findFirst()
                    .map(SmsResourceCategory::getName)
                    .orElse(""));
            return resourceModel;
        }).collect(Collectors.toList());
    }


    /**
     * 资源Api详情
     */
    public RoleResourceModel.ResourceModel getResourceDetail(Long id){
        SmsResource smsResource = smsResourceMapper.selectByPrimaryKey(id);
        if(smsResource == null)
            return null;
        RoleResourceModel.ResourceModel resourceModel = new RoleResourceModel.ResourceModel(smsResource);

        if (resourceModel.getCategoryId() != null) {
            SmsResourceCategory smsResourceCategory = smsResourceCategoryMapper.selectByPrimaryKey(resourceModel.getCategoryId());
            resourceModel.setCategoryName(smsResourceCategory.getName());
        }

        return resourceModel;
    }


    /**
     * 资源分类列表
     */
    public List<SmsResourceCategory> getResourceCategoryList(RoleResourceModel.ResourceCategoryListReqModel reqModel){
        SmsResourceCategoryExample smsResourceCategoryExample = new SmsResourceCategoryExample();
        SmsResourceCategoryExample.Criteria criteria = smsResourceCategoryExample.createCriteria();

        if(StringUtils.isNotEmpty(reqModel.getName()))
            criteria.andNameLike("%" + reqModel.getName() + "%");

        smsResourceCategoryExample.setOrderByClause("sort asc, create_time desc, id desc");
        if(reqModel.getPageNum() != null && reqModel.getPageSize() != null)
            PageHelper.startPage(reqModel.getPageNum(), reqModel.getPageSize());
        return smsResourceCategoryMapper.selectByExample(smsResourceCategoryExample);
    }


    /**
     * 封装资源分类列表返回参数
     */
    public List<RoleResourceModel.ResourceCategoryModel> setResourceCategoryList(List<SmsResourceCategory> resourceCategoryList){
        if(CollectionUtil.isEmpty(resourceCategoryList))
            return Collections.emptyList();
        return resourceCategoryList.stream().map(resourceCategory -> {
            RoleResourceModel.ResourceCategoryModel resourceCategoryModel = new RoleResourceModel.ResourceCategoryModel(resourceCategory);
            return resourceCategoryModel;
        }).collect(Collectors.toList());
    }


    /**
     * 封装资源分类和资源接口对应关系列表返回参数
     */
    public List<RoleResourceModel.ResourceCategoryAndResourceApiRelationModel> getResourceCategoryAndResourceApiRelation(){
        //获取所有分类
        SmsResourceCategoryExample smsResourceCategoryExample = new SmsResourceCategoryExample();
        smsResourceCategoryExample.setOrderByClause("sort asc, create_time desc, id desc");
        List<SmsResourceCategory> resourceCategoryList = smsResourceCategoryMapper.selectByExample(smsResourceCategoryExample);
        if(CollectionUtil.isEmpty(resourceCategoryList))
            return Collections.emptyList();

        //获取对应分类下的对应资源接口
        SmsResourceExample smsResourceExample = new SmsResourceExample();
        smsResourceExample.createCriteria()
                .andCategoryIdIn(resourceCategoryList.stream()
                        .map(SmsResourceCategory::getId)
                        .collect(Collectors.toList()));
        List<SmsResource> resourceList = smsResourceMapper.selectByExample(smsResourceExample);

        if(CollectionUtil.isEmpty(resourceList))
            return Collections.emptyList();

        return resourceCategoryList.stream().map(resourceCategory -> {
            RoleResourceModel.ResourceCategoryAndResourceApiRelationModel model = new RoleResourceModel.ResourceCategoryAndResourceApiRelationModel();

            model.setCategoryId(resourceCategory.getId());
            model.setCategoryName(resourceCategory.getName());

            model.setResourceApiList(resourceList.stream()
                    .filter(resource -> Objects.equals(resource.getCategoryId(), resourceCategory.getId()))
                    .map(resource -> new RoleResourceModel.IdNameList(resource.getId(), resource.getName()))
                    .collect(Collectors.toList()));

            return model;
        }).collect(Collectors.toList());
    }


    /**
     * 分类详情
     */
    public SmsResourceCategory getResourceCategoryDetail(Long id){
        return smsResourceCategoryMapper.selectByPrimaryKey(id);
    }



    /**
     * 添加角色
     */
    public void insertRole(RoleResourceModel.AddRoleReqModel roleReqModel){
        SmsRole smsRole = roleReqModel.buildModel();
        smsRoleMapper.insertSelective(smsRole);
    }



    /**
     * 修改角色
     */
    public void updateRole(SmsRole smsRole){
        smsRoleMapper.updateByPrimaryKeySelective(smsRole);
    }



    /**
     * 删除角色
     */
    public void deleteRole(Long id){
        smsRoleMapper.deleteByPrimaryKey(id);
    }


    /**
     * 新增资源接口
     */
    public void insertResource(SmsResource smsResource){
        smsResourceMapper.insertSelective(smsResource);
    }


    /**
     * 修改资源接口
     */
    public void updateResource(SmsResource smsResource){
        smsResourceMapper.updateByPrimaryKeySelective(smsResource);
    }


    /**
     * 删除资源接口
     */
    public void deleteResource(Long id){
        smsResourceMapper.deleteByPrimaryKey(id);
    }


    /**
     * 新增资源分类
     */
    public void insertResourceCategory(SmsResourceCategory smsResourceCategory){
        smsResourceCategoryMapper.insertSelective(smsResourceCategory);
    }


    /**
     * 修改资源分类
     */
    public void updateResourceCategory(SmsResourceCategory smsResourceCategory){
        smsResourceCategoryMapper.updateByPrimaryKeySelective(smsResourceCategory);
    }


    /**
     * 删除资源分类
     */
    public void deleteResourceCategory(Long id){
        smsResourceCategoryMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据多个资源分类id获取资源分类详情
     */
    private List<SmsResourceCategory> getResourceCategoryListByIds(List<Long> ids){
        if(CollectionUtil.isEmpty(ids))
            return Collections.emptyList();
        SmsResourceCategoryExample smsResourceCategoryExample = new SmsResourceCategoryExample();
        smsResourceCategoryExample.createCriteria().andIdIn(ids);
        return smsResourceCategoryMapper.selectByExample(smsResourceCategoryExample);
    }


    /**
     * 获取所有角色和资源的关系数据
     */
    public List<RoleResourceModel.RoleResourceRelationModel> getRoleResourceList(){
        SmsMenuExample smsMenuExample = new SmsMenuExample();
        smsMenuExample.createCriteria().andDeleteTypeEqualTo(YesEnum.NO.getCodeType());
        List<SmsMenu> smsMenus = smsMenuMapper.selectByExample(smsMenuExample);
        if(CollectionUtil.isEmpty(smsMenus))
            return Collections.emptyList();
        //角色关联菜单数据
        SmsRoleMenuRelationExample smsRoleMenuRelationExample = new SmsRoleMenuRelationExample();
        smsRoleMenuRelationExample.createCriteria()
                .andMenuIdIn(smsMenus.stream()
                        .map(SmsMenu::getId)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList()));
        List<SmsRoleMenuRelation> smsRoleMenuRelations = smsRoleMenuRelationMapper.selectByExample(smsRoleMenuRelationExample);
        if(CollectionUtil.isEmpty(smsRoleMenuRelations))
            return Collections.emptyList();

        //菜单关联资源接口数据
        SmsMenuResourceRelationExample smsMenuResourceRelationExample = new SmsMenuResourceRelationExample();
        smsMenuResourceRelationExample.createCriteria()
                .andMenuIdIn(smsRoleMenuRelations.stream()
                        .map(SmsRoleMenuRelation::getMenuId)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList()));

        List<SmsMenuResourceRelation> smsMenuResourceRelations = smsMenuResourceRelationMapper.selectByExample(smsMenuResourceRelationExample);
        if(CollectionUtil.isEmpty(smsMenuResourceRelations))
            return Collections.emptyList();

        List<Long> resourceIds = smsMenuResourceRelations.stream()
                .map(SmsMenuResourceRelation::getResourceId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        //查询资源接口数据
        SmsResourceExample smsResourceExample = new SmsResourceExample();
        smsResourceExample.createCriteria().andIdIn(resourceIds);
        List<SmsResource> smsResources = smsResourceMapper.selectByExample(smsResourceExample);
        if(CollectionUtil.isEmpty(smsResources))
            return Collections.emptyList();

        //封装数据
        return smsRoleMenuRelations.stream()
                .map(SmsRoleMenuRelation::getRoleId)
                .filter(Objects::nonNull)
                .distinct()
                .map(roleId -> {
                    RoleResourceModel.RoleResourceRelationModel roleResourceRelationModel = new RoleResourceModel.RoleResourceRelationModel();
                    roleResourceRelationModel.setId(roleId);
                    //过滤关联关系

                    List<String> resourceUrlApiList = smsResources.stream()
                            //菜单关联资源接口数据
                            .filter(smsResource -> smsMenuResourceRelations.stream()
                                    //角色关联菜单数据
                                    .filter(smsMenuResourceRelation -> smsRoleMenuRelations.stream()
                                            .filter(smsRoleMenuRelation -> Objects.equals(smsRoleMenuRelation.getRoleId(), roleId))
                                            .map(SmsRoleMenuRelation::getMenuId)
                                            .filter(Objects::nonNull)
                                            .distinct()
                                            .collect(Collectors.toList())
                                            .contains(smsMenuResourceRelation.getMenuId()))
                                    .map(SmsMenuResourceRelation::getResourceId)
                                    .filter(Objects::nonNull)
                                    .distinct()
                                    .collect(Collectors.toList())
                                    .contains(smsResource.getId()))
                            .map(SmsResource::getUrl)
                            .filter(StringUtils::isNotEmpty)
                            .distinct()
                            .collect(Collectors.toList());
                    roleResourceRelationModel.setUrls(resourceUrlApiList);

                    return roleResourceRelationModel;
                })
                .collect(Collectors.toList());
    }


    /**
     * 获取所有用户和角色关系数据
     */
    public List<RoleResourceModel.UserRoleRelationModel> getMemberRoleList(Integer relationType) {
        if (relationType == null)
            relationType = 2;
        SmsMemberRoleRelationExample smsMemberRoleRelationExample = new SmsMemberRoleRelationExample();
        smsMemberRoleRelationExample.createCriteria().andRelationTypeEqualTo(relationType);
        List<SmsMemberRoleRelation> smsMemberRoleRelationList = smsMemberRoleRelationMapper.selectByExample(smsMemberRoleRelationExample);
        if(CollectionUtil.isEmpty(smsMemberRoleRelationList))
            return Collections.emptyList();

        List<Long> memberIdList = smsMemberRoleRelationList.stream()
                .map(SmsMemberRoleRelation::getMemberId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        return memberIdList.stream()
                .map(memberId -> {
                    RoleResourceModel.UserRoleRelationModel userRoleRelationModel = new RoleResourceModel.UserRoleRelationModel();
                    userRoleRelationModel.setId(memberId);

                    List<Long> roleIdList = smsMemberRoleRelationList.stream()
                            .filter(smsMemberRoleRelation -> Objects.equals(memberId, smsMemberRoleRelation.getMemberId()))
                            .map(SmsMemberRoleRelation::getRoleId)
                            .filter(Objects::nonNull)
                            .distinct()
                            .collect(Collectors.toList());

                    userRoleRelationModel.setRoleIds(roleIdList);


                    return userRoleRelationModel;
                }).collect(Collectors.toList());
    }


    /**
     * 根据管理员id获取管理员关联的角色
     */
    public List<SmsRole> getRoleListByAdminMemberId(Long memberId){
        SmsMemberRoleRelationExample smsMemberRoleRelationExample = new SmsMemberRoleRelationExample();
        smsMemberRoleRelationExample.createCriteria().andMemberIdEqualTo(memberId).andRelationTypeEqualTo(2);
        List<SmsMemberRoleRelation> smsMemberRoleRelationList = smsMemberRoleRelationMapper.selectByExample(smsMemberRoleRelationExample);
        if(CollectionUtil.isEmpty(smsMemberRoleRelationList))
            return Collections.emptyList();
        List<Long> roleIdList = smsMemberRoleRelationList.stream()
                .map(SmsMemberRoleRelation::getRoleId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        SmsRoleExample smsRoleExample = new SmsRoleExample();
        smsRoleExample.createCriteria().andIdIn(roleIdList);
        smsRoleExample.setOrderByClause("sort asc,id desc");
        return smsRoleMapper.selectByExample(smsRoleExample);
    }


    /**
     * 获取当前角色关联的菜单
     */
    public List<Long> getRoleMenuIdList(Long roleId) {
        if(roleId == null)
            return Collections.emptyList();
        SmsRoleMenuRelationExample smsRoleMenuRelationExample = new SmsRoleMenuRelationExample();
        smsRoleMenuRelationExample.createCriteria().andRoleIdEqualTo(roleId);
        List<SmsRoleMenuRelation> smsRoleMenuRelations = smsRoleMenuRelationMapper.selectByExample(smsRoleMenuRelationExample);
        return smsRoleMenuRelations.stream()
                .map(SmsRoleMenuRelation::getMenuId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }


    /**
     * 删除当前角色关联的菜单数据
     */
    public void deleteRoleMenuList(Long roleId) {
        if(roleId == null)
            return;
        SmsRoleMenuRelationExample smsRoleMenuRelationExample = new SmsRoleMenuRelationExample();
        smsRoleMenuRelationExample.createCriteria().andRoleIdEqualTo(roleId);
        smsRoleMenuRelationMapper.deleteByExample(smsRoleMenuRelationExample);
    }


}
