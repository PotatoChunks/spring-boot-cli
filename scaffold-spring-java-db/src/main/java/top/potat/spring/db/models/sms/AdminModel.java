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
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.db.model.SmsRole;

import java.util.Date;
import java.util.List;

/**
 * 管理员Model
 */
public class AdminModel {

    /**
     * 请求管理员列表参数Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListParamModel extends PageBase {
        @ApiModelProperty(value = "名称")
        private String userName;
        @ApiModelProperty(value = "登录账号")
        private String loginName;
        @ApiModelProperty(value = "手机号")
        private String phone;
        @ApiModelProperty(value = "职务")
        private String title;
    }


    /**
     * 管理员列表返回信息Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AdminListModel extends SmsAdmin {
        @ApiModelProperty(value = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;
        @ApiModelProperty(value = "修改时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;
        @ApiModelProperty(value = "删除时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date deleteTime;

        @ApiModelProperty(value = "禁用启用")
        private String disableTypeName;


        public AdminListModel(){}
        public AdminListModel(SmsAdmin admin){
            BeanUtils.copyProperties(admin,this);
        }

    }


    /**
     * 管理员信息Model
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AdminInfoModel extends SmsAdmin {
        @ApiModelProperty(value = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;
        @ApiModelProperty(value = "修改时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;
        @ApiModelProperty(value = "删除时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date deleteTime;


        @ApiModelProperty(value = "角色ID列表")
        private List<SmsRole> roleIdList;

        public AdminInfoModel(){}
        public AdminInfoModel(SmsAdmin admin){
            BeanUtils.copyProperties(admin,this);
        }


        public void check(){
            if(StringUtils.isEmpty(this.getLoginName()))
                ErrorAsserts.dataNotFount("登录账号不能为空");
            if(StringUtils.isEmpty(this.getPassword()))
                ErrorAsserts.dataNotFount("密码不能为空");
        }
        public String toJson(){
            return JSONObject.toJSONString(this);
        }

    }


    /**
     * 管理员修改信息参数Model
     */
    @Data
    public static class UpdateParamModel {
        private Long id;
        @ApiModelProperty(value = "名称")
        private String userName;
        @ApiModelProperty(value = "职务")
        private String title;
        @ApiModelProperty(value = "头像")
        private String photoPic;
        @ApiModelProperty(value = "登录账号")
        private String loginName;
        @ApiModelProperty(value = "密码")
        private String password;
        @ApiModelProperty(value = "手机号")
        private String phone;
        @ApiModelProperty(value = "1->启用;2->禁用;")
        private Integer disableType;

        @ApiModelProperty(value = "角色ID列表")
        private List<SmsRole> roleIdList;

        public void check(){
            if(this.getId() == null)
                ErrorAsserts.dataNotFount("管理员Id不能为空");
            if(StringUtils.isEmpty(this.getLoginName()))
                ErrorAsserts.dataNotFount("登录账号不能为空");
        }
        public String toJson(){
            return JSONObject.toJSONString(this);
        }
        public SmsAdmin build(){
            SmsAdmin smsAdmin = new SmsAdmin();
            BeanUtils.copyProperties(this,smsAdmin);
            return smsAdmin;
        }
    }


}
