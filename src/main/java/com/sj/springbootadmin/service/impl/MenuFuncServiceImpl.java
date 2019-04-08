package com.sj.springbootadmin.service.impl;

import com.sj.springbootadmin.entity.Function;
import com.sj.springbootadmin.entity.Menu;
import com.sj.springbootadmin.entity.MenuFunction;
import com.sj.springbootadmin.entity.RoleMenuFunction;
import com.sj.springbootadmin.mapper.FunctionMapper;
import com.sj.springbootadmin.mapper.MenuFuncMapper;
import com.sj.springbootadmin.mapper.MenuMapper;
import com.sj.springbootadmin.mapper.RoleMenuFuncMapper;
import com.sj.springbootadmin.service.BaseService;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.MenuFuncService;
import com.sj.springbootadmin.service.dto.TreeNodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuFuncServiceImpl extends BaseService implements MenuFuncService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private FunctionMapper funcMapper;

    @Autowired
    private MenuFuncMapper menuFuncMapper;

    @Autowired
    private RoleMenuFuncMapper roleMenuFuncMapper;

    @Override
    public ResponseData<List<TreeNodeDto>> getMenuFuncTree() {
        ResponseData<List<TreeNodeDto>> res = doInvoke(resp ->
        {
            List<Menu> menus = menuMapper.getAllMenu();
            List<Function> funcs = funcMapper.getAllFunction();
            List<MenuFunction> menuFuncs = menuFuncMapper.getAllMenuFunc();
            resp.setData(buildMenuFuncTree(menus, funcs, menuFuncs));
        });
        return res;
    }


    private List<TreeNodeDto> buildMenuFuncTree(List<Menu> menus, List<Function> funcs,
                                                List<MenuFunction> menuFuncs) {
        List<TreeNodeDto> nodes = new ArrayList<>();
        if (menus == null)
            return nodes;
        List<Menu> rootsMenu = menus.stream()
                .filter(m -> m.getParentID() == 0)
                .collect(Collectors.toList());
        rootsMenu.forEach(m ->
        {
            TreeNodeDto node = new TreeNodeDto();
            mapTo(m, node);
            nodes.add(node);

            setNodeChildren(menus, funcs, menuFuncs, node);
        });
        return nodes;
    }


    private void setNodeChildren(List<Menu> menus, List<Function> funcs,
                                 List<MenuFunction> menuFuncs, TreeNodeDto pNode) {
        List<Menu> subMenus = menus.stream()
                .filter(m -> m.getParentID() == pNode.getID())
                .collect(Collectors.toList());
        if (subMenus.size() > 0) {
            List<TreeNodeDto> children = new ArrayList<>();
            pNode.setChildren(children);
            subMenus.forEach(m ->
            {
                TreeNodeDto node = new TreeNodeDto();
                mapTo(m, node);
                children.add(node);
                setNodeChildren(menus, funcs, menuFuncs, node);
            });
        } else {
            setFuncChildren(pNode, funcs, menuFuncs);
        }
    }

    private void mapTo(Menu menu, TreeNodeDto node) {
        node.setID(menu.getID());
        node.setLabel(menu.getName());
        node.setLeaf(!menu.getParent());
    }

    private void setFuncChildren(TreeNodeDto pNode, List<Function> funcs,
                                 List<MenuFunction> menuFunc) {
        List<MenuFunction> subMenuFuncs = menuFunc.stream()
                .filter(m -> m.getMenuID() == pNode.getID())
                .collect(Collectors.toList());
        if (subMenuFuncs.size() == 0)
            return;
        List<TreeNodeDto> children = new ArrayList<>();
        pNode.setChildren(children);

        pNode.setLeaf(false);
        subMenuFuncs.forEach(mf ->
        {
            TreeNodeDto node = new TreeNodeDto();
            node.setID(mf.getFunctionID());

            Function func = funcs.stream()
                    .filter(m -> m.getID() == mf.getFunctionID()).findFirst().get();

            node.setLabel(func == null ? "" : func.getName());
            node.setLeaf(true);
            node.setOrderID(func == null ? 0 : func.getOrderID());
            children.add(node);
        });
        List<TreeNodeDto> slist = children.stream().sorted(Comparator.comparing(TreeNodeDto::getOrderID))
                .collect(Collectors.toList());
        pNode.setChildren(slist);

    }

    @Override
    public ResponseData<List<TreeNodeDto>> getUserAuthMenuFuncTree(Integer userID) {
        ResponseData<List<TreeNodeDto>> res = doInvoke(resp ->
        {
            List<Menu> menus = menuMapper.getAllMenu();
            List<Function> funcs = funcMapper.getAllFunction();
            List<MenuFunction> menuFuncs = menuFuncMapper.getAllMenuFunc();
            List<RoleMenuFunction> authMenuFuncs = roleMenuFuncMapper.getUserAllAuthMenuFunc(userID);
            List<TreeNodeDto> nodes = buildMenuFuncTree(menus, funcs, menuFuncs);
            SetTreeChecked(nodes, authMenuFuncs);
            resp.setData(nodes);
        });
        return res;
    }

    private void SetTreeChecked(List<TreeNodeDto> nodes, List<RoleMenuFunction> authMenuFuncs) {
        if (nodes == null)
            return;
        nodes.forEach(pNode ->
        {
            SetNodeChecked(pNode, authMenuFuncs);
        });
    }

    private void SetNodeChecked(TreeNodeDto pNode, List<RoleMenuFunction> authMenuFuncs) {
        if (pNode.getChildren() != null && pNode.getChildren().size() > 0) {
            List<TreeNodeDto> leafNodes = pNode.getChildren().stream().filter(n -> n.getLeaf()).
                    collect(Collectors.toList());
            if (leafNodes.size() > 0) {
                pNode.getChildren().forEach(subNode ->
                {
                    List<RoleMenuFunction> list = authMenuFuncs.stream().filter(x -> x.getMenuID() == pNode.getID()
                            && x.getFunctionID() == subNode.getID()).collect(Collectors.toList());
                    subNode.setChecked(list.size() > 0);
                });
            } else {
                pNode.getChildren().forEach(subNode ->

                            SetNodeChecked(subNode, authMenuFuncs)

                );
            }
        }

    }
}
