package com.sj.springbootadmin.service.impl;


import com.sj.springbootadmin.entity.Menu;
import com.sj.springbootadmin.mapper.MenuMapper;
import com.sj.springbootadmin.service.BaseService;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.MenuService;
import com.sj.springbootadmin.service.dto.MenuDto;
import com.sj.springbootadmin.service.dto.TreeNodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends BaseService implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public ResponseData<List<MenuDto>> getMenuList() {
        ResponseData<List<MenuDto>> res = doInvoke(resp ->
        {
            List<Menu> menus = menuMapper.getAllMenu();
            List<MenuDto> list = new ArrayList<>();
            menus.forEach(m -> {
                MenuDto dto = new MenuDto();
                mapTo(m, dto);
                list.add(dto);
            });
            resp.setCount(list.size());
            resp.setData(list);
        });
        return res;
    }

    @Override
    public ResponseData<List<TreeNodeDto>> getMenuTree() {
        ResponseData<List<TreeNodeDto>> res = doInvoke(resp -> {
            List<Menu> menus = menuMapper.getAllMenu();
            if (menus != null) {
                List<TreeNodeDto> list = genMenuTree(menus);
                resp.setData(list);
            }
        });
        return res;
    }

    @Override
    public ResponseData<List<TreeNodeDto>> getRootMenus() {
        ResponseData<List<TreeNodeDto>> res = doInvoke(resp ->
        {
            List<Menu> menus = menuMapper.getRootMenus();
            List<TreeNodeDto> roots = new ArrayList<>();
            menus.forEach(m -> {
                TreeNodeDto node = new TreeNodeDto();
                node.setID(m.getID());
                node.setLabel(m.getName());
                node.setLeaf(!m.getParent());
                roots.add(node);

            });
            resp.setData(roots);
        });
        return res;
    }

    @Override
    public ResponseData<List<TreeNodeDto>> getSubMenus(Integer parentID) {
        ResponseData<List<TreeNodeDto>> res = doInvoke(resp ->
        {
            List<Menu> menus = menuMapper.getSubMenus(parentID);
            List<TreeNodeDto> subMenus = new ArrayList<>();
            menus.forEach(m -> {
                TreeNodeDto node = new TreeNodeDto();
                mapToNode(m, node);
                subMenus.add(node);
            });
            resp.setData(subMenus);
        });
        return res;
    }

    @Override
    public ResponseData<String> buildLeftMenuHtml() {
        ResponseData<String> res = doInvoke(resp ->
        {
            StringBuilder sb = new StringBuilder();
            ResponseData<List<TreeNodeDto>> menus = getMenuTree();
            menus.getData().forEach(menu ->
            {
                sb.append("<li class=\"layui-nav-item\">");
                if (menu.getLeaf()) {
                    sb.append(String.format("<a href=\"javascript:;\" lay-id=\"%1$s\" tab-text=\"%2$s\">%2$s</a>",
                            menu.getUrl(), menu.getLabel()));
                } else {
                    sb.append(String.format("<a href=\"javascript:;\">%s</a>", menu.getLabel()));
                }
                buildMenuHtml(sb, menu);
                sb.append("</li>");

            });
            resp.setData(sb.toString());
        });
        return res;
    }

    @Override
    public ResponseData<List<TreeNodeDto>> getUserAuthMenuTree(Integer userID) {
        ResponseData<List<TreeNodeDto>> res = doInvoke(resp ->
        {
            List<Menu> menus = menuMapper.getUserAllAuthMenu(userID);
            resp.setData(genMenuTree(menus));
        });
        return res;
    }

    private void buildMenuHtml(StringBuilder sb, TreeNodeDto menu) {
        if (menu.getChildren() != null && menu.getChildren().size() > 0) {
            sb.append("<dl class=\"layui-nav-child\">");
            menu.getChildren().forEach(subMenu -> {
                sb.append("<dd>");
                if (subMenu.getLeaf()) {
                    sb.append(String.format("<a href=\"#\" lay-id =\"%1$s\" tab-text=\"%2$s\">%2$s</a>",
                            subMenu.getUrl(), subMenu.getLabel()));
                } else {
                    sb.append(String.format("<a href=\"#\">%s</a>", subMenu.getLabel()));
                    buildMenuHtml(sb, subMenu);
                }
                sb.append("</dd>");
            });
            sb.append("</dl>");
        }
    }

    private List<TreeNodeDto> genMenuTree(List<Menu> menus) {
        List<TreeNodeDto> nodes = new ArrayList<>();
        if (menus == null)
            return nodes;
        List<Menu> roots = null;
        roots = menus.stream()
                .filter(m -> m.getParentID() == 0)
                .collect(Collectors.toList());

        roots.forEach(m -> {
            TreeNodeDto rootNode = new TreeNodeDto();
            mapToNode(m, rootNode);
            nodes.add(rootNode);
            setNodeChildren(menus, m.getID(), rootNode);
        });

        return nodes;
    }

    private void setNodeChildren(List<Menu> menus, Integer parentID, TreeNodeDto pNode) {
        List<Menu> subMenus = null;
        subMenus = menus.stream()
                .filter(m -> m.getParentID() == parentID)
                .collect(Collectors.toList());
        if (subMenus != null && !subMenus.isEmpty()) {
            List<TreeNodeDto> children = new ArrayList<>();
            pNode.setChildren(children);
            subMenus.forEach((Menu m) -> {
                TreeNodeDto node = new TreeNodeDto();
                mapToNode(m, node);
                children.add(node);
                setNodeChildren(menus, m.getID(), node);
            });
        }
    }

    private void mapToNode(Menu menu, TreeNodeDto node) {
        node.setID(menu.getID());
        node.setLabel(menu.getName());
        node.setLeaf(!menu.getParent());
        node.setUrl(menu.getUrl());
    }

    private void mapTo(Menu menu, MenuDto dto) {
        dto.setID(menu.getID());
        dto.setName(menu.getName());
        dto.setUrl(menu.getUrl());
        dto.setParentID(menu.getParentID());
        dto.setParentName(menu.getParentMenu() == null ? "0" : menu.getParentMenu().getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setCreateTime(sdf.format(menu.getCreateTime()));
    }
}
