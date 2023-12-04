package com.pt.db.model;

import java.io.Serializable;

public class UmsMemberUser implements Serializable {
    private Long id;

    /**
     * 用户信息表id
     */
    private Long userInfoId;

    /**
     * 密码
     */
    private String password;

    /**
     * 唯一编码
     */
    private String userCode;

    /**
     * 状态：0->禁用；1->正常；
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", password=").append(password);
        sb.append(", userCode=").append(userCode);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}