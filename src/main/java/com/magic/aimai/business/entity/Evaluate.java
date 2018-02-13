package com.magic.aimai.business.entity;


import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论 entity
 * Created by Eric Xie on 2017/7/17 0017.
 */

@ApiModel
public class Evaluate implements Serializable {


    /** 评论ID */
    @ApiModelProperty(value = "评论ID")
    private Integer id;

    /** 用户ID */
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    /** 用户名 */
    @ApiModelProperty(value = "UserName")
    private String userName;

    /** 用户头像 */
    @ApiModelProperty(value = "photo")
    private String avatar;

    /** 评论内容 */
    @ApiModelProperty(value = "content")
    private String content;

    /** 是否有效  0:无效  1:有效  缺省值 1 */
    @ApiModelProperty(value = "1")
    private Integer isValid;

    /** 创建时间 */
    @ApiModelProperty(value = "CreateTime")
    private Date createTime;

    /** 课程Id */
    @ApiModelProperty(value = "课程 Id")
    private Integer curriculumId;

    /** 课程名 */
    private String curriculumName;

    /** 所在地 */
    private String cityName;

    /** 系统当前时间 */
    private long timeStamp = System.currentTimeMillis();

    /** 获取 评论ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 评论ID */
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

    /** 获取 用户名 */
    public String getUserName() {
        return this.userName;
    }

    /** 设置 用户名 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 获取 用户头像 */
    public String getAvatar() {
        return this.avatar;
    }

    /** 设置 用户头像 */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /** 获取 评论内容 */
    public String getContent() {
        return this.content;
    }

    /** 设置 评论内容 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 是否有效  0:无效  1:有效  缺省值 1 */
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 是否有效  0:无效  1:有效  缺省值 1 */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 课程Id */
    public Integer getCurriculumId() {
        return this.curriculumId;
    }

    /** 设置 课程Id */
    public void setCurriculumId(Integer curriculumId) {
        this.curriculumId = curriculumId;
    }

    /** 获取 系统当前时间 */
    public long getTimeStamp() {
        return this.timeStamp;
    }

    /** 设置 系统当前时间 */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /** 获取 课程名 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 所在地 */
    public String getCityName() {
        return this.cityName;
    }

    /** 设置 所在地 */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
