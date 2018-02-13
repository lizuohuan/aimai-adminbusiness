package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 爱麦公司信息
 * @author lzh
 * @create 2017/8/3 19:53
 */
public class Company implements Serializable {

    /***/
    private Integer id;

    /** 公司名 */
    private String name;

    /** logo */
    private String logo;

    /** 公司介绍 */
    private String introduce;

    /** 联系方式 */
    private String mobile;

    /** 地址 */
    private String address;

    /** 邮箱 */
    private String email;

    /** 传真 */
    private String fax;

    /** 公司网址 */
    private String url;

    /***/
    public Integer getId() {
        return this.id;
    }

    /***/
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 公司名 */
    public String getName() {
        return this.name;
    }

    /** 设置 公司名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 logo */
    public String getLogo() {
        return this.logo;
    }

    /** 设置 logo */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /** 获取 公司介绍 */
    public String getIntroduce() {
        return this.introduce;
    }

    /** 设置 公司介绍 */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /** 获取 联系方式 */
    public String getMobile() {
        return this.mobile;
    }

    /** 设置 联系方式 */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /** 获取 地址 */
    public String getAddress() {
        return this.address;
    }

    /** 设置 地址 */
    public void setAddress(String address) {
        this.address = address;
    }

    /** 获取 邮箱 */
    public String getEmail() {
        return this.email;
    }

    /** 设置 邮箱 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 获取 传真 */
    public String getFax() {
        return this.fax;
    }

    /** 设置 传真 */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /** 获取 公司网址 */
    public String getUrl() {
        return this.url;
    }

    /** 设置 公司网址 */
    public void setUrl(String url) {
        this.url = url;
    }
}
