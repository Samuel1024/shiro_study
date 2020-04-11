package com.offcn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 * 加点注释
 */
public class ShiroApp {
    public static void main( String[] args ) {

        // 得到securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        Realm realm = new IniRealm("classpath:shiro.ini");
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        //得到subject
        Subject subject = SecurityUtils.getSubject();

        //认证
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","123456");
        subject.login(token);
        if(subject.isAuthenticated()){
            System.out.println("认证通过");
            //授权;
            boolean isRole1 = subject.hasRole("role11");
            System.out.println("判断用户是否有role1的角色：："+isRole1);
//            List<String> list = new ArrayList<String>(2){{"role2","role1"}};
            List<String> list2 = new ArrayList<String>(2){
                {
                    this.add("222");
                    this.add("333");
                }
            };
            System.out.println(list2);
            List<String> list = new ArrayList<String>(2);
            list.add("role1");
            list.add("role22");
            boolean[] booleans = subject.hasRoles(list);
            System.out.println(booleans.toString());

            //判断是否有权限
            try {
                subject.checkPermission("permission11");
            }catch (UnauthorizedException e){
                System.out.println("UnauthorizedException::"+"没有指定的权限");
                e.printStackTrace();
            }


        }else{
            System.out.println("认证未通过");
        }

    }
}
