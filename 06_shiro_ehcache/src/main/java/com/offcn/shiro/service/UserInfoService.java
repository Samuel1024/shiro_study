package com.offcn.shiro.service;

import com.offcn.shiro.pojo.UserInfo;

public interface UserInfoService {

    public UserInfo findUserByUserName(String username);
}
