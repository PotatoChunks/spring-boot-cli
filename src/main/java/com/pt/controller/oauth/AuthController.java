package com.pt.controller.oauth;

import com.pt.api.common.CommonResult;
import com.pt.dto.Oauth2TokenDto;
import com.pt.dto.contant.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping(value = "/oauth", produces = {"application/json;charset=UTF-8"})
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> oauth2TokenReq) throws HttpRequestMethodNotSupportedException {
        if (oauth2TokenReq == null) return CommonResult.failed("参数错误");
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, oauth2TokenReq).getBody();
        if (oAuth2AccessToken == null) return CommonResult.failed("账号错误");
        Oauth2TokenDto oauth2TokenDto = new Oauth2TokenDto()
                .setToken(oAuth2AccessToken.getValue())
                .setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .setExpiresIn(oAuth2AccessToken.getExpiresIn())
                .setTokenHead(MyConstant.JWT_TOKEN_PREFIX);
        try {
            Date expireAt=new Date();
            /*String sign = JWT.create()
                    //发行人
                    .withIssuer("auth0")
                    //存放数据
                    .withClaim("username", "admin")
                    .withClaim("password", "123456")
                    //过期时间
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256("123456q"));*/
            System.out.println(oAuth2AccessToken.getValue());
            System.out.println("============================");
            //System.out.println(sign);
            //JWSObject parse = JWSObject.parse(oAuth2AccessToken.getValue());
            //System.out.println(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CommonResult.success(oauth2TokenDto);
    }

}
