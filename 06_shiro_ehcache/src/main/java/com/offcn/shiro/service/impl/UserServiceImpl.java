package com.offcn.shiro.service.impl;

import com.offcn.shiro.dao.UserInfoRepository;
import com.offcn.shiro.pojo.UserInfo;
import com.offcn.shiro.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userDao;

    @Override
    public UserInfo findUserByUserName(String username) {
        UserInfo userin = userDao.findUserInfoByUsernameEquals(username);
        return userin;
    }
}
