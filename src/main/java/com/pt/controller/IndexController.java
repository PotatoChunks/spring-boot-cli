package com.pt.controller;

import com.pt.api.common.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping(value = "/",produces = {"application/json;charset=UTF-8"})
public class IndexController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> test(){
        return CommonResult.success("OK");
    }

}
