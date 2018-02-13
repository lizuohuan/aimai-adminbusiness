package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频库
 * @author lzh
 * @create 2017/8/15 10:34
 */
public class VideoWareHouse implements Serializable {

    /***/
    private Integer id;

    /** 视频名 */
    private String name;

    /** 视频存放的地址 */
    private String url;

    /** 是否绑定 */
    private Integer isBand;

    /** 视频的上传时间 */
    private Date createTime;

    /** 课程名 */
    private String curriculumName;

    /** 课件名 */
    private String courseWareName;

    /** 视频长度 秒 */
    private Integer seconds;

    /** 阿里云 流畅版本 */
    private String fd;

    /** 阿里云 标清版本 */
    private String ld;

    /** 阿里云 高清版本 */
    private String sd;

    /** 阿里云 超清版本 */
    private String hd;

    /** 阿里云 原画版本 */
    private String od;

    /** 阿里云服务器视频ID */
    private String videoId;

    /** 阿里云服务器视频封面URL */
    private String coverURL;

    /** 0:表示阿里云服务器的视频，否则本地服务器视频 */
    private Integer flag;


    /***/
    public Integer getId() {
        return this.id;
    }

    /***/
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 视频名 */
    public String getName() {
        return this.name;
    }

    /** 设置 视频名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 视频存放的地址 */
    public String getUrl() {
        return this.url;
    }

    /** 设置 视频存放的地址 */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 获取 是否绑定 */
    public Integer getIsBand() {
        return this.isBand;
    }

    /** 设置 是否绑定 */
    public void setIsBand(Integer isBand) {
        this.isBand = isBand;
    }

    /** 获取 视频的上传时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 视频的上传时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 课程名 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 课件名 */
    public String getCourseWareName() {
        return this.courseWareName;
    }

    /** 设置 课件名 */
    public void setCourseWareName(String courseWareName) {
        this.courseWareName = courseWareName;
    }

    /** 获取 视频长度 秒 */
    public Integer getSeconds() {
        return this.seconds;
    }

    /** 设置 视频长度 秒 */
    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getFd() {
        return this.fd;
    }

    public void setFd(String fd) {
        this.fd = fd;
    }

    public String getLd() {
        return this.ld;
    }

    public void setLd(String ld) {
        this.ld = ld;
    }

    public String getSd() {
        return this.sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getHd() {
        return this.hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getOd() {
        return this.od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    /** 获取 0:表示阿里云服务器的视频，否则本地服务器视频 */
    public Integer getFlag() {
        return this.flag;
    }

    /** 设置 0:表示阿里云服务器的视频，否则本地服务器视频 */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /** 获取 阿里云服务器视频ID */
    public String getVideoId() {
        return this.videoId;
    }

    /** 设置 阿里云服务器视频ID */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /** 获取 阿里云服务器视频封面URL */
    public String getCoverURL() {
        return this.coverURL;
    }

    /** 设置 阿里云服务器视频封面URL */
    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}
