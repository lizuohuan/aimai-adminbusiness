package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.Menu;
import com.magic.aimai.business.entity.RoleMenu;
import com.magic.aimai.business.mapper.IMenuMapper;
import com.magic.aimai.business.mapper.IRoleMenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限 -- 业务
 * @author lzh
 * @create 2017/4/19 14:14
 */
@Service
public class MenuService {

    @Resource
    private IMenuMapper menuMapper;
    @Resource
    private IRoleMenuMapper roleUrlMapper;

    /**
     * 查询所有菜单(权限)
     * @return
     */
    public List<Menu> findAllMenu(Integer roleId){
        List<Menu> menuList = menuMapper.findAllMenu();

        //一级菜单
        List<Menu> menuLevel1 = new ArrayList<Menu>();
        //二级菜单
        List<Menu> menuLevel2 = new ArrayList<Menu>();
        //三级菜单
        List<Menu> menuLevel3 = new ArrayList<Menu>();
        //四级菜单
        List<Menu> menuLevel4 = new ArrayList<Menu>();
        //五级菜单
        List<Menu> menuLevel5 = new ArrayList<Menu>();

        List<RoleMenu> roleUrls = null;
        if (null != roleId) {
            roleUrls = roleUrlMapper.queryUrlByRole(roleId);
        }

        packagingLevelMenu(menuList,menuLevel1,1);
        packagingLevelMenu(menuList,menuLevel2,2);
        packagingLevelMenu(menuList,menuLevel3,3);
        packagingLevelMenu(menuList,menuLevel4,4);
        packagingLevelMenu(menuList,menuLevel5,5);

        packagingMenu(menuLevel4,menuLevel5,roleUrls);
        packagingMenu(menuLevel3,menuLevel4,roleUrls);
        packagingMenu(menuLevel2,menuLevel3,roleUrls);
        packagingMenu(menuLevel1,menuLevel2,roleUrls);

        return menuLevel1;
    }




    /**
     * 获取当前登录人角色的所有权限
     * @param userId
     * @return
     */
    public List<Menu> getRoleMenu(Integer userId) {

        List<Menu> menuList = menuMapper.getRoleMenu(userId);
        //一级菜单
        List<Menu> menuLevel1 = new ArrayList<Menu>();
        //二级菜单
        List<Menu> menuLevel2 = new ArrayList<Menu>();
        //三级菜单
        List<Menu> menuLevel3 = new ArrayList<Menu>();
        //四级菜单
        List<Menu> menuLevel4 = new ArrayList<Menu>();
        //五级菜单
        List<Menu> menuLevel5 = new ArrayList<Menu>();

        packagingLevelMenu(menuList,menuLevel1,1);
        packagingLevelMenu(menuList,menuLevel2,2);
        packagingLevelMenu(menuList,menuLevel3,3);
        packagingLevelMenu(menuList,menuLevel4,4);
        packagingLevelMenu(menuList,menuLevel5,5);

        packagingMenu(menuLevel4,menuLevel5,null);
        packagingMenu(menuLevel3,menuLevel4,null);
        packagingMenu(menuLevel2,menuLevel3,null);
        packagingMenu(menuLevel1,menuLevel2,null);
        return menuLevel1;
    }


    /**
     * 封装 每级菜单 包含子菜单
     * @param m1
     * @param m2
     * @param roleMenus
     */
    public void packagingMenu(List<Menu> m1 ,List<Menu> m2 ,List<RoleMenu> roleMenus) {
        for (Menu menu : m1) {
            if (null != roleMenus && roleMenus.size() > 0) {
                for (RoleMenu r : roleMenus) {
                    if (r.getMenuId().equals(menu.getId())) {
                        menu.setSpread(true);
                    }
                }
            }
            List<Menu> mL = new ArrayList<Menu>();
            out : for (Menu menu1 : m2) {
                if (menu.getId().equals(menu1.getParentId())) {
                    if (null != roleMenus && roleMenus.size() > 0) {
                        for (RoleMenu r : roleMenus) {
                            if (r.getMenuId().equals(menu1.getId())) {
                                menu1.setSpread(true);
                            }
                        }
                    }
                    mL.add(menu1);
                    continue out;
                }
            }
            menu.getChild().addAll(mL);
        }
    }

    /**
     * 封装 分别五级菜单
     * @param m
     * @param mLevel
     * @param level
     */
    public void packagingLevelMenu(List<Menu> m ,List<Menu> mLevel ,Integer level) {
        for (int i = 0 ; i < m.size() ; i ++) {
            if (m.get(i).getLevel().equals(level)) {
                mLevel.add(m.get(i));
                m.remove(i);
                i -- ;
                continue;
            }
        }
    }


}
