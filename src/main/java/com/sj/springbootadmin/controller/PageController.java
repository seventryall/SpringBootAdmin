package com.sj.springbootadmin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

@Controller
public class PageController {

    // 从 application.yml 中读取配置，如取不到默认值为Hello Jsp
    @Value("${application.hello:Hello Jsp}")
    private String hello = "Hello Jsp";

    /**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     * 可以通过localhost:8080或者localhost:8080/index访问该方法
     */
//    @RequestMapping(value = {"/","/index"})
//    public String index(Map<String, Object> model){
//        // 直接返回字符串，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
//        // 本例为 /WEB-INF/jsp/index.jsp
//        model.put("time", new Date());
//        model.put("message", this.hello);
//        return "index";
//    }

    /**
     * 响应到JSP页面page1
     */
    @RequestMapping("/page1")
    public ModelAndView page1(){
        // 页面位置 /WEB-INF/jsp/page/page.jsp
        ModelAndView mav = new ModelAndView("page/page1");
        mav.addObject("content", hello);
        return mav;
    }

    /**
     * 响应到JSP页面page1（可以直接使用Model封装内容，直接返回页面字符串）
     */
    @RequestMapping("/page2")
    public String page2(Model model){
        // 页面位置 /WEB-INF/jsp/page/page.jsp
        model.addAttribute("content", hello + "（第二种）");
        return "page/page1";
    }
}