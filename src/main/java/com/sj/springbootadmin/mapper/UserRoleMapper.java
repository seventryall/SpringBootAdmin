package com.sj.springbootadmin.mapper;

import com.sj.springbootadmin.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserRoleMapper {

    //List<Role> getUserRoles(@Param("userID") Integer userID);
}
