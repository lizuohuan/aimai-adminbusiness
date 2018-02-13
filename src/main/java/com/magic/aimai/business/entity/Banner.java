package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Eric Xie on 2017/7/24 0024.
 */
public class Banner implements Serializable {

    private static final long serialVersionUID = -3561014420176588513L;

    /** BannerId */
    private Integer id;

    /** 移动端banner图 */
    private String apiImage;

    /** PC端banner图 */
    private String pcImage;

    /** title */
    private String title;

    /** 内容 */
    private String content;

    /** 创建时间 */
    private Date createTime;

    /** 有效期 包含当天 */
    private Date validity;

    /** 创建者 */
    private Integer createUserId;

    /** 来源 */
    private String source;

    /** 责任编辑人 */
    private String editor;

    /** 外链地址 */
    private String linkUrl;

    /** 是否是外链  0:否   1:是 */
    private Integer isLink;

    /** PC广告: 0  banner: 1 */
    private Integer isBanner;

    /** 如果是PC广告 则位置  值为：1 上，2 左，3 下 同一位置只能存在一个广告 */
    private Integer location;


    /** 获取 BannerId */
    public Integer getId() {
        return this.id;
    }

    /** 设置 BannerId */
    public void setId(Integer id) {
        this.id = id;
    }


    /** 获取 移动端banner图 */
    public String getApiImage() {
        return this.apiImage;
    }

    /** 设置 移动端banner图 */
    public void setApiImage(String apiImage) {
        this.apiImage = apiImage;
    }

    /** 获取 PC端banner图 */
    public String getPcImage() {
        return this.pcImage;
    }

    /** 设置 PC端banner图 */
    public void setPcImage(String pcImage) {
        this.pcImage = pcImage;
    }

    /** 获取 title */
    public String getTitle() {
        return this.title;
    }

    /** 设置 title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 内容 */
    public String getContent() {
        return this.content;
    }

    /** 设置 内容 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 创建者 */
    public Integer getCreateUserId() {
        return this.createUserId;
    }

    /** 设置 创建者 */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /** 获取 来源 */
    public String getSource() {
        return this.source;
    }

    /** 设置 来源 */
    public void setSource(String source) {
        this.source = source;
    }

    /** 获取 责任编辑人 */
    public String getEditor() {
        return this.editor;
    }

    /** 设置 责任编辑人 */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /** 获取 外链地址 */
    public String getLinkUrl() {
        return this.linkUrl;
    }

    /** 设置 外链地址 */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /** 获取 是否是外链  0:否   1:是 */
    public Integer getIsLink() {
        return this.isLink;
    }

    /** 设置 是否是外链  0:否   1:是 */
    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    /** 获取 有效期 包含当天 */
    public Date getValidity() {
        return this.validity;
    }

    /** 设置 有效期 包含当天 */
    public void setValidity(Date validity) {
        this.validity = validity;
    }

    /** 获取 PC广告: 0  banner: 1 */
    public Integer getIsBanner() {
        return this.isBanner;
    }

    /** 设置 PC广告: 0  banner: 1 */
    public void setIsBanner(Integer isBanner) {
        this.isBanner = isBanner;
    }

    /** 如果是PC广告 则位置  值为：1 上，2 左，3 下 同一位置只能存在一个广告 */
    public Integer getLocation() {
        return this.location;
    }

    /** 如果是PC广告 则位置  值为：1 上，2 左，3 下 同一位置只能存在一个广告 */
    public void setLocation(Integer location) {
        this.location = location;
    }
}
