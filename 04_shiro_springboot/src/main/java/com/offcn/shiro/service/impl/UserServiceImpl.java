package com.offcn.shiro.service.impl;

import com.offcn.shiro.pojo.User;
import com.offcn.shiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public User findUserByUserName(String username) {
       if("zhangsan".equals(username)){
           return  new User(1L,"zhangsan","c4fe29e3b862a80bb57cb9c2a7d009df");
       }
       return null;
    }
}
