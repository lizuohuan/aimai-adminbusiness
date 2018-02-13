package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 视频
 * Created by Eric Xie on 2017/7/13 0013.
 */
public class Video implements Serializable {

    /** 带删除的视频缓存key */
    public final static String video_session_delete = "video_session_delete";


    /** 视频ID */
    private Integer id;

    /** 视频名 */
    private String name;

    /** 高清视频地址 */
    private String highDefinition;

    /** 高清视频时间 单位 秒 */
    private Integer highDefinitionSeconds;

    /** 流畅视频地址 */
    private String lowDefinition;

    /** 流畅视频时间 单位 秒 */
    private Integer lowDefinitionSeconds;

    /** 课时ID */
    private Integer courseWareId;

    /** 封面图 */
    private String cover;

    /** 当前用户对此视频的最新播放进度 */
    private VideoStatus videoStatus;

    /** 高清视频来源  0：直接上传  1：从视频库中选择 2:从云视频库中选择 */
    private Integer sourceHigh;

    /** sourceHigh = 1 : 高清视频id */
    private Integer videoWareHouseHighId;

    /** 流程视频来源  0：直接上传  1：从视频库中选择  2:从云视频库中选择 */
    private Integer sourceLow;

    /** sourceLow = 1 : 流畅视频id */
    private Integer videoWareHouseLowId;


    /** 获取 视频ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 视频ID */
    public void setId(Integer id) {
        this.id = id;
    }


    /** 获取 高清视频地址 */
    public String getHighDefinition() {
        return this.highDefinition;
    }

    /** 设置 高清视频地址 */
    public void setHighDefinition(String highDefinition) {
        this.highDefinition = highDefinition;
    }

    /** 获取 高清视频时间 单位 秒 */
    public Integer getHighDefinitionSeconds() {
        return this.highDefinitionSeconds;
    }

    /** 设置 高清视频时间 单位 秒 */
    public void setHighDefinitionSeconds(Integer highDefinitionSeconds) {
        this.highDefinitionSeconds = highDefinitionSeconds;
    }

    /** 获取 流畅视频地址 */
    public String getLowDefinition() {
        return this.lowDefinition;
    }

    /** 设置 流畅视频地址 */
    public void setLowDefinition(String lowDefinition) {
        this.lowDefinition = lowDefinition;
    }

    /** 获取 流畅视频时间 单位 秒 */
    public Integer getLowDefinitionSeconds() {
        return this.lowDefinitionSeconds;
    }

    /** 设置 流畅视频时间 单位 秒 */
    public void setLowDefinitionSeconds(Integer lowDefinitionSeconds) {
        this.lowDefinitionSeconds = lowDefinitionSeconds;
    }

    /** 获取 课时ID */
    public Integer getCourseWareId() {
        return this.courseWareId;
    }

    /** 设置 课时ID */
    public void setCourseWareId(Integer courseWareId) {
        this.courseWareId = courseWareId;
    }

    /** 获取 视频名 */
    public String getName() {
        return this.name;
    }

    /** 设置 视频名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 封面图 */
    public String getCover() {
        return this.cover;
    }

    /** 设置 封面图 */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /** 获取 当前用户对此视频的最新播放进度 */
    public VideoStatus getVideoStatus() {
        return this.videoStatus;
    }

    /** 设置 当前用户对此视频的最新播放进度 */
    public void setVideoStatus(VideoStatus videoStatus) {
        this.videoStatus = videoStatus;
    }



    /** 获取 高清视频id */
    public Integer getVideoWareHouseHighId() {
        return this.videoWareHouseHighId;
    }

    /** 设置 高清视频id */
    public void setVideoWareHouseHighId(Integer videoWareHouseHighId) {
        this.videoWareHouseHighId = videoWareHouseHighId;
    }

    /** 获取 流畅视频id */
    public Integer getVideoWareHouseLowId() {
        return this.videoWareHouseLowId;
    }

    /** 设置 流畅视频id */
    public void setVideoWareHouseLowId(Integer videoWareHouseLowId) {
        this.videoWareHouseLowId = videoWareHouseLowId;
    }

    /** 获取 高清视频来源  0：直接上传  1：从视频库中选择 */
    public Integer getSourceHigh() {
        return this.sourceHigh;
    }

    /** 设置 高清视频来源  0：直接上传  1：从视频库中选择 */
    public void setSourceHigh(Integer sourceHigh) {
        this.sourceHigh = sourceHigh;
    }

    /** 获取 流程视频来源  0：直接上传  1：从视频库中选择 */
    public Integer getSourceLow() {
        return this.sourceLow;
    }

    /** 设置 流程视频来源  0：直接上传  1：从视频库中选择 */
    public void setSourceLow(Integer sourceLow) {
        this.sourceLow = sourceLow;
    }
}
