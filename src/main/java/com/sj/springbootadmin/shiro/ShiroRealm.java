package com.sj.springbootadmin.shiro;

import com.sj.springbootadmin.service.Permission;
import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.contract.AuthService;
import com.sj.springbootadmin.service.contract.RoleService;
import com.sj.springbootadmin.service.contract.UserService;
import com.sj.springbootadmin.service.dto.RoleDto;
import com.sj.springbootadmin.service.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 查出是否有此用户
        ResponseData<UserDto> res = userService.getUserByUserName(token.getUsername());
        if (res.getData() != null) {
            UserDto user=res.getData();
            //Session session = SecurityUtils.getSubject().getSession();
            //session.setAttribute("user", user);//成功则放入session
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验

            return new SimpleAuthenticationInfo(user,
                    user.getUserPwd(), getName());
        }
        return null;
    }

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        //logger.info("##################执行Shiro权限认证##################");
        // 获取当前登录输入的用户名，等价于(String)
        //String loginName = (String) super
                //.getAvailablePrincipal(principalCollection);
        // 到数据库查是否有此对象
        //UserDto user = userService.getUserByUserName(loginName).getData();
        UserDto user  = (UserDto)principalCollection.getPrimaryPrincipal();
        if (user != null) {
            // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 用户的角色集合
            Set<String> set = new HashSet<String>();
            ResponseData<List<RoleDto>> res=roleService.getUserRoles(user.getID());
            if(res.getData()!=null)
            {
                res.getData().forEach(r->{
                    set.add(r.getCode());
                });
            }
            else
            {
                if(user.getAdmin())
                {
                    set.add("admin");
                }
            }
            info.setRoles(set);
            // 用户的权限集合

            List<String> pNameList = new ArrayList();
            ResponseData<List<Permission>> pres=authService.getUserPermissions(2); //user.getID()
            if(pres.getData()!=null)
            {
                pres.getData().forEach(p->{
                    pNameList.add(p.getName());
                });
            }
            info.addStringPermissions(pNameList);
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

//    @Override
//    public boolean isPermitted(PrincipalCollection principals, String permission) {
//        UserDto user = (UserDto)principals.getPrimaryPrincipal();
//        return user.getAdmin()|| super.isPermitted(principals, permission);
//    }
}
