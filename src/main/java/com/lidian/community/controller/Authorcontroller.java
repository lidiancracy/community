package com.lidian.community.controller;

import com.lidian.community.DTO.accesstoken;
import com.lidian.community.DTO.githubuser;
import com.lidian.community.mapper.usermapper;
import com.lidian.community.model.user;
import com.lidian.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


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
    @Autowired
    private usermapper usermapper;


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
        githubuser githubuser = gp.getuserinfo(getaccesstoken);
        if (githubuser != null) {
            //        将user对象传入数据库
            user user = new user();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubuser.getName());
            user.setAccountid(String.valueOf(githubuser.getId()) );
            user.setCreatetime(System.currentTimeMillis());
            user.setModifiedtime(user.getModifiedtime());
            usermapper.insertuser(user);
            System.out.println(user);
            request.getSession().setAttribute("user",githubuser);
            // 登陆成功
            return "redirect:/";
        } else {
//            失败
            return "redirect:/";
        }

    }
}
