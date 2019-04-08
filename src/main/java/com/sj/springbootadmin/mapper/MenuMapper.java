package com.sj.springbootadmin.mapper;

import com.sj.springbootadmin.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MenuMapper {

    List<Menu> getAllMenu();

    List<Menu> getRootMenus();

    List<Menu> getSubMenus(@Param("parentID") Integer parentID);

    List<Menu> getUserAllAuthMenu(@Param("userID") Integer userID);
}
