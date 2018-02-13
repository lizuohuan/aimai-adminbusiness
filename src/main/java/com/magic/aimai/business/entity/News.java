package com.magic.aimai.business.entity;

import net.sf.json.JSONArray;

import java.io.Serializable;
import java.util.Date;

/**
 * 资讯
 * Created by Eric Xie on 2017/7/13 0013.
 */
public class News implements Serializable {


    /** 主键ID */
    private Integer id;

    /** 标题 */
    private String title;

    /** 图片地址 */
    private String image;

    /** 摘要 */
    private String digest;

    /** 富文本内容 */
    private String content;

    /** 来源 */
    private String source;

    /** 责任编辑人 */
    private String editor;

    /** 创建者 */
    private Integer createUserId;

    /** 创建者名字 */
    private String createUserName;

    /** 创建时间 */
    private Date createTime;

    /** 被收藏数量 */
    private Integer collectNum;

    /** 阅读/浏览量 */
    private Integer readNum;

    /** 类型 0行业动态、1重大新闻、2安全事故、3安全常识、4考试、5其他 */
    private Integer type;

    /** 是否推荐 0:否  1:是 */
    private Integer isRecommend;

    /** 外链地址 */
    private String linkUrl;

    /** 是否是外链  0:否   1:是 */
    private Integer isLink;

    /** 所属地区ID */
    private Integer cityId;


    private JSONArray cityJsonAry;

    /** 获取 主键ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 主键ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 标题 */
    public String getTitle() {
        return this.title;
    }

    /** 设置 标题 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 图片地址 */
    public String getImage() {
        return this.image;
    }

    /** 设置 图片地址 */
    public void setImage(String image) {
        this.image = image;
    }

    /** 获取 摘要 */
    public String getDigest() {
        return this.digest;
    }

    /** 设置 摘要 */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /** 获取 富文本内容 */
    public String getContent() {
        return this.content;
    }

    /** 设置 富文本内容 */
    public void setContent(String content) {
        this.content = content;
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

    /** 获取 创建者 */
    public Integer getCreateUserId() {
        return this.createUserId;
    }

    /** 设置 创建者 */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /** 获取 创建者名字 */
    public String getCreateUserName() {
        return this.createUserName;
    }

    /** 设置 创建者名字 */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 被收藏数量 */
    public Integer getCollectNum() {
        return this.collectNum;
    }

    /** 设置 被收藏数量 */
    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }


    /** 获取 阅读量 */
    public Integer getReadNum() {
        return this.readNum;
    }

    /** 设置 阅读量 */
    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    /** 获取 类型 0行业动态、1重大新闻、2安全事故、3安全常识、4考试、5其他 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 类型 0行业动态、1重大新闻、2安全事故、3安全常识、4考试、5其他 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 是否推荐 0:否  1:是 */
    public Integer getIsRecommend() {
        return this.isRecommend;
    }

    /** 设置 是否推荐 0:否  1:是 */
    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
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

    /** 获取 所属地区ID */
    public Integer getCityId() {
        return this.cityId;
    }

    /** 设置 所属地区ID */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public JSONArray getCityJsonAry() {
        return this.cityJsonAry;
    }

    public void setCityJsonAry(JSONArray cityJsonAry) {
        this.cityJsonAry = cityJsonAry;
    }
}
