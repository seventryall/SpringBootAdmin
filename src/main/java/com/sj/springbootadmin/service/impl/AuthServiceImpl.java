package com.sj.springbootadmin.service.impl;

import com.sj.springbootadmin.entity.RoleMenuFunction;
import com.sj.springbootadmin.mapper.RoleMenuFuncMapper;
import com.sj.springbootadmin.service.BaseService;
import com.sj.springbootadmin.service.Permission;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl extends BaseService implements AuthService {

    @Autowired
    private RoleMenuFuncMapper  roleMenuFuncMapper;

    @Override
    public ResponseData<List<Permission>> getUserPermissions(Integer userID) {
       ResponseData<List<Permission>> res=doInvoke(resp->{
           List<RoleMenuFunction> viewList=roleMenuFuncMapper.getUserAllAuthMenuFuncView(userID);
           List<Permission> permissions=new ArrayList<>();
           viewList.forEach(v->{
               Permission p=new Permission();
               p.setName(String.format("%s.%s",v.getMenu()==null?"":v.getMenu().getUrl(),
                      v.getFunction()==null?"":v.getFunction().getCode() ));
               permissions.add(p);
           });
           resp.setData(permissions);
       });
       return res;
    }
}
