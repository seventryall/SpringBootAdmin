package com.sj.springbootadmin.service.impl;

import com.sj.springbootadmin.entity.Function;

import com.sj.springbootadmin.mapper.FunctionMapper;

import com.sj.springbootadmin.service.BaseService;
import com.sj.springbootadmin.service.ResponseData;

import com.sj.springbootadmin.service.contract.FunctionService;
import com.sj.springbootadmin.service.dto.FunctionDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionServiceImpl extends BaseService implements FunctionService {

    @Autowired
    private FunctionMapper funcMapper;

    @Override
    public ResponseData<List<FunctionDto>> getFunctionList() {
        ResponseData<List<FunctionDto>> res=doInvoke(resp->{
            List<Function> funcs=funcMapper.getAllFunction();
            List<FunctionDto> list=new ArrayList<>();
            funcs.forEach(f->{
                FunctionDto dto=new FunctionDto();
                mapTo(f,dto);
                list.add(dto);
            });
            resp.setData(list);
        });
        return res;
    }

     private void mapTo(Function func,FunctionDto dto)
     {
         dto.setID(func.getID());
         dto.setName(func.getName());
         dto.setCode(func.getCode());
         dto.setOrderID(func.getOrderID());
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         dto.setCreateTime(sdf.format(func.getCreateTime()));
     }

}
