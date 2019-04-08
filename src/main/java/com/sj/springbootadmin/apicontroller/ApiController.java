package com.sj.springbootadmin.apicontroller;

import com.sj.springbootadmin.jwt.JwtUtil;
import com.sj.springbootadmin.service.PageList;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.UserService;
import com.sj.springbootadmin.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    /**
     * @api {get} /api/users/token  用户登录
     * @apiName get/users/token
     * @apiGroup user
     * @apiDescription 用户登录获取token
     * @apiParam userName 用户名
     * @apiParam userPwd  密码
     * @apiParamExample {String} 请求示例：
     * ?userName=admin&userPwd=123
     * @apiSuccess (成功 200) {Integer} code 状态码
     * @apiSuccess (成功 200) {String} msg 信息
     * @apiSuccess  (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":"Bearer **********"
     * }
     */
    @GetMapping("/users/token")
    public ResponseData<String> userLogin(@RequestParam("userName") String userName,
                                           @RequestParam("userPwd") String userPwd) {
        ResponseData<String> res=new ResponseData<>();
        ResponseData<UserDto> res2 = userService.getUser(userName, userPwd);
        if(res2.getData()!=null)
        {
            String token= JwtUtil.generateToken(res2.getData().getUserName());
            res.setData(token);
        }
        res.setCode(res2.getCode());
        res.setMsg(res2.getMsg());
        return res;
    }

    /**
     * @api {get} /api/users/{id} 获取单个用户
     * @apiName get/users/id
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 通过id获取单个用户
     * @apiParam id 用户id
     * @apiSuccess  (成功 200) {Integer} code 状态码
     * @apiSuccess  (成功 200) {String} msg 信息
     * @apiSuccess  (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":
     *      {
     *          "id":1,
     *          "userName":"admin",
     *          "userPwd":"123"
     *      }
     * }
     */
    @GetMapping("/users/{id}")
    public ResponseData<UserDto> getUserByID(@PathVariable("id") Integer id) {
        ResponseData<UserDto> res = userService.getUserByID(id);
        return res;
    }

    @GetMapping("/users/page")
    public  ResponseData<PageList<UserDto>> getUserList(@RequestParam(value="pageNum",required =false) Integer pageNum,

                                                       @RequestParam(value="pageSize",required = false) Integer pageSize) {
        ResponseData<PageList<UserDto>> res=null;
        if(pageNum==null||pageSize==null)
        {
            //为了演示，暂时这么处理
            ResponseData<List<UserDto>> res2 = userService.getUserAll();
            res.setCode(res2.getCode());
            res.setMsg(res2.getMsg());
            PageList<UserDto> pageList=new PageList<>();
            pageList.setItems(res2.getData());
            pageList.setPageSize(res2.getData().size());
            res.setData(pageList);
            return res;
        }
        else {
            res = userService.getUserList(pageNum, pageSize);
            return res;
        }

    }

    /**
     * @api {get} /api/users 获取所有用户
     * @apiName get/users
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 获取所有用户
     * @apiSuccess (成功 200) {Integer} code 状态码
     * @apiSuccess  (成功 200) {String} msg 信息
     * @apiSuccess  (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":
     *      [
     *      {
     *          "id":1,
     *          "userName":"admin",
     *          "userPwd":"123"
     *      },
     *      {
     *               "id":2,
     *               "userName":"user",
     *               "userPwd":"123"
     *           },
     *      ]
     * }
     */
    @GetMapping("/users")
    public ResponseData<List<UserDto>> getAll() {
        ResponseData<List<UserDto>> res = userService.getUserAll();
       return res;
   }

    /**
     * @api {get} /api/users/search 查询用户
     * @apiName get/users/search
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 根据条件查询用户
     * @apiParam query 查询条件
     * @apiParamExample {String} 请求示例：
     * ?query={filed1=value1&filed2=value2}
     * @apiSuccess (成功 200) {Integer} code 状态码
     * @apiSuccess (成功 200) {String} msg 信息
     * @apiSuccess (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":
     *      [
     *      {
     *          "id":1,
     *          "userName":"admin",
     *          "userPwd":"123"
     *      },
     *      {
     *               "id":2,
     *               "userName":"user",
     *               "userPwd":"123"
     *           },
     *      ]
     * }
     * @apiSampleRequest off
     */
    @GetMapping("/users/search")
    public ResponseData<List<UserDto>> getByQuery(@RequestParam("query") String query) {
       return null;
    }

    /**
     * @api {post} /api/users 新增用户
     * @apiName post/users
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 新增用户
     * @apiParam userName 用户名
     * @apiParam userPwd  密码
     * @apiParam realName 姓名
     * @apiParam age  年龄
     * @apiParamExample {json} 请求示例：
     * {"userName":"abc","userPwd":"123","realName":"你猜","age":18}
     * @apiSuccess (成功 200) {Integer} code 状态码
     * @apiSuccess (成功 200) {String} msg 信息
     * @apiSuccess (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":1
     * }
     * @apiSampleRequest off
     */
    @PostMapping("/users")
    public ResponseData<Integer> createUser(@RequestBody UserDto user) {
        ResponseData<Integer> res = userService.addUser(user);
        return res;
    }

    /**
     * @api {put} /api/users 更新用户
     * @apiName put/users
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 更新用户
     * @apiParam id 用户id
     * @apiParam userName 用户名
     * @apiParam userPwd  密码
     * @apiParam realName 姓名
     * @apiParam age  年龄
     * @apiParamExample {json} 请求示例：
     * {"id":1,"userName":"abc","userPwd":"123","realName":"你猜","age":18}
     * @apiSuccess (成功 200) {Integer} code 状态码
     * @apiSuccess (成功 200) {String} msg 信息
     * @apiSuccess (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":1
     * }
     * @apiSampleRequest off
     */
    @PutMapping("/users")
    public ResponseData<Integer> updateUser(@RequestBody UserDto user) {
        ResponseData<Integer> res = userService.updateUser(user);
        return res;
    }

//    @PostMapping("/user")
//    public ResponseData<Integer> updateUser(String userStr) {
//        ResponseData<Integer> res = new ResponseData<>();
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            UserDto dto = mapper.readValue(userStr, UserDto.class);
//            res = userService.addUser(dto);
//        } catch (Exception ex) {
//            res.setMsg("传入的参数有误，json解析出错");
//        }
//
//        return res;
//    }

    /**
     * @api {delete} /api/users/{id} 删除单个用户
     * @apiName delete/users/id
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 根据id删除单个用户
     * @apiParam id 用户id
     * @apiSuccess (成功 200) {Integer} code 状态码
     * @apiSuccess (成功 200) {String} msg 信息
     * @apiSuccess (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":1
     * }
     * @apiSampleRequest off
     */
    @DeleteMapping("/users/{id}")
    public ResponseData<Integer> deleteUser(@PathVariable("id") Integer id) {
        ResponseData<Integer> res = userService.deleteUser(id);
        return res;
    }

    /**
     * @api {delete} /api/users 删除多个用户
     * @apiName delete/users
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 根据ids删除多个用户
     * @apiParam ids 多个用户id
     * @apiParamExample {String} 请求示例：
     * ?ids=1,2,3
     * @apiSuccess  (成功 200){Integer} code 状态码
     * @apiSuccess  (成功 200) {String} msg 信息
     * @apiSuccess  (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":1
     * }
     * @apiSampleRequest off
     */
    @DeleteMapping("/users")
    public ResponseData<Integer> deleteUsers(@RequestParam("ids") String ids) {
        ResponseData<Integer> res = userService.deleteUsers(ids);
        return res;
    }

    /**
     * @api {post} /api/users/batch 批量操作
     * @apiName post/users/batch
     * @apiGroup user
     * @apiPermission token
     * @apiDescription 批量操作
     * @apiParam batch 批量json
     * @apiParamExample {json} 请求示例：
     * [{"method":"delete","ids":[1,2,3]}]
     * @apiSuccess (成功 200) {Integer} code 状态码
     * @apiSuccess  (成功 200) {String} msg 信息
     * @apiSuccess  (成功 200) {Object} data 数据
     * @apiSuccessExample {json} 返回:
     * {
     *      "code": 200,
     *      "msg": "",
     *      "data":1
     * }
     * @apiSampleRequest off
     */
    @PostMapping("/users/batch")
    public ResponseData<Integer> batchOperation(String batch) {
       return null;
    }
}
