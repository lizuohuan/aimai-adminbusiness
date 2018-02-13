package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色对应的URL
 * Created by Eric Xie on 2017/6/12 0012.
 */
public interface IRoleMenuMapper {

    /**
     * 批量新增
     * @param roleMenus
     * @return
     */
    Integer batchAddRoleUrl(@Param("roleMenus") List<RoleMenu> roleMenus);


    /**
     *  根据角色ID  删除 权限
     * @param roleId
     * @return
     */
    Integer delRoleUrlByRole(@Param("roleId") Integer roleId);


    /**
     *  根据角色ID 查询 该角色下的所有权限
     * @param roleId
     * @return
     */
    List<RoleMenu> queryUrlByRole(@Param("roleId") Integer roleId);


}
