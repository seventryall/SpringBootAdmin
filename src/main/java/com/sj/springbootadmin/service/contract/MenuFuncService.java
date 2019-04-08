package com.sj.springbootadmin.service.contract;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.dto.TreeNodeDto;

import java.util.List;

public interface MenuFuncService {

    ResponseData<List<TreeNodeDto>> getMenuFuncTree();

    ResponseData<List<TreeNodeDto>> getUserAuthMenuFuncTree(Integer userID);
}
