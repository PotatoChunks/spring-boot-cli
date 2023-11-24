package com.pt.controller.app.ums;

import com.pt.api.common.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/member",produces = {"application/json;charset=UTF-8"})
public class MemberController {

    //登陆注册接口
    @RequestMapping(value = "/loginRegister", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> login(){

        return CommonResult.success("ok");
    }

}
