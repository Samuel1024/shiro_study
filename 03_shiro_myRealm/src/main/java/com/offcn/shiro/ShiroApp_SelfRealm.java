package com.offcn.shiro;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * Hello world!
 *
 */
public class ShiroApp_SelfRealm {
    public static void main( String[] args ) {

        //第一种方式加载配置文件，从配置文件中已经配置了自定义realme
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        //准备好了subject，模拟用户登录
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","123456");
        subject.login(token);
        System.out.println("是否登录成功：："+subject.isAuthenticated());


    }
}
