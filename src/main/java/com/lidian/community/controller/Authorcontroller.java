package com.lidian.community.controller;

import com.lidian.community.DTO.accesstoken;
import com.lidian.community.DTO.githubuser;
import com.lidian.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpRequest;

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
//    etst

    /**
     * @param code
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code , HttpServletRequest request) {
        accesstoken accesstoken = new accesstoken();
        accesstoken.setCode(code);
        accesstoken.setClient_id(Client_id);
        accesstoken.setClient_secret(Client_secret);
        accesstoken.setState("1");
        accesstoken.setRedirect_uri(Redirect_uri);
        String getaccesstoken = gp.getaccesstoken(accesstoken);
//        获取user对象
        githubuser user = gp.getuserinfo(getaccesstoken);
//        System.out.println(getuserinfo.toString());
        if (user != null) {
            request.getSession().setAttribute("user",user);
            // 登陆成功
            return "redirect:/";
        } else {
//            失败
            return "redirect:/";
        }

    }
}
