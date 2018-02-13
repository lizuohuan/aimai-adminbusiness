package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 学习模块 课程列表 entity
 * Created by Eric Xie on 2017/7/25 0025.
 */
public class CurriculumStudy implements Serializable {

    private static final long serialVersionUID = -7543006557054465977L;

    /** 课程ID */
    private Integer id;

    /** 订单ID */
    private Integer orderId;

    /** 课程封面 */
    private String cover;

    /** 课程名称 */
    private String curriculumName;

    /** 课程习题数量 */
    private Integer examinationNum;

    /** 课程视频数量 */
    private Integer videoNum;

    /** 课程PPT数量 */
    private Integer pptNum;

    /** 课程下视频的总时间 单位:秒 */
    private Integer videoSeconds;

    /** 该课程下 已经学习视频的时间  单位：秒 */
    private Integer finishSeconds;

    /** 购买的总份数 */
    private Integer number;

    /** 已分配的数量 */
    private Integer allocationNum;

    /** 视频购买时的单价 */
    private Double price;

    /** 是否模拟完   0:没有  1:有 */
    private Integer isFinish;

    /** 已通过考试的次数 */
    private Integer passNum;

    /** 未通过考试的次数 */
    private Integer unPassNum;

    /** 试卷ID */
    private Integer paperId;

    /** 试卷用时 */
    private Integer useTime;

    /** 培训阶段 */
    private String stageName;

    /** 年度 */
    private Date year;

    /** 已观看的人数 */
    private Integer watchNum;

    /** 完成考核的人数 */
    private Integer finishExamine;

    /** 公司总人数 */
    private Integer countUser;

    /** 是否看完课程 0:否  1:是 */
    private Integer isFinishCurriculum;

    /** 学习状态  1：可以学习   0：停止  1：暂停 */
    private Integer studyStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }

    /** 获取 课程ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 课程ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 课程封面 */
    public String getCover() {
        return this.cover;
    }

    /** 设置 课程封面 */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /** 获取 课程名称 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名称 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 课程习题数量 */
    public Integer getExaminationNum() {
        return this.examinationNum;
    }

    /** 设置 课程习题数量 */
    public void setExaminationNum(Integer examinationNum) {
        this.examinationNum = examinationNum;
    }

    /** 获取 课程视频数量 */
    public Integer getVideoNum() {
        return this.videoNum;
    }

    /** 设置 课程视频数量 */
    public void setVideoNum(Integer videoNum) {
        this.videoNum = videoNum;
    }

    /** 获取 课程PPT数量 */
    public Integer getPptNum() {
        return this.pptNum;
    }

    /** 设置 课程PPT数量 */
    public void setPptNum(Integer pptNum) {
        this.pptNum = pptNum;
    }

    /** 获取 课程下视频的总时间 单位:秒 */
    public Integer getVideoSeconds() {
        return this.videoSeconds;
    }

    /** 设置 课程下视频的总时间 单位:秒 */
    public void setVideoSeconds(Integer videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    /** 获取 该课程下 已经学习视频的时间  单位：秒 */
    public Integer getFinishSeconds() {
        return this.finishSeconds;
    }

    /** 设置 该课程下 已经学习视频的时间  单位：秒 */
    public void setFinishSeconds(Integer finishSeconds) {
        this.finishSeconds = finishSeconds;
    }

    /** 获取 购买的总份数 */
    public Integer getNumber() {
        return this.number;
    }

    /** 设置 购买的总份数 */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /** 获取 已分配的数量 */
    public Integer getAllocationNum() {
        return this.allocationNum;
    }

    /** 设置 已分配的数量 */
    public void setAllocationNum(Integer allocationNum) {
        this.allocationNum = allocationNum;
    }

    /** 获取 视频购买时的单价 */
    public Double getPrice() {
        return this.price;
    }

    /** 设置 视频购买时的单价 */
    public void setPrice(Double price) {
        this.price = price;
    }

    /** 获取 订单ID */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单ID */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 是否模拟完   0:没有  1:有 */
    public Integer getIsFinish() {
        return this.isFinish;
    }

    /** 设置 是否模拟完   0:没有  1:有 */
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    /** 获取 已通过考试的次数 */
    public Integer getPassNum() {
        return this.passNum;
    }

    /** 设置 已通过考试的次数 */
    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    /** 获取 未通过考试的次数 */
    public Integer getUnPassNum() {
        return this.unPassNum;
    }

    /** 设置 未通过考试的次数 */
    public void setUnPassNum(Integer unPassNum) {
        this.unPassNum = unPassNum;
    }

    /** 获取 试卷ID */
    public Integer getPaperId() {
        return this.paperId;
    }

    /** 设置 试卷ID */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /** 获取 试卷用时 */
    public Integer getUseTime() {
        return this.useTime;
    }

    /** 设置 试卷用时 */
    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
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

    /** 获取 已观看的人数 */
    public Integer getWatchNum() {
        return this.watchNum;
    }

    /** 设置 已观看的人数 */
    public void setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
    }

    /** 获取 是否看完课程 0:否  1:是 */
    public Integer getIsFinishCurriculum() {
        return this.isFinishCurriculum;
    }

    /** 设置 是否看完课程 0:否  1:是 */
    public void setIsFinishCurriculum(Integer isFinishCurriculum) {
        this.isFinishCurriculum = isFinishCurriculum;
    }

    /** 获取 完成考核的人数 */
    public Integer getFinishExamine() {
        return this.finishExamine;
    }

    /** 设置 完成考核的人数 */
    public void setFinishExamine(Integer finishExamine) {
        this.finishExamine = finishExamine;
    }

    /** 获取 公司总人数 */
    public Integer getCountUser() {
        return this.countUser;
    }

    /** 设置 公司总人数 */
    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }
}
