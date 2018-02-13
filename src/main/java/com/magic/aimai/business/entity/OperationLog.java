package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 * @author lzh
 * @create 2018/1/22 15:15
 */
public class OperationLog implements Serializable {


    private Integer id;

    /** 操作人 */
    private Integer userId;

    /** 角色id */
    private Integer roleId;

    /** 操作内容 */
    private String content;

    /** 操作时间 */
    private Date createTime;


    /*************** 业务字段 ****************/

    /** 操作人 */
    private String userName;

    /** 角色 */
    private String roleName;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 操作人 */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 操作人 */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 角色id */
    public Integer getRoleId() {
        return this.roleId;
    }

    /** 设置 角色id */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 获取 操作内容 */
    public String getContent() {
        return this.content;
    }

    /** 设置 操作内容 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 操作时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 操作时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 操作人 */
    public String getUserName() {
        return this.userName;
    }

    /** 设置 操作人 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 获取 角色 */
    public String getRoleName() {
        return this.roleName;
    }

    /** 设置 角色 */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
