package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 最近点击 触发的课程记录 entity
 *  每个用户只会存在一条记录
 * Created by Eric Xie on 2017/8/1 0001.
 */
public class CurriculumNewRecord implements Serializable {


    private Integer id;

    /** 订单ID */
    private Integer orderId;

    /** 用户ID */
    private Integer userId;

    /** 更新时间 */
    private Date updateTime;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 订单ID */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单ID */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 创建时间 */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /** 设置 创建时间 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
