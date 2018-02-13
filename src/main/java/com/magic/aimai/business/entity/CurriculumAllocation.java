package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 分配课程 从订单数量中
 * Created by Eric Xie on 2017/7/14 0014.
 */
public class CurriculumAllocation implements Serializable {

    /** 分配ID */
    private Integer id;

    /** 被分配者用户 */
    private Integer userId;

    /** 分配用户 */
    private String userName;

    /** 从订单中分配 */
    private Integer orderId;

    /** 分配的数量 */
    private Integer number;

    /** 分配时间 */
    private Date createTime;

    /** 手机号 */
    private String phone;

    /** 学习状态  1：可以学习   0：停止  1：暂停 */
    private Integer studyStatus;

    /** 获取 分配ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 分配ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 被分配者用户 */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 被分配者用户 */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 从订单中分配 */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 从订单中分配 */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 分配的数量 */
    public Integer getNumber() {
        return this.number;
    }

    /** 设置 分配的数量 */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /** 获取 分配时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 分配时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 分配用户 */
    public String getUserName() {
        return this.userName;
    }

    /** 设置 分配用户 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 获取 手机号 */
    public String getPhone() {
        return this.phone;
    }

    /** 设置 手机号 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 获取 学习状态  1：可以学习   0：停止  1：暂停 */
    public Integer getStudyStatus() {
        return this.studyStatus;
    }

    /** 设置 学习状态  1：可以学习   0：停止  1：暂停 */
    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }
}
