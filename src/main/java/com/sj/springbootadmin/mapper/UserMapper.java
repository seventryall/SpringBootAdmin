package com.sj.springbootadmin.mapper;

import com.sj.springbootadmin.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    User getUser(@Param("userName") String userName,@Param("userPwd") String userPwd);

    User getUserByUserName(@Param("userName") String userName);

    @Select("select * from sys_user where id=#{id} and (is_deleted is null or is_deleted=0)")
    User getUserByID(@Param("id") Integer id);

    List<User> getUserAll();

    @Select("select * from sys_user where (is_deleted is null or is_deleted=0) " +
            "limit #{startNum},#{pageSize} ")
    List<User> getUserList(@Param("pageNo") Integer startNum,@Param("pageSize") Integer pageSize);

    @Select("select count(*) from sys_user where is_deleted is null or is_deleted=0")
    int selectCount();

    @Insert("insert into sys_user(user_name,user_pwd,real_name,age,phone,create_time) " +
            "values (#{userName},#{userPwd},#{realName},#{age},#{phone},now())")
     int addUser(User user);

    @Update("update sys_user set age=#{age},phone=#{phone} where id=#{id}")
     int updateUser(User user);


    @Update("update sys_user set is_deleted=1 where id=#{id}")
     int deleteUser(int id);

    @Update("update sys_user set is_deleted=1 where id in (#{ids})")
    int deleteUsers(String ids);

}
