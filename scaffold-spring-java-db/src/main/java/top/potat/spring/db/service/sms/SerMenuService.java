package top.potat.spring.db.service.sms;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.common.enums.YesEnum;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.db.mapper.SmsMemberRoleRelationMapper;
import top.potat.spring.db.mapper.SmsMenuMapper;
import top.potat.spring.db.mapper.SmsMenuResourceRelationMapper;
import top.potat.spring.db.mapper.SmsRoleMenuRelationMapper;
import top.potat.spring.db.model.*;
import top.potat.spring.db.models.sms.SerMenuModel;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SerMenuService {
    private final SmsMenuMapper smsMenuMapper;
    private final SmsMenuResourceRelationMapper smsMenuResourceRelationMapper;
    private final SmsMemberRoleRelationMapper smsMemberRoleRelationMapper;
    private final SmsRoleMenuRelationMapper smsRoleMenuRelationMapper;


    /**
     * 获取菜单列表
     */
    public List<SmsMenu> getMenuList(SerMenuModel.MenuListReqModel reqModel){
        SmsMenuExample smsMenuExample = new SmsMenuExample();
        SmsMenuExample.Criteria criteria = smsMenuExample.createCriteria().andDeleteTypeEqualTo(YesEnum.NO.getCodeType());

        if(reqModel.getMenuParentId() != null)
            criteria.andMenuParentIdEqualTo(reqModel.getMenuParentId());

        if(reqModel.getMenuStatus() != null)
            criteria.andMenuStatusEqualTo(reqModel.getMenuStatus());

        if(reqModel.getMenuShowStatus() != null)
            criteria.andMenuShowStatusEqualTo(reqModel.getMenuShowStatus());

        if(StringUtils.isNotEmpty(reqModel.getMenuPath()))
            criteria.andMenuPathLike("%" + reqModel.getMenuPath() + "%");

        if(StringUtils.isNotEmpty(reqModel.getMenuName()))
            criteria.andMenuNameLike("%" + reqModel.getMenuName() + "%");

        smsMenuExample.setOrderByClause("menu_parent_id asc, menu_sort asc, create_time desc, id desc");

        if(reqModel.getPageNum() != null && reqModel.getPageSize() != null)
            PageHelper.startPage(reqModel.getPageNum(), reqModel.getPageSize());
        return smsMenuMapper.selectByExample(smsMenuExample);
    }


    /**
     * 封装列表返回Model
     */
    public List<SerMenuModel.MenuTreeModel> setMenuList(List<SmsMenu> sourceList){
        if(CollectionUtil.isEmpty(sourceList))
            return Collections.emptyList();
        return sourceList.stream().map(SerMenuModel.MenuTreeModel::new).collect(Collectors.toList());
    }


    /**
     * 获取菜单树
     */
    public List<SerMenuModel.MenuTreeModel> getMenuTree(){
        SmsMenuExample smsMenuExample = new SmsMenuExample();
        smsMenuExample.createCriteria().andDeleteTypeEqualTo(YesEnum.NO.getCodeType());
        smsMenuExample.setOrderByClause("menu_sort asc, create_time desc, id desc");
        List<SmsMenu> smsMenus = smsMenuMapper.selectByExample(smsMenuExample);
        if(CollectionUtil.isEmpty(smsMenus)) return Collections.emptyList();

        return setMenuTree(smsMenus, 0L);
    }


    /**
     * 根据管理员id获取管理员关联的菜单
     */
    public List<SerMenuModel.MenuTreeModel> getMenuTreeByAdminId(Long adminId){
        if(adminId == null) return Collections.emptyList();
        SmsMemberRoleRelationExample smsMemberRoleRelationExample = new SmsMemberRoleRelationExample();
        smsMemberRoleRelationExample.createCriteria().andMemberIdEqualTo(adminId);
        List<SmsMemberRoleRelation> smsMemberRoleRelations = smsMemberRoleRelationMapper.selectByExample(smsMemberRoleRelationExample);
        if(CollectionUtil.isEmpty(smsMemberRoleRelations))
            return Collections.emptyList();


        SmsRoleMenuRelationExample smsRoleMenuRelationExample = new SmsRoleMenuRelationExample();
        smsRoleMenuRelationExample.createCriteria()
                .andRoleIdIn(smsMemberRoleRelations.stream()
                        .map(SmsMemberRoleRelation::getRoleId)
                        .collect(Collectors.toList()));

        List<SmsRoleMenuRelation> smsRoleMenuRelations = smsRoleMenuRelationMapper.selectByExample(smsRoleMenuRelationExample);

        if(CollectionUtil.isEmpty(smsRoleMenuRelations))
            return Collections.emptyList();


        //展示的菜单列表
        SmsMenuExample smsMenuExample = new SmsMenuExample();
        smsMenuExample.createCriteria()
                .andIdIn(smsRoleMenuRelations.stream()
                        .map(SmsRoleMenuRelation::getMenuId)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList()))
                .andDeleteTypeEqualTo(YesEnum.NO.getCodeType())
                .andMenuStatusEqualTo(YesEnum.YES.getCodeType())
                .andMenuShowStatusEqualTo(YesEnum.YES.getCodeType());
        List<SmsMenu> smsMenus = smsMenuMapper.selectByExample(smsMenuExample);
        if(CollectionUtil.isEmpty(smsMenus))
            return Collections.emptyList();

        return setMenuTree(smsMenus, 0L);
    }


    /**
     * 递归获取菜单树
     */
    public List<SerMenuModel.MenuTreeModel> setMenuTree(List<SmsMenu> sourceList, Long parentId){

        return sourceList.stream()
                .filter(menu -> Objects.equals(menu.getMenuParentId(), parentId))
                .map(SerMenuModel.MenuTreeModel::new)
                .peek(menuTreeModel -> menuTreeModel.setChildren(setMenuTree(sourceList, menuTreeModel.getId())))
                .collect(Collectors.toList());
    }


    /**
     * 新增菜单
     */
    public void addMenu(SmsMenu smsMenu){
        smsMenuMapper.insertSelective(smsMenu);
    }


    /**
     * 修改菜单
     */
    public void updateMenu(SmsMenu smsMenu){
        if(smsMenu.getId() == null) return;
        smsMenuMapper.updateByPrimaryKeySelective(smsMenu);
    }


    /**
     * 删除菜单 (软删除)
     */
    public void deleteMenu(Long id){
        SmsMenu smsMenu = smsMenuMapper.selectByPrimaryKey(id);
        if (smsMenu == null) return;
        smsMenu.setDeleteType(YesEnum.YES.getCodeType());
        smsMenu.setDeleteTime(new Date());
        updateMenu(smsMenu);
    }


    /**
     * 获取菜单详情
     */
    public SmsMenu getMenuDetail(Long id){
        return smsMenuMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据菜单id获取对应资源接口数据
     */
    public List<Long> getMenuResource(Long menuId){
        if(menuId == null) return Collections.emptyList();
        SmsMenuResourceRelationExample menuResourceRelationExample = new SmsMenuResourceRelationExample();
        menuResourceRelationExample.createCriteria().andMenuIdEqualTo(menuId);
        List<SmsMenuResourceRelation> menuResourceRelations = smsMenuResourceRelationMapper.selectByExample(menuResourceRelationExample);
        if(CollectionUtil.isEmpty(menuResourceRelations))
            return Collections.emptyList();

        return menuResourceRelations.stream().map(SmsMenuResourceRelation::getResourceId).collect(Collectors.toList());
    }


    /**
     * 删除菜单绑定的资源Api
     */
    public void deleteMenuResource(Long menuId){
        SmsMenuResourceRelationExample menuResourceRelationExample = new SmsMenuResourceRelationExample();
        menuResourceRelationExample.createCriteria().andMenuIdEqualTo(menuId);
        smsMenuResourceRelationMapper.deleteByExample(menuResourceRelationExample);
    }

}
