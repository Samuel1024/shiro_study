package com.offcn.shiro.realm;

import com.offcn.shiro.pojo.SysPermission;
import com.offcn.shiro.pojo.SysRole;
import com.offcn.shiro.pojo.UserInfo;
import com.offcn.shiro.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证加授权的自定义real        AuthorizingRealm
 * 只认证                       AuthenticatingRealm
 *
 */
@Component
public class DbRealm extends AuthorizingRealm {


    @Autowired
    private UserInfoService userInfoService;

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principal.getPrimaryPrincipal();
        for(SysRole role:userInfo.getRoleList()){
            info.addRole(role.getRole());
            for(SysPermission permission:role.getPermissionList()){
               info.addStringPermission(permission.getPermission());
            }
        }
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

        //session管理
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("key","value");

        String username = (String)token.getPrincipal();
        Object credentials = token.getCredentials();
        String password = new String((char[])credentials);

        UserInfo userInfo = userInfoService.findUserByUserName(username);
        if(userInfo != null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getSalt()), getName());
            return info;
        }

        return null;
    }
}
