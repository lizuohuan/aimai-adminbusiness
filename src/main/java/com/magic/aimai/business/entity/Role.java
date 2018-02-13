package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 角色 entity
 * Created by Eric Xie on 2017/7/12 0012.
 */
public class Role implements Serializable {

    /** 角色ID */
    private Integer id;

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String describe;


    /** 获取 角色ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 角色ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 角色名称 */
    public String getRoleName() {
        return this.roleName;
    }

    /** 设置 角色名称 */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /** 获取 角色描述 */
    public String getDescribe() {
        return this.describe;
    }

    /** 设置 角色描述 */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
