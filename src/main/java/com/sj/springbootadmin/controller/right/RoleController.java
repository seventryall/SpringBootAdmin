package com.sj.springbootadmin.controller.right;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.RoleService;
import com.sj.springbootadmin.service.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index()
    {
        return "right/role/index";
    }

    @RequestMapping("/insert")
    public String insert()
    {
        return "right/role/insert";
    }

    @RequestMapping("/update")
    public String update()
    {
        return "right/role/update";
    }

    @RequestMapping("/detail")
    public String detail()
    {
        return "right/role/detail";
    }

    @ResponseBody
    @RequestMapping(value = {"/getRoleList"},method= RequestMethod.GET)
    public ResponseData<List<RoleDto>> getRoleList()
    {
        ResponseData<List<RoleDto>> res=roleService.getRoleList();
        return res;
    }
}
