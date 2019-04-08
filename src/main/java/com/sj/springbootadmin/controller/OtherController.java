package com.sj.springbootadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/other")
public class OtherController {

    @RequestMapping("/score")
    public String score()
    {
        return "other/score";
    }
}
