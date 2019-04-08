package com.sj.springbootadmin.service.contract;

import com.sj.springbootadmin.service.Permission;
import com.sj.springbootadmin.service.ResponseData;

import java.util.List;

public interface AuthService {

    ResponseData<List<Permission>> getUserPermissions(Integer userID);
}
