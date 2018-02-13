package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.Role;
import com.magic.aimai.business.mapper.IRoleMapper;
import com.magic.aimai.business.mapper.IUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色 -- 业务
 * @author lzh
 * @create 2017/4/20 16:13
 */
@Service
public class RoleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IRoleMapper roleMapper;
    @Resource
    private IUserMapper userMapper;

    /**
    * 角色列表
    * @return
    */
    public List<Role> list() {
        try {
            return roleMapper.list();
        } catch (Exception e) {
            logger.error("服务器超时，获取角色列表失败",e);
        }
        return null;
    }


    public void updateRole(String roleName,String describe,Integer id){
        Role role = new Role();
        role.setId(id);
        role.setDescribe(describe);
        role.setRoleName(roleName);
        roleMapper.updateRole(role);
    }


    /**
     * 添加角色
     * @param role 角色实体
     */
    public void insert(Role role) {
        try {
            roleMapper.insert(role);
        } catch (Exception e) {
            logger.error("服务器超时,添加失败",e);
        }
    }

    /**
     * 删除角色  以及  此角色的用户
     * @param id
     */
    @Transactional
    public void delete(Integer id){
        userMapper.deleteByRoleId(id);
        roleMapper.delete(id);
    }
}
