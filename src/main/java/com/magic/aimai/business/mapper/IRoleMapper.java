package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色 -- 接口
 * @author lzh
 * @create 2017/4/18 9:36
 */
public interface IRoleMapper {


    /**
     * 角色列表
     * @return
     */
    List<Role> list();


    /**
     * 添加角色
     * @param role 角色实体
     * @return
     */
    void insert(Role role);


    /**
     *  后台用户查询角色集合
     * @param userId
     * @return
     */
    List<Role> queryAdminRole(@Param("userId") Integer userId);

    /**
     *  根据ID 更新不为空的字段
     * @param role
     * @return
     */
    Integer updateRole(@Param("role") Role role);


    /**
     * 删除角色
     * @param id
     */
    void delete(@Param("id") Integer id);
}
