package com.sj.springbootadmin.service.contract;

import com.sj.springbootadmin.service.PageList;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.dto.UserDto;

import java.util.List;

public interface UserService {

    ResponseData<UserDto> getUser(String userName, String userPwd);
    ResponseData<UserDto> getUserByUserName(String userName);
    ResponseData<UserDto> getUserByID(Integer id);
    ResponseData<PageList<UserDto>> getUserList(Integer pageNum, Integer pageSize);
    ResponseData<List<UserDto>> getUserAll();
    ResponseData<Integer> addUser(UserDto user);
    ResponseData<Integer> updateUser(UserDto user);
    ResponseData<Integer> deleteUser(Integer id);
    ResponseData<Integer> deleteUsers(String ids);
}
