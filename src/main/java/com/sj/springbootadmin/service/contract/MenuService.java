package com.sj.springbootadmin.service.contract;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.dto.MenuDto;
import com.sj.springbootadmin.service.dto.TreeNodeDto;

import java.util.List;

public interface MenuService {

    ResponseData<List<MenuDto>> getMenuList();

    ResponseData<List<TreeNodeDto>> getMenuTree();

    ResponseData<List<TreeNodeDto>> getRootMenus();

    ResponseData<List<TreeNodeDto>> getSubMenus(Integer parentID);

    ResponseData<String> buildLeftMenuHtml();

    ResponseData<List<TreeNodeDto>> getUserAuthMenuTree(Integer userID);
}
