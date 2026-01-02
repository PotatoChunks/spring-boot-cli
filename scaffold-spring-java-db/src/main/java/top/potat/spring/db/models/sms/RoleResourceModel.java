package top.potat.spring.db.models.sms;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import top.potat.spring.common.domain.page.PageBase;
import top.potat.spring.common.enums.YesEnum;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.db.model.SmsResource;
import top.potat.spring.db.model.SmsResourceCategory;
import top.potat.spring.db.model.SmsRole;

import java.util.Date;
import java.util.List;

/**
 * 角色资源Model
 */
public class RoleResourceModel {

    /**
     * 角色列表请求参数Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class RoleListReqModel extends PageBase {
        @ApiModelProperty(value = "角色名称")
        private String name;
    }


    /**
     * 角色列表返回Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class RoleModel extends SmsRole {
        @ApiModelProperty(value = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

        @ApiModelProperty(value = "更新时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;

        @ApiModelProperty(value = "绑定的菜单id")
        private List<Long> menuIdList;

        public RoleModel(){}
        public RoleModel(SmsRole smsRole){
            BeanUtils.copyProperties(smsRole,this);
        }
    }


    /**
     * 角色类型列表Model
     */
    @Data
    public static class RoleTypeModel {
        @ApiModelProperty(value = "角色类型ID")
        private Integer value;
        @ApiModelProperty(value = "角色类型名称")
        private String label;
    }


    /**
     * 新增角色请求参数 Model
     */
    @Data
    public static class AddRoleReqModel {
        private Long id;
        @ApiModelProperty(value = "角色名称")
        private String name;

        @ApiModelProperty(value = "描述")
        private String description;

        @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
        private Integer status;

        @ApiModelProperty(value = "排序")
        private Integer sort;

        @ApiModelProperty(value = "角色类型：1->用户角色；2->后台角色；")
        private Integer roleType;


        @ApiModelProperty(value = "菜单id")
        private List<Long> menuIdList;

        /**
         * 校验
         */
        public void check(){
            if(StringUtils.isEmpty(this.getName()))
                ErrorAsserts.dataNotFount("角色名称不能为空");
            if(this.getStatus() == null)
                this.setStatus(YesEnum.YES.getCodeType());
            if (this.getSort() == null)
                this.setSort(1);
            if (this.getRoleType() == null)
                this.setRoleType(2);
        }

        /**
         * 转换新增对象
         */
        public SmsRole buildModel(){
            SmsRole smsRole = new SmsRole();
            BeanUtils.copyProperties(this,smsRole);
            return smsRole;
        }

    }


    /**
     * 资源接口路径列表请求参数Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ResourceListReqModel extends PageBase {
        @ApiModelProperty(value = "资源名称")
        private String name;
        @ApiModelProperty(value = "资源URL")
        private String url;
        @ApiModelProperty(value = "资源分类ID")
        private Long categoryId;
    }

    /**
     * 资源接口分类列表请求参数Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ResourceCategoryListReqModel extends PageBase {
        @ApiModelProperty(value = "分类名称")
        private String name;
    }


    /**
     * 资源接口分类列表返回Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ResourceCategoryModel extends SmsResourceCategory {
        @ApiModelProperty(value = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

        public ResourceCategoryModel(){}
        public ResourceCategoryModel(SmsResourceCategory smsResourceCategory){
            BeanUtils.copyProperties(smsResourceCategory,this);
        }
    }

    /**
     * 角色关联资源Api参数Model
     */
    @Data
    public static class RoleResourceReqModel {
        @ApiModelProperty(value = "角色ID")
        private Long roleId;
        @ApiModelProperty(value = "资源ID列表")
        private List<Long> resourceIdList;


        public void check(){
            if(this.getRoleId() == null)
                ErrorAsserts.dataNotFount("角色Id不能为空");
            if(CollectionUtil.isEmpty(this.getResourceIdList()))
                ErrorAsserts.dataNotFount("资源Id列表不能为空");
        }
    }

    /**
     * 资源Api参数Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ResourceModel extends SmsResource {
        @ApiModelProperty(value = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

        @ApiModelProperty(value = "资源分类名称")
        private String categoryName;

        public ResourceModel(){}
        public ResourceModel(SmsResource smsResource){
            BeanUtils.copyProperties(smsResource,this);
        }



        public void check(){
            if(StringUtils.isEmpty(this.getUrl()))
                ErrorAsserts.dataNotFount("资源URL不能为空");
        }

    }


    /**
     * 角色资源关联Model
     */
    @Data
    public static class RoleResourceRelationModel{
        @ApiModelProperty("角色ID")
        private Long id;
        @ApiModelProperty("多个资源路径")
        private List<String> urls;
    }

    /**
     * 用户关联多个角色Model
     */
    @Data
    public static class UserRoleRelationModel{
        @ApiModelProperty("用户ID")
        private Long id;
        @ApiModelProperty("多个角色ID")
        private List<Long> roleIds;
    }


    /**
     * 资源分类对应资源接口Model
     */
    @Data
    public static class ResourceCategoryAndResourceApiRelationModel{
        @ApiModelProperty("资源分类ID")
        private Long categoryId;
        @ApiModelProperty("资源分类名称")
        private String categoryName;
        @ApiModelProperty("多个资源接口")
        private List<IdNameList> resourceApiList;
    }



    @Data
    public static class IdNameList{
        @ApiModelProperty("ID")
        private Long id;
        @ApiModelProperty("名称")
        private String name;
        public IdNameList(){}
        public IdNameList(Long id, String name){
            this.id = id;
            this.name = name;
        }
    }

}
