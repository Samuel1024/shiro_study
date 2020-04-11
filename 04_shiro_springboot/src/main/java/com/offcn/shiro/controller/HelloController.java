package com.offcn.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    /**
     * @return
     */
    @RequiresPermissions("user:list")
    @RequestMapping("/isPermission")
    @ResponseBody
    public String isPermission(){
        return "OK";
    }
}
