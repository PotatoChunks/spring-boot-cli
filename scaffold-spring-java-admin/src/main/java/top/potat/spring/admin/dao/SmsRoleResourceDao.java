package top.potat.spring.admin.dao;

import org.apache.ibatis.annotations.Param;
import top.potat.spring.db.model.SmsMemberRoleRelation;
import top.potat.spring.db.model.SmsMenuResourceRelation;
import top.potat.spring.db.model.SmsRoleMenuRelation;

import java.util.List;

public interface SmsRoleResourceDao {

    /**
     * 批量关联 菜单关联资源
     */
    void insertMenuResourceList(@Param("list") List<SmsMenuResourceRelation> list);


    /**
     * 批量关联 管理员关联角色
     */
    void insertAdminRoleList(@Param("list") List<SmsMemberRoleRelation> list);


    /**
     * 批量关联 角色菜单关联
     */
    void insertRoleMenuIdList(@Param("list")List<SmsRoleMenuRelation> list);

}
