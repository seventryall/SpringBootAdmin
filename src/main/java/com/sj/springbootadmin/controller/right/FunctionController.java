package com.sj.springbootadmin.controller.right;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.FunctionService;
import com.sj.springbootadmin.service.dto.FunctionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionController {

    @Autowired
    private FunctionService funcService;

    @RequestMapping("/index")
    public String index()
    {
        return "right/function/index";
    }

    @RequestMapping("/insert")
    public String insert()
    {
        return "right/function/insert";
    }

    @RequestMapping("/update")
    public String update()
    {
        return "right/function/update";
    }

    @RequestMapping("/detail")
    public String detail()
    {
        return "right/function/detail";
    }

    @ResponseBody
    @RequestMapping(value = {"/getFuncList"},method= RequestMethod.GET)
    public ResponseData<List<FunctionDto>> getFuncList()
    {
        ResponseData<List<FunctionDto>> res=funcService.getFunctionList();
        return res;
    }
}
