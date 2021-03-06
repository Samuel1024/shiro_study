package com.offcn.shiro.controller;

import com.offcn.shiro.pojo.UserInfo;
import com.offcn.shiro.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "OK";
    }
}
