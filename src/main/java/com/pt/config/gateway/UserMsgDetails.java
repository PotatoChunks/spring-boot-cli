package com.pt.config.gateway;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserMsgDetails implements Serializable {
    //用户唯一编码
    private String user_code;
    //
    private String user_name;
    private List<String> scope;
    //加密信息
    private String jtc;
    //身份
    private List<String> authorities;
    //客户端id
    private String client_id;
}
