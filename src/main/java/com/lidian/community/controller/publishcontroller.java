package com.lidian.community.controller;

import com.lidian.community.mapper.questionMapper;
import com.lidian.community.mapper.usermapper;
import com.lidian.community.model.question;
import com.lidian.community.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName publishcontroller
 * @Description TODO
 * @Date 2022/7/23 12:30
 */
@Controller
public class publishcontroller {
    @Autowired
    private usermapper usermapper;
    @Autowired
    private questionMapper questionMapper;

    @GetMapping("/publish")
    public String show(RedirectAttributes attributes) {

        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag, HttpServletRequest request,Model model
    ) {

        Cookie[] cookies = request.getCookies();
        user getuserbytoken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                如果在
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    getuserbytoken = usermapper.getuserbytoken(token);
                    System.out.println(getuserbytoken);
                    if (getuserbytoken != null) {
                        request.getSession().setAttribute("user", getuserbytoken);
                    }
                    break;
                }
            }
        }
//        还是老样子，我们通过浏览器中的token获取用户
        model.addAttribute("title",title);
        model.addAttribute("desp",description);
        model.addAttribute("tag",tag);
        if (getuserbytoken == null) {
            //在model里面添加错误信息提示,这种信息我们肯定不能往cookie里面扔
            model.addAttribute("error","用户没登录");
            return "publish";
        }
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if (StringUtils.length(title) > 50) {
            model.addAttribute("error", "标题最多 50 个字符");
            return "publish";
        }
        if (StringUtils.isEmpty(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }



        question question = new question();
//        用户表单输入的数据
        question.setTitle(title);
        question.setDesp(description);
        question.setTag(tag);
//        还需要user的一些数据
        question.setCreatetime(System.currentTimeMillis());
        question.setModifytime(System.currentTimeMillis());
        question.setCreator(getuserbytoken.getId());
        questionMapper.insertq(question);
//        return "redirect: /"; 不能写成这样
        return "redirect:/";
    }
}
