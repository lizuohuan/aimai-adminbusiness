package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 收藏 entity
 * Created by Eric Xie on 2017/7/14 0014.
 */
public class Collect implements Serializable {


    /** 主键ID */
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 收藏类型 枚举 收藏类型 0:资讯收藏 1:课程收藏 2:考题 */
    private Integer type;

    /** 目标ID */
    private Integer targetId;


    /** 获取 主键ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 主键ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 收藏类型 枚举 收藏类型 0:资讯收藏 1:课程收藏 2:考题 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 收藏类型 枚举 收藏类型 0:资讯收藏 1:课程收藏 2:考题 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 目标ID */
    public Integer getTargetId() {
        return this.targetId;
    }

    /** 设置 目标ID */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }
}
