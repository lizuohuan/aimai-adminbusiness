package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频状态 记录 entity
 * Created by Eric Xie on 2017/7/17 0017.
 */
public class VideoStatus implements Serializable {

    /** 记录ID */
    private Integer id;

    /** 视频ID */
    private Integer videoId;

    /** 用户ID */
    private Integer userId;

    /**  播放状态 0:开始播放  1:其他异常状态暂停 2:播放完成 */
    private Integer status;

    /** 视频播放的秒 */
    private Integer seconds;

    /** 创建时间 */
    private Date createTime;

    /** 订单id */
    private Integer orderId;

    /** 获取 记录ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 记录ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 视频ID */
    public Integer getVideoId() {
        return this.videoId;
    }

    /** 设置 视频ID */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /** 获取 用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取  播放状态 0:开始播放  1:其他异常状态暂停 2:播放完成 */
    public Integer getStatus() {
        return this.status;
    }

    /** 设置  播放状态 0:开始播放  1:其他异常状态暂停 2:播放完成 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 视频播放的秒 */
    public Integer getSeconds() {
        return this.seconds;
    }

    /** 设置 视频播放的秒 */
    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 订单id */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单id */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
