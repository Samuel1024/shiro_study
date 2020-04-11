package com.offcn.shiro.dao;

import com.offcn.shiro.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    /**
     * 使用mybatis的方式查找用户以及角色、角色对应的权限
     *  5张表，两种方式查找
     *  子查询
     *  外连接
     * @param username
     * @return
     */
    UserInfo findUserInfoByUsernameEquals(String username);
}
