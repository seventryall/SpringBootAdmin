package com.sj.springbootadmin.mapper;

import com.sj.springbootadmin.entity.RoleMenuFunction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMenuFuncMapper {

    List<RoleMenuFunction> getAllRoleMenuFunc();

    List<RoleMenuFunction> getUserAllAuthMenuFunc(@Param("userID") Integer userID);

    List<RoleMenuFunction> getUserAllAuthMenuFuncView(@Param("userID") Integer userID);
}
