package com.lidian.community.controller;

import com.lidian.community.DTO.accesstoken;
import com.lidian.community.DTO.githubuser;
import com.lidian.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @ClassName Authorcontroller
 * @Description TODO
 * @Date 2022/7/21 13:57
 */
@Controller
public class Authorcontroller {

    @Autowired
    private Githubprovider gp;
    @Value("${github.Client_id}")
    private String Client_id;
    @Value("${github.Client_secret}")
    private String Client_secret;
    @Value("${github.Redirect_uri}")
    private String Redirect_uri;
    /**
     *
     * @param code
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code){
        accesstoken accesstoken = new accesstoken();
        accesstoken.setCode(code);
        accesstoken.setClient_id(Client_id);
        accesstoken.setClient_secret(Client_secret);
        accesstoken.setState("1");
        accesstoken.setRedirect_uri(Redirect_uri);
        String getaccesstoken = gp.getaccesstoken(accesstoken);
        githubuser getuserinfo = gp.getuserinfo(getaccesstoken);
        System.out.println(getuserinfo.toString());
        return "index";
    }
}
