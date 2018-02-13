package com.magic.aimai.business.entity;

import com.wordnik.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息
 * Created by Eric Xie on 2017/7/13 0013.
 */
@ApiModel
public class SystemInfo implements Serializable {

    /** 主键ID */
    private Integer id;

    /** title */
    private String title;

    /** 摘要 */
    private String digest;

    /** 消息类型 0:资讯推送 */
    private Integer type;

    /** 如果type是 0  则资讯ID */
    private Integer newsId;

    /** 创建时间 */
    private Date createTime;

    /** 是否查看过 0:否  1:是 */
    @ApiModelProperty(value = "是否查看过 0:否  1:是")
    private Integer isChecked;

    /** 是否外链  0:否  1:是 */
    private Integer isLink;

    /** 外链地址 */
    private String linkUrl;

    /** 封面图 */
    private String img;


    /** 获取 主键ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 主键ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 title */
    public String getTitle() {
        return this.title;
    }

    /** 设置 title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 摘要 */
    public String getDigest() {
        return this.digest;
    }

    /** 设置 摘要 */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /** 获取 消息类型 0:资讯推送 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 消息类型 0:资讯推送 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 如果type是 0  则资讯ID */
    public Integer getNewsId() {
        return this.newsId;
    }

    /** 设置 如果type是 0  则资讯ID */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 是否查看过 0:否  1:是 */
    public Integer getIsChecked() {
        return this.isChecked;
    }

    /** 设置 是否查看过 0:否  1:是 */
    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    /** 获取 是否外链  0:否  1:是 */
    public Integer getIsLink() {
        return this.isLink;
    }

    /** 设置 是否外链  0:否  1:是 */
    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    /** 获取 外链地址 */
    public String getLinkUrl() {
        return this.linkUrl;
    }

    /** 设置 外链地址 */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /** 获取 封面图 */
    public String getImg() {
        return this.img;
    }

    /** 设置 封面图 */
    public void setImg(String img) {
        this.img = img;
    }
}
