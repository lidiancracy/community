package com.lidian.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName indexcontroller
 * @Description TODO
 * @Date 2022/7/21 12:20
 */
@Controller
public class indexcontroller {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
