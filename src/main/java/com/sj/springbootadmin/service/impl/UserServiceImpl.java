package com.sj.springbootadmin.service.impl;

import com.sj.springbootadmin.entity.User;
import com.sj.springbootadmin.mapper.UserMapper;
import com.sj.springbootadmin.service.BaseService;
import com.sj.springbootadmin.service.PageList;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.UserService;
import com.sj.springbootadmin.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseData<UserDto> getUser(String userName, String userPwd) {
        ResponseData<UserDto> res= doInvoke(resp->{
            User user = userMapper.getUser(userName, userPwd);
            if(user!=null)
            {
                UserDto dto=new UserDto();
                mapTo(user,dto);
                resp.setData(dto);
            }
            else
            {
                resp.setCode(101);
                resp.setMsg("用户名或密码错误");
            }

        });
        return res;
    }

    @Override
    public ResponseData<UserDto> getUserByUserName(String userName) {
        ResponseData<UserDto> res= doInvoke(resp->{
            User user = userMapper.getUserByUserName(userName);
            if(user!=null)
            {
                UserDto dto=new UserDto();
                mapTo(user,dto);
                resp.setData(dto);
            }
        });
        return res;
    }

    @Override
    public ResponseData<UserDto> getUserByID(Integer id) {
        ResponseData<UserDto> res= doInvoke(resp->{
            User user = userMapper.getUserByID(id);
            if(user!=null)
            {
                UserDto dto=new UserDto();
                mapTo(user,dto);
                resp.setData(dto);
            }
        });
        return res;
    }

    @Override
    public ResponseData<PageList<UserDto>> getUserList(Integer pageNum, Integer pageSize) {
        ResponseData<PageList<UserDto>> res=doInvoke(resp->{
            PageList<UserDto> plist=new PageList<>();
            plist.setPageNum(pageNum);
            plist.setPageSize(pageSize);
            plist.setTotalCount(userMapper.selectCount());
            int startNum=(pageNum-1)*pageSize;
            List<User> users = userMapper.getUserList(startNum,pageSize);
            if(users!=null)
            {
                List<UserDto> dtoList=new ArrayList<>();
                users.forEach(u->{
                    UserDto dto=new UserDto();
                    mapTo(u,dto);
                    dtoList.add(dto);
                });
                plist.setItems(dtoList);
            }
            resp.setData(plist);
        });
        return res;
    }

    @Override
    public ResponseData<List<UserDto>> getUserAll() {
        ResponseData<List<UserDto>> res=doInvoke(resp->{
            List<User> users = userMapper.getUserAll();
            if(users!=null)
            {
                List<UserDto> dtoList=new ArrayList<>();
                users.forEach(u->{
                    UserDto dto=new UserDto();
                    mapTo(u,dto);
                    dtoList.add(dto);
                });
                resp.setData(dtoList);
            }
        });
        return res;
    }

    @Override
    public ResponseData<Integer> addUser(UserDto user) {
        ResponseData<Integer> res=doInvoke(resp->{
            User entity=new User();
            mapToEntity(entity,user);
            resp.setData(userMapper.addUser(entity));
            if(resp.getData()>0)
            {
                resp.setMsg("新增成功");
            }
            else
            {
                resp.setMsg("新增失败");
            }
        });
        return res;
    }

    @Override
    public ResponseData<Integer> updateUser(UserDto user) {
        ResponseData<Integer> res=doInvoke(resp->{
            User entity=new User();
            mapToEntity(entity,user);
            resp.setData(userMapper.updateUser(entity));
            if(resp.getData()>0)
            {
                resp.setMsg("更新成功");
            }
            else
            {
                resp.setMsg("更新失败");
            }
        });
        return res;
    }

    @Override
    public ResponseData<Integer> deleteUser(Integer id) {
        ResponseData<Integer> res=doInvoke(resp->{
            resp.setData(userMapper.deleteUser(id));
            if(resp.getData()>0)
            {
                resp.setMsg("删除成功");
            }
            else
            {
                resp.setMsg("删除失败");
            }
        });
        return res;
    }

    @Override
    public ResponseData<Integer> deleteUsers(String ids) {
        ResponseData<Integer> res=doInvoke(resp->{
            resp.setData(userMapper.deleteUsers(ids));
            if(resp.getData()>0)
            {
                resp.setMsg("删除成功");
            }
            else
            {
                resp.setMsg("删除失败");
            }
        });
        return res;
    }

    private void mapTo(User user,UserDto dto)
    {
        dto.setID(user.getID());
        dto.setUserName(user.getUserName());
        dto.setUserPwd(user.getUserPwd());
        dto.setAge(user.getAge());
        dto.setRealName(user.getRealName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAdmin(user.getAdmin());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(user.getCreateTime()!=null) {
            dto.setCreateTime(sdf.format(user.getCreateTime()));
        }
    }

    private void mapToEntity(User user,UserDto dto)
    {
        user.setID(dto.getID());
        user.setUserName(dto.getUserName());
        user.setUserPwd(dto.getUserPwd());
        user.setAge(dto.getAge());
        user.setRealName(dto.getRealName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAdmin(dto.getAdmin());
        user.setCreateTime(new Date());

    }
}
