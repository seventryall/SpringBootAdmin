package com.sj.springbootadmin.service.contract;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.dto.RoleDto;

import java.util.List;

public interface RoleService {

    ResponseData<List<RoleDto>> getRoleList();

    ResponseData<List<RoleDto>> getUserRoles(Integer userID);
}
