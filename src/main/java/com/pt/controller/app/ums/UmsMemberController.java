package com.pt.controller.app.ums;

import com.pt.api.common.CommonPage;
import com.pt.api.common.CommonResult;
import com.pt.db.model.UmsMemberUser;
import com.pt.service.app.ums.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;


    @RequestMapping(value = "/get/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberUser>> getUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<UmsMemberUser> userList = memberService.getUserList(pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(userList));
    }


}
