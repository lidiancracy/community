package com.lidian.community.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lidian.community.DTO.questiondto;
import com.lidian.community.mapper.questionMapper;
import com.lidian.community.mapper.usermapper;
import com.lidian.community.model.question;
import com.lidian.community.model.user;
import com.lidian.community.service.questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName indexcontroller
 * @Description TODO
 * @Date 2022/7/21 12:20
 */
@Controller
public class indexcontroller {
    @Autowired
//    我需要将question相关内容渲染到index 页面上
    private questionMapper qm;
    @Autowired
    private usermapper usermapper;
    @Autowired
    private questionservice questservice;
    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, HttpServletRequest request, Model model) {
//        我们需要检索浏览器中有没有数据库中对应的cookie token
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                如果在
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user getuserbytoken = usermapper.getuserbytoken(token);
//                    System.out.println(getuserbytoken);
                    if (getuserbytoken!=null){
                        request.getSession().setAttribute("user",getuserbytoken);
                    }
                    break;
                }
            }
        }
//    分页



        // 默认第一页开始、一行显示10个
        PageHelper.startPage(pageNum, 5);
// 原有的查询方法（需写在startpage后）
        List<questiondto> getquestions1 = questservice.getquestions();
// pagehelper默认写法、传递session
        PageInfo<questiondto> pageInfo = new PageInfo<>(getquestions1);
        model.addAttribute("questions", pageInfo);

        return "index";
    }
}
