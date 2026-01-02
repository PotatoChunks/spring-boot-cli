package top.potat.spring.admin.utils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.db.models.sms.RoleResourceModel;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限工具类
 */
@Slf4j
public class AuthUtils {
    /**
     * 角色资源关系
     */
    private final Map<Long , List<String>> roleResourceMap = new HashMap<>();
    /**
     * 用户角色关系
     */
    private final Map<Long , List<Long>> userRoleMap = new HashMap<>();


    /**
     * 初始化权限
     */
    public void initAuthority(List<RoleResourceModel.RoleResourceRelationModel> roleResourceRelationList,
                              List<RoleResourceModel.UserRoleRelationModel> userRoleRelationList) {
        log.debug("==========初始化权限==========");
        for (RoleResourceModel.RoleResourceRelationModel roleResourceRelationModel : roleResourceRelationList) {
            roleResourceMap.put(roleResourceRelationModel.getId(), roleResourceRelationModel.getUrls());
        }
        for (RoleResourceModel.UserRoleRelationModel userRoleRelationModel : userRoleRelationList) {
            userRoleMap.put(userRoleRelationModel.getId(), userRoleRelationModel.getRoleIds());
        }
        log.debug("==========初始化权限完成==========");
        roleResourceRelationList = null;
        userRoleRelationList = null;
    }


    /**
     * 根据角色获取权限
     */
    public List<String> getAuthority(Long roleId) {
        if(roleId == null || !roleResourceMap.containsKey(roleId))
            return Collections.emptyList();
        return roleResourceMap.get(roleId);
    }


    /**
     * 根据角色获取多个权限
     */
    public List<String> getAuthority(List<Long> roleIds) {
        if(CollectionUtil.isEmpty(roleIds))
            return Collections.emptyList();
        return roleIds.stream()
                .map(this::getAuthority)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }


    /**
     * 根据用户获取角色权限
     */
    public List<String> getAuthorityByUserId(Long userId) {
        if(userId == null || !userRoleMap.containsKey(userId))
            return Collections.emptyList();
        return userRoleMap.get(userId)
                .stream()
                .map(roleResourceMap::get)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(StringUtils::isNotEmpty)
                .distinct()
                .collect(Collectors.toList());
    }

}
