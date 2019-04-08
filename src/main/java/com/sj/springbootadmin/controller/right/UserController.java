package com.sj.springbootadmin.controller.right;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.UserService;
import com.sj.springbootadmin.service.dto.UserDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    @RequiresPermissions("/user/index.View")
    public String index()
    {
        return "right/user/index";
    }

    @RequestMapping("/insert2")
    public String insert2()
    {
        return "right/user/insert";
    }


    @RequestMapping("/insert")
    @RequiresPermissions("/user/index.Insert")
    public String insert()
    {
        return "right/user/insert";
    }

    @RequestMapping("/update")
    @RequiresPermissions("/user/index.Update")
    public String update()
    {
        return "right/user/update";
    }

    @RequestMapping("/detail")
    @RequiresPermissions("/user/index.Detail")
    public String detail()
    {
        return "right/user/detail";
    }




    @ResponseBody
    @RequestMapping(value = {"/getUserList"},method = RequestMethod.GET)
    public ResponseData<List<UserDto>> getUserList()
    {
        ResponseData<List<UserDto>> res=userService.getUserAll();
        return res;
    }
}
