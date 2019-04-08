package com.sj.springbootadmin.mapper;

import com.sj.springbootadmin.entity.MenuFunction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MenuFuncMapper {

    List<MenuFunction> getAllMenuFunc();
}
