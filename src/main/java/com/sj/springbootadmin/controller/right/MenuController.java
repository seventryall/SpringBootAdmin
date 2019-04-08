package com.sj.springbootadmin.controller.right;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.FunctionService;
import com.sj.springbootadmin.service.contract.MenuService;
import com.sj.springbootadmin.service.dto.MenuDto;
import com.sj.springbootadmin.service.dto.TreeNodeDto;
import com.sj.springbootadmin.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private FunctionService funcService;

    @RequestMapping("/index")
    public String index()
    {
        return "right/menu/index";
    }

    @RequestMapping("/insert")
    public String insert(Model model)
    {
        model.addAttribute("funcList",funcService.getFunctionList().getData());
        return "right/menu/insert";
    }

    @RequestMapping("/update")
    public String update()
    {
        return "right/menu/update";
    }

    @RequestMapping("/detail")
    public String detail()
    {
        return "right/menu/detail";
    }

    @ResponseBody
    @RequestMapping(value = {"/getMenuList"},method=RequestMethod.GET)
    public ResponseData<List<MenuDto>> getMenuList()
    {
        ResponseData<List<MenuDto>> res=menuService.getMenuList();
        return res;
    }

    @ResponseBody
    @RequestMapping(value = {"/getMenuTree"},method=RequestMethod.GET)
    public ResponseData<List<TreeNodeDto>> getMenuTree()
    {
        ResponseData<List<TreeNodeDto>> res=menuService.getMenuTree();
        return res;
    }

    @ResponseBody
    @RequestMapping(value = {"/getRootMenus"},method=RequestMethod.GET)
    public ResponseData<List<TreeNodeDto>> getRootMenus()
    {
        ResponseData<List<TreeNodeDto>> res=menuService.getRootMenus();
        return res;
    }

    @ResponseBody
    @RequestMapping(value = {"/getSubMenus"},method=RequestMethod.GET)
    public ResponseData<List<TreeNodeDto>> getSubMenus(@RequestParam("parentID") Integer parentID)
    {
        ResponseData<List<TreeNodeDto>> res=menuService.getSubMenus(parentID);
        return res;
    }

}
