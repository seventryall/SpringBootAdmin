package com.sj.springbootadmin.controller;


import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.UserService;
import com.sj.springbootadmin.service.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "login/index";
    }

//    @ResponseBody
//    @RequestMapping(value = {"/userLogin2"},method= RequestMethod.POST)
//    public ResponseData<UserDto> userLogin2(@RequestParam("userName") String userName,
//                            @RequestParam("userPwd") String userPwd,
//                            HttpServletRequest request){
//
//        ResponseData<UserDto> res = userService.getUser(userName,userPwd);
//
//        if(res.getData() != null){                                                  //登录成功
//            request.getSession().setAttribute("session_user",res.getData());     //将用户信息放入session  用于后续的拦截器
//        }
//        return res;
//    }

    @ResponseBody
    @RequestMapping(value = {"/userLogin"},method= RequestMethod.POST)
    public ResponseData<String> userLogin(@RequestParam("userName") String userName,
                                           @RequestParam("userPwd") String userPwd,
                                           HttpServletRequest request){

        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        ResponseData<String> res=new ResponseData<String>();
        try
        {
            currentUser.login(token);
        }
        catch(AuthenticationException ae)
        {
            res.setCode(101);
            res.setMsg("用户名或密码错误");
        }
        if(currentUser.isAuthenticated()){

        }else{
            token.clear();
            res.setCode(102);
            res.setMsg("登录认证失败");
        }

        return res;
    }

//    @GetMapping("/logout2")
//    public String logout2(HttpSession session){
//        session.removeAttribute("session_user");
//        return "redirect:/login/index";
//    }

    @GetMapping("/logout")
    public String logout(){
       // SecurityUtils.getSubject().logout();
        return "redirect:/login/index";
    }

}
