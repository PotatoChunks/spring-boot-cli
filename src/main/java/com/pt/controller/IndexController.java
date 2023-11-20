package com.pt.controller;

import com.pt.api.common.CommonResult;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/",produces = {"application/json;charset=UTF-8"})
public class IndexController {
    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    //ip获取地理位置用法
    @RequestMapping(value = "/test/ip", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getIpTest(){
        IpInfo ipInfo = ip2regionSearcher.memorySearch("192.168.10.42");
        //province省份
        //city城市
        //country国家
        return CommonResult.success(ipInfo);
    }

}
