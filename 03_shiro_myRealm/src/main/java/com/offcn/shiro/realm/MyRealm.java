package com.offcn.shiro.realm;

import com.offcn.shiro.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;


/**
 * 自定义的real,完成自定义的认证
 * 假设：当用户名是zhagsan\密码是123456时那么我们任务是认证通过
 * 实际上：用户名和密码应该是保存在了数据库中，使用伪代码的形式查找数据库
 */
public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //从token中获取用户名和密码
        String username = token.getPrincipal().toString();
        Object credentials = token.getCredentials();
        String inputPwd = new String((char[]) credentials);

        System.out.println("tocken中获取的用户名是：："+username+"  密码分别是：："+inputPwd);

        //判断输入的用户名和密码是否正确
        User user = serviceFindUserByUserName(username);
        if(user!=null){
            //第三个参数，密码的盐
            AuthenticationInfo info = new SimpleAuthenticationInfo(credentials,user.getPassword(),ByteSource.Util.bytes("samuel"),"myreal");
            return info;
        }
        return null;
    }

    /**
     * 模拟从数据库中查找user
     * @param username
     * @return
     */
    private User serviceFindUserByUserName(String username) {

        if("zhangsan".equals(username)){
             User user = new User();
             user.setPassword("c4fe29e3b862a80bb57cb9c2a7d009df"); //迭代三次
             return user;
        }
        return null;

    }
}
