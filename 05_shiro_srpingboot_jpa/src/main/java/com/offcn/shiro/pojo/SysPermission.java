package com.offcn.shiro.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class SysPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id; // 主键.
    private String  name; // 名称.
    @Column(columnDefinition =  "enum('menu','button')")
    private String  resourceType; // 资源类型，[menu|button]
    private String  url; // 资源路径.
    private String  permission;  // 权限字符串,menu 例子：role:*，button 例子：role:create,role:update,role:delete,role:view
    private Long  parentId;  // 父编号
    private String  parentIds;  // 父编号列表
    private Boolean  available = Boolean.FALSE;

}
