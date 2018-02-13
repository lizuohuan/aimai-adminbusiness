package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * Created by Eric Xie on 2018/1/18 0018.
 */
public class UserCity implements Serializable {

    private Integer id;

    private Integer userId;

    private Integer cityId;


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

    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
