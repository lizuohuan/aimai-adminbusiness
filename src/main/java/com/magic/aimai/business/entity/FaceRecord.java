package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 人脸验证 记录 entity
 * Created by Eric Xie on 2017/7/25 0025.
 */
public class FaceRecord implements Serializable {

    private static final long serialVersionUID = 6395348543276241259L;

    /** 主键ID */
    private Integer id;

    /** 视频ID */
    private Integer videoId;

    /** 验证状态  0:失败  1:成功 */
    private Integer status;

    /** 用户ID */
    private Integer userId;

    /** 验证的人脸 图片地址 */
    private String faceImage;

    /** 人脸验证的时间点 单位 秒 */
    private Integer videoSecond;

    /** 创建时间 */
    private Date createTime;

    /** 订单id */
    private Integer orderId;

    /** 课件名 */
    private String courseWareName;

    /** 视频名 */
    private String videoName;

    /** 获取 主键ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 主键ID */
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

    /** 获取 验证状态  0:失败  1:成功 */
    public Integer getStatus() {
        return this.status;
    }

    /** 设置 验证状态  0:失败  1:成功 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 验证的人脸 图片地址 */
    public String getFaceImage() {
        return this.faceImage;
    }

    /** 设置 验证的人脸 图片地址 */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 人脸验证的时间点 单位 秒 */
    public Integer getVideoSecond() {
        return this.videoSecond;
    }

    /** 设置 人脸验证的时间点 单位 秒 */
    public void setVideoSecond(Integer videoSecond) {
        this.videoSecond = videoSecond;
    }

    /** 获取 订单id */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单id */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 课件名 */
    public String getCourseWareName() {
        return this.courseWareName;
    }

    /** 设置 课件名 */
    public void setCourseWareName(String courseWareName) {
        this.courseWareName = courseWareName;
    }

    /** 获取 视频名 */
    public String getVideoName() {
        return this.videoName;
    }

    /** 设置 视频名 */
    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
