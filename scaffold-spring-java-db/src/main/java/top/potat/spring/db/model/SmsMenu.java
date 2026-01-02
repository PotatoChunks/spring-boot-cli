package top.potat.spring.db.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SmsMenu implements Serializable {
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

    @ApiModelProperty(value = "是否删除1->是;2->否;")
    private Integer deleteType;

    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Long menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getMenuLevelStr() {
        return menuLevelStr;
    }

    public void setMenuLevelStr(String menuLevelStr) {
        this.menuLevelStr = menuLevelStr;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public Integer getMenuShowStatus() {
        return menuShowStatus;
    }

    public void setMenuShowStatus(Integer menuShowStatus) {
        this.menuShowStatus = menuShowStatus;
    }

    public Integer getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(Integer deleteType) {
        this.deleteType = deleteType;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuParentId=").append(menuParentId);
        sb.append(", menuLevelStr=").append(menuLevelStr);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", menuPath=").append(menuPath);
        sb.append(", menuDescription=").append(menuDescription);
        sb.append(", menuSort=").append(menuSort);
        sb.append(", menuStatus=").append(menuStatus);
        sb.append(", menuShowStatus=").append(menuShowStatus);
        sb.append(", deleteType=").append(deleteType);
        sb.append(", deleteTime=").append(deleteTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}