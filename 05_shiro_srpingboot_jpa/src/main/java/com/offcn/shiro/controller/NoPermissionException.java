package com.offcn.shiro.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class NoPermissionException {

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String   handelShiroException(){
        return "无访问权限";
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String   ShiroAuthorizationException(){
        return "认证异常";
    }
}
