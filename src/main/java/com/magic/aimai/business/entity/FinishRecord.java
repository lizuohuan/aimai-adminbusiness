package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 完成记录 entity
 * Created by Eric Xie on 2017/7/26 0026.
 */
public class FinishRecord implements Serializable {

    private static final long serialVersionUID = -3290550970911105772L;

    /** 记录ID */
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 目标ID */
    private Integer targetId;

    /** 记录类型 0：课件 1：课程 */
    private Integer type;

    /** 创建时间 | 完成时间 */
    private Date createTime;

    /** 订单ID */
    private Integer orderId;


    /** 获取 记录ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 记录ID */
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

    /** 获取 目标ID */
    public Integer getTargetId() {
        return this.targetId;
    }

    /** 设置 目标ID */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /** 获取 记录类型 0：课件 1：课程 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 记录类型 0：课件 1：课程 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 创建时间 | 完成时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 | 完成时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 订单ID */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单ID */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
