package com.sj.springbootadmin.controller.right;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.MenuFuncService;
import com.sj.springbootadmin.service.contract.MenuService;
import com.sj.springbootadmin.service.contract.RoleService;
import com.sj.springbootadmin.service.contract.UserService;
import com.sj.springbootadmin.service.dto.RoleDto;
import com.sj.springbootadmin.service.dto.TreeNodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuFuncService menuFuncService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("userList", userService.getUserAll().getData());
        return "right/auth/index";
    }

    @RequestMapping("/adminTest")
    public String adminTest() {
        return "right/auth/adminTest";
    }

    @RequestMapping("/userTest")
    public String userTest() {
        return "right/auth/userTest";
    }

    @RequestMapping("/authTest")
    public String authTest() {
        return "right/auth/authTest";
    }

    @ResponseBody
    @RequestMapping(value = {"/getAllMenuFuncTree"}, method = RequestMethod.GET)
    public ResponseData<List<TreeNodeDto>> getAllMenuFuncTree() {
        ResponseData<List<TreeNodeDto>> res = menuFuncService.getMenuFuncTree();
        return res;
    }

    @ResponseBody
    @RequestMapping(value = {"/getAllMenuTree"}, method = RequestMethod.GET)
    public ResponseData<List<TreeNodeDto>> getAllMenuTree() {
        ResponseData<List<TreeNodeDto>> res = menuService.getMenuTree();
        return res;
    }

    @ResponseBody
    @RequestMapping(value = {"/getUserAuthMenuTree"}, method = RequestMethod.GET)
    public ResponseData<List<TreeNodeDto>> getUserAuthMenuTree() {
        ResponseData<List<TreeNodeDto>> res = menuService.getUserAuthMenuTree(2);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = {"/getUserAuthMenuFuncTree"}, method = RequestMethod.GET)
    public ResponseData<List<TreeNodeDto>> getUserAuthMenuFuncTree() {
        ResponseData<List<TreeNodeDto>> res = menuFuncService.getUserAuthMenuFuncTree(2);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = {"/getUserRoles"}, method = RequestMethod.GET)
    public ResponseData<List<RoleDto>> getUserRoles() {
        ResponseData<List<RoleDto>> res = roleService.getUserRoles(2);
        return res;
    }

    @RequestMapping("/403")
    public String unauthorizedError(){
        return "right/auth/403";
    }
}
