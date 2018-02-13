package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 *  角色 对应的URL
 * Created by Eric Xie on 2017/6/12 0012.
 */
public class RoleMenu implements Serializable {

    private Integer id;

    private Integer roleId;

    private Integer menuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleMenu roleUrl = (RoleMenu) o;

        return menuId.equals(roleUrl.menuId);

    }

    @Override
    public int hashCode() {
        return menuId.hashCode();
    }
}
