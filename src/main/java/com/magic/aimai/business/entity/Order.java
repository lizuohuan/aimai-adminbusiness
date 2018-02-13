package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单 entity
 * Created by Eric Xie on 2017/7/14 0014.
 */
public class Order implements Serializable {

    /** 订单ID */
    private Integer id;

    /** 订单号 */
    private String orderNumber;

    /** 用户ID */
    private Integer userId;

    /** 用户 */
    private User user;

    /** 课程ID */
    private Integer curriculumId;

    /** 购买的数量 */
    private Integer number;

    /** 支付状态 0：未支付  1：已支付 */
    private Integer payStatus;

    /** 支付方式 0:支付宝 1:微信  2:公众号支付 3:线下支付 */
    private Integer payMethod;

    /** 第三方 支付 的订单号 */
    private String outTradeNO;

    /** 订单总价 */
    private Double price;

    /** 订单创建时间 */
    private Date createTime;

    /** 如果是线下支付 则 录入订单人 */
    private Integer createUserId;

    /** 如果是线下支付 则 录入订单人 */
    private String createUserName;

    /** 订单对用户 是否有效 0：无效 1：有效 缺省值 1
     * 用户删除订单字段 */
    private Integer userIsValid;

    /** 课程名称 */
    private String curriculumName;

    /** 课程封面 */
    private String cover;

    /** 地区id */
    private Integer cityId;

    /** 地区 */
    private String cityName;

    /** 培训阶段 */
    private String stageName;

    /** 年度 */
    private Date year;

    /** 是否是平台创建  0：否  1：是 */
    private Integer isPlatformCreate;

    /** 角色id */
    private Integer roleId;


    /** 学习状态  1：可以学习   0：停止  1：暂停 */
    private Integer studyStatus;

    // 业务字段

    /** 剩余还未分配的数量 */
    private Integer surplusNumber;

    /** 获取 订单ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 订单ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 订单号 */
    public String getOrderNumber() {
        return this.orderNumber;
    }

    /** 设置 订单号 */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /** 获取 用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 用户 */
    public User getUser() {
        return this.user;
    }

    /** 设置 用户 */
    public void setUser(User user) {
        this.user = user;
    }

    /** 获取 课程ID */
    public Integer getCurriculumId() {
        return this.curriculumId;
    }

    /** 设置 课程ID */
    public void setCurriculumId(Integer curriculumId) {
        this.curriculumId = curriculumId;
    }

    /** 获取 购买的数量 */
    public Integer getNumber() {
        return this.number;
    }

    /** 设置 购买的数量 */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /** 获取 支付状态 0：未支付  1：已支付 */
    public Integer getPayStatus() {
        return this.payStatus;
    }

    /** 设置 支付状态 0：未支付  1：已支付 */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /** 获取 支付方式 0:支付宝 1:微信  2:公众号支付 3:线下支付 */
    public Integer getPayMethod() {
        return this.payMethod;
    }

    /** 设置 支付方式 0:支付宝 1:微信  2:公众号支付 3:线下支付 */
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    /** 获取 第三方 支付 的订单号 */
    public String getOutTradeNO() {
        return this.outTradeNO;
    }

    /** 设置 第三方 支付 的订单号 */
    public void setOutTradeNO(String outTradeNO) {
        this.outTradeNO = outTradeNO;
    }

    /** 获取 订单总价 */
    public Double getPrice() {
        return this.price;
    }

    /** 设置 订单总价 */
    public void setPrice(Double price) {
        this.price = price;
    }

    /** 获取 订单创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 订单创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 如果是线下支付 则 录入订单人 */
    public Integer getCreateUserId() {
        return this.createUserId;
    }

    /** 设置 如果是线下支付 则 录入订单人 */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /** 获取 如果是线下支付 则 录入订单人 */
    public String getCreateUserName() {
        return this.createUserName;
    }

    /** 设置 如果是线下支付 则 录入订单人 */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /** 订单对用户 是否有效 0：无效 1：有效 缺省值 1
     * 用户删除订单字段 */
    public Integer getUserIsValid() {
        return this.userIsValid;
    }

    /** 订单对用户 是否有效 0：无效 1：有效 缺省值 1
     * 用户删除订单字段 */
    public void setUserIsValid(Integer userIsValid) {
        this.userIsValid = userIsValid;
    }

    /** 获取 课程名称 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名称 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 课程封面 */
    public String getCover() {
        return this.cover;
    }

    /** 设置 课程封面 */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /** 获取 地区 */
    public String getCityName() {
        return this.cityName;
    }

    /** 设置 地区 */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /** 获取 地区id */
    public Integer getCityId() {
        return this.cityId;
    }

    /** 设置 地区id */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /** 获取 培训阶段 */
    public String getStageName() {
        return this.stageName;
    }

    /** 设置 培训阶段 */
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    /** 获取 年度 */
    public Date getYear() {
        return this.year;
    }

    /** 设置 年度 */
    public void setYear(Date year) {
        this.year = year;
    }

    /** 获取 是否是平台创建  0：否  1：是 */
    public Integer getIsPlatformCreate() {
        if (null == this.isPlatformCreate) {
            return 0;
        }
        return this.isPlatformCreate;
    }

    /** 设置 是否是平台创建  0：否  1：是 */
    public void setIsPlatformCreate(Integer isPlatformCreate) {
        this.isPlatformCreate = isPlatformCreate;
    }

    /** 获取 角色id */
    public Integer getRoleId() {
        return this.roleId;
    }

    /** 设置 角色id */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 获取 剩余还未分配的数量 */
    public Integer getSurplusNumber() {
        return this.surplusNumber;
    }

    /** 设置 剩余还未分配的数量 */
    public void setSurplusNumber(Integer surplusNumber) {
        this.surplusNumber = surplusNumber;
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
