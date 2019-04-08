package com.sj.springbootadmin.service.impl;

import com.sj.springbootadmin.entity.Role;
import com.sj.springbootadmin.mapper.RoleMapper;
import com.sj.springbootadmin.service.BaseService;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.RoleService;
import com.sj.springbootadmin.service.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public ResponseData<List<RoleDto>> getRoleList() {
        ResponseData<List<RoleDto>> res=doInvoke(resp->{
            List<Role> roles=roleMapper.getAllRole();
            List<RoleDto> list=new ArrayList<>();
            roles.forEach(r->{
                RoleDto dto=new RoleDto();
                mapTo(r,dto);
                list.add(dto);
            });
            resp.setData(list);
        });
        return res;
    }

     private void mapTo(Role role,RoleDto dto)
     {
         dto.setID(role.getID());
         dto.setName(role.getName());
         dto.setCode(role.getCode());
         dto.setRemark(role.getRemark());
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         dto.setCreateTime(sdf.format(role.getCreateTime()));
     }

    @Override
    public ResponseData<List<RoleDto>> getUserRoles(Integer userID) {
        ResponseData<List<RoleDto>> res=doInvoke(resp->{
            List<Role> roles=roleMapper.getUserRoles(userID);
            List<RoleDto> list=new ArrayList<>();
            roles.forEach(r->{
                RoleDto dto=new RoleDto();
                mapTo(r,dto);
                list.add(dto);
            });
            resp.setData(list);
        });
        return res;
    }
}
