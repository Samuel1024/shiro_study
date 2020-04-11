package com.offcn.shiro.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.mapping.FetchProfile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long uid;

    @Column(unique = true)
    private String username;

    private String password;

    //盐
    private String salt;

    private byte  state; // 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,

    /**
     * 用户和角色的关系是多对多
     *  查找用户的时候将用户的角色一并查找出来
     */
    @ManyToMany(fetch=FetchType.EAGER) //从数据库中直接加载
    @JoinTable(name="SysUserRole",
              joinColumns = {@JoinColumn(name="uid")},
              inverseJoinColumns = {@JoinColumn(name="roleId")})
    private List<SysRole> roleList;


}
