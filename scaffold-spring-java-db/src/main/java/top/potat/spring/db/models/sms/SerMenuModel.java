package top.potat.spring.db.models.sms;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import top.potat.spring.common.domain.page.PageBase;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.db.model.SmsMenu;

import java.util.Date;
import java.util.List;

/**
 * 菜单Model
 */
public class SerMenuModel {


    /**
     * 新增/修改/详情菜单Model
     */
    @Data
    public static class AddUpdateMenuReqModel {
        @ApiModelProperty(value = "id")
        private Long id;
        @ApiModelProperty(value = "父级id")
        private Long menuParentId;
        @ApiModelProperty(value = "菜单层级")
        private String menuLevelStr;
        @ApiModelProperty(value = "菜单名称")
        private String menuName;
        @ApiModelProperty(value = "图标")
        private String menuIcon;
        @ApiModelProperty(value = "路径")
        private String menuPath;
        @ApiModelProperty(value = "描述")
        private String menuDescription;
        @ApiModelProperty(value = "排序")
        private Integer menuSort;
        @ApiModelProperty(value = "1->启用;2->禁用;")
        private Integer menuStatus;
        @ApiModelProperty(value = "显示状态:1->显示;2->隐藏;")
        private Integer menuShowStatus;

        private MenuResourceRelationModel menuResourceRelationModel;

        /**
         *  校验
         */
        public void check(){
            if(this.getMenuParentId() == null)
                this.setMenuParentId(0L);
            if(StringUtils.isEmpty(this.getMenuName()))
                ErrorAsserts.dataNotFount("菜单名称不能为空");
            if(this.getMenuStatus() == null || this.getMenuStatus() < 1 || this.getMenuStatus() > 2){
                this.setMenuStatus(1);
            }
            if(this.getMenuShowStatus() == null || this.getMenuShowStatus() < 1 || this.getMenuShowStatus() > 2){
                this.setMenuShowStatus(1);
            }
        }

        /**
         * 封装
         */
        public SmsMenu buildModel(){
            SmsMenu smsMenu = new SmsMenu();
            BeanUtils.copyProperties(this,smsMenu);
            return smsMenu;
        }
        public AddUpdateMenuReqModel(){}
        public AddUpdateMenuReqModel(SmsMenu menu){
            BeanUtils.copyProperties(menu,this);
        }

    }



    /**
     * 列表请求参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class MenuListReqModel extends PageBase {
        @ApiModelProperty(value = "父级id")
        private Long menuParentId;
        @ApiModelProperty(value = "菜单名称")
        private String menuName;
        @ApiModelProperty(value = "路径")
        private String menuPath;
        @ApiModelProperty(value = "1->启用;2->禁用;")
        private Integer menuStatus;
        @ApiModelProperty(value = "显示状态:1->显示;2->隐藏;")
        private Integer menuShowStatus;
    }


    /**
     * 菜单树形结构Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class MenuTreeModel extends SmsMenu {
        @ApiModelProperty(value = "删除时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date deleteTime;
        @ApiModelProperty(value = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;
        @ApiModelProperty(value = "修改时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;

        @ApiModelProperty(value = "子级菜单")
        private List<MenuTreeModel> children;

        public MenuTreeModel(){}
        public MenuTreeModel(SmsMenu menu){
            BeanUtils.copyProperties(menu,this);
        }

        public String toJson(){
            return JSONObject.toJSONString(this);
        }

    }


    /**
     * 菜单关联资源ApiModel
     */
    @Data
    public static class MenuResourceRelationModel {
        @ApiModelProperty(value = "菜单ID")
        private Long menuId;
        @ApiModelProperty(value = "多个资源ApiID")
        private List<Long> resourceIdList;


        public void check(){
            if(this.getMenuId() == null)
                ErrorAsserts.dataNotFount("菜单ID不能为空");
        }

    }

}
