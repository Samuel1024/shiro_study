package com.offcn.shiro.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    private String  description;  // 角色描述,UI 界面显示使用
    private Boolean  available = Boolean. FALSE ;  // 是否可用,如果不可用将不会添加给用户

    @ManyToMany
    @JoinTable(name="SysUserRole",
            joinColumns = {@JoinColumn(name="roleId")},
            inverseJoinColumns = {@JoinColumn(name="uid")})
    private List<UserInfo> userInfoList;

    /**
     * 角色和权限的关系是多对多
     */
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="SysRolePermission",
            joinColumns = {@JoinColumn(name="roleId")},
            inverseJoinColumns = {@JoinColumn(name="permissionId")})
    private List<SysPermission> permissionList;
}
