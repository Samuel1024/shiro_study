package com.offcn.shiro.controller;

import com.offcn.shiro.pojo.UserInfo;
import com.offcn.shiro.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Security;

@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/findUserByUsername")
    public UserInfo findUserByUsername(String username){
        return userInfoService.findUserByUserName(username);
    }


    @RequestMapping("/isPermission")
    @ResponseBody
    @RequiresPermissions("userInfo:view")
    public String isPermission(){
        Session session = SecurityUtils.getSubject().getSession();

        return (String) session.getAttribute("key");
    }
}
