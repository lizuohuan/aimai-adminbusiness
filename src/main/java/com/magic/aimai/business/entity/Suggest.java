package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈
 * Created by Eric Xie on 2017/7/13 0013.
 */
public class Suggest implements Serializable {

    private Integer id;

    private String content;

    private Integer userId;

    private String userName;

    private Date createTime;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
