package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.RoleMenu;
import com.magic.aimai.business.mapper.IRoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多角色 对应 多URL 业务实体
 * Created by Eric Xie on 2017/6/12 0012.
 */
@Service
public class RoleMenuService {

    @Resource
    private IRoleMenuMapper roleMenuMapper;


    @Transactional
    public void addRoleUrl(List<RoleMenu> roleMenus, Integer roleId) throws Exception{
        roleMenuMapper.delRoleUrlByRole(roleId);
        if (null != roleMenus && roleMenus.size() > 0) {
            roleMenuMapper.batchAddRoleUrl(roleMenus);
        }
    }



    public List<RoleMenu> queryUrlByRole(Integer roleId){
        return roleMenuMapper.queryUrlByRole(roleId);
    }

}
