package com.lidian.community.controller;

import com.lidian.community.mapper.usermapper;
import com.lidian.community.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName indexcontroller
 * @Description TODO
 * @Date 2022/7/21 12:20
 */
@Controller
public class indexcontroller {
    @Autowired
    private usermapper usermapper;
    @GetMapping("/")
    public String index(HttpServletRequest request) {
//        我们需要检索浏览器中有没有数据库中对应的cookie token
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                如果在
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user getuserbytoken = usermapper.getuserbytoken(token);
                    System.out.println(getuserbytoken);
                    if (getuserbytoken!=null){
                        request.getSession().setAttribute("user",getuserbytoken);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
