package com.sj.springbootadmin.controller;


import com.sj.springbootadmin.service.contract.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value={"","/","/home/index"})
    public String index(Model model){
        model.addAttribute("title","主页");
        model.addAttribute("leftMenuHtml",menuService.buildLeftMenuHtml().getData());
        model.addAttribute("rootMenus",menuService.getRootMenus().getData());
        return "home/index";
    }
}
