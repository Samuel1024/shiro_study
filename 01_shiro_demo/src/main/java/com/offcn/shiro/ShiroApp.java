package com.offcn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Hello world!
 *
 */
public class ShiroApp {
    /**
     * 读取配置文件完成简单的登录操作
     * 1、得到配置文件
     * 2、获取subject和SecurityManager对象
     * 3、subject.login(token);
     * @param args
     */
    public static void main( String[] args ) {

        //使用老版本api得到securityManager
//        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);

        //使用iniRealm方式得到securityManager
        SecurityManager securityManager = new DefaultSecurityManager();
        //securityManager加载shiro.ini
        Realm realm = new IniRealm("classpath:shiro.ini");
        ((DefaultSecurityManager) securityManager).setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        try {
            //web 项目时，用户名和密码是客户端表单传递过来的用户名和密码。
            AuthenticationToken token = new UsernamePasswordToken("zhangsan", "123456");
        //login() 方法没有返回值，只能通过是否有异常判断是否登录成功。
            subject.login(token);
            if(subject.isAuthenticated()){
                System.out.println("登录成功");
            }
        } catch (UnknownAccountException e) {
            System.out.println("账号 不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println(" 密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


    }
}
