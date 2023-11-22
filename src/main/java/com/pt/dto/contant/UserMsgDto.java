package com.pt.dto.contant;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserMsgDto {
    private Long id;
    private String userCode;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;

}
