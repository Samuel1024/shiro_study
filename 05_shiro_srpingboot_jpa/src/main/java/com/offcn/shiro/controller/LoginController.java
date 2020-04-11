package com.offcn.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/doLogin")
    public String doLogin(String username,String password,boolean rememberme){
        try {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(username,password,rememberme);
            subject.login(token);
            return "redirect:/showIndex";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/showLogin";

    }

    @RequestMapping("/showIndex")
    public  String showIndex(){
        System.out.println("showIndex");
        return "index";
    }

    @RequestMapping("/showLogin")
    public  String showLogin(){
        System.out.println("showLogin");
        return "login";
    }


}
