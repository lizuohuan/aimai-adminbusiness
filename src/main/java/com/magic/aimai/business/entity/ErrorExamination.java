package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 错题集 entity
 * Created by Eric Xie on 2017/8/2 0002.
 */
public class ErrorExamination implements Serializable {

    /** 记录ID */
    private Integer id;

    /** 习题ID */
    private Integer examinationId;

    /** 用户ID */
    private Integer userId;

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

    /** 获取 习题ID */
    public Integer getExaminationId() {
        return this.examinationId;
    }

    /** 设置 习题ID */
    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    /** 获取 用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 订单ID */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单ID */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorExamination that = (ErrorExamination) o;

        if (!examinationId.equals(that.examinationId)) return false;
        if (!userId.equals(that.userId)) return false;
        return orderId.equals(that.orderId);

    }

    @Override
    public int hashCode() {
        int result = examinationId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + orderId.hashCode();
        return result;
    }
}
