package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Eric Xie on 2017/10/30 0030.
 */
public class VideoApprovedRecord implements Serializable {


    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Date createTime;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
