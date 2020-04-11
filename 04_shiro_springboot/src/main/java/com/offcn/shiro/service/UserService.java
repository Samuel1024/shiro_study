package com.offcn.shiro.service;

import com.offcn.shiro.pojo.User;

public interface UserService {

    User findUserByUserName(String username);
}
