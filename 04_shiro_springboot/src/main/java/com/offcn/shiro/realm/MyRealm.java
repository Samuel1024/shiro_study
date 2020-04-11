package com.offcn.shiro.realm;

import com.offcn.shiro.pojo.User;
import com.offcn.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证加授权的自定义real        AuthorizingRealm
 * 只认证                       AuthenticatingRealm
 *
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //模拟出已经认证过的用户所具有的角色和权限
        info.addRole("role1");
        info.addRole("role2");
        info.addStringPermission("user:add");
        info.addStringPermission("user:update");
        info.addStringPermission("user:list");
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();
        Object credentials = token.getCredentials();
        String password = new String((char[])credentials);

        //1、根据用户名查找用户对象
        User user = userService.findUserByUserName(username);
        //2、根据输入的密码和查找到的秘密创建 authenticationInfo 对象
        if(user!=null){
//            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(credentials, user.getPassword(), "myReal");
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(credentials, user.getPassword(), ByteSource.Util.bytes("samuel"),"myReal");
            return info;

        }

        return null;
    }
}
