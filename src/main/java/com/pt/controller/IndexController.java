package com.pt.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONArray;
import com.pt.api.common.CommonResult;
import com.pt.db.model.UmsMemberUser;
import com.pt.dto.excel.UserMsgTitle;
import com.pt.service.app.ums.UmsMemberService;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
//@RequestMapping(value = "/",produces = {"application/json;charset=UTF-8"})
public class IndexController {

    @Autowired
    private Ip2regionSearcher ip2regionSearcher;
    @Autowired
    private UmsMemberService memberService;

    //ip获取地理位置用法
    @RequestMapping(value = "/test/ip", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getIpTest(){
        IpInfo ipInfo = ip2regionSearcher.memorySearch("192.168.10.42");
        //province省份
        //city城市
        //country国家
        int a = 1;
        a = a/0;
        System.out.println(a);
        return CommonResult.success(ipInfo);
    }

    //导出excel表格
    @RequestMapping(value = "/test/outExcelTest", method = RequestMethod.GET)
    @ResponseBody
    public void outExcelTest(HttpServletResponse response){
        List<UmsMemberUser> userList = memberService.getUserList(1, 10);
        List<UserMsgTitle> userMsgTitles = JSONArray.parseArray(JSONArray.toJSONString(userList), UserMsgTitle.class);
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("用户数据导出", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream())
                    .head(UserMsgTitle.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("用户数据导出")
                    .doWrite(userMsgTitles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
