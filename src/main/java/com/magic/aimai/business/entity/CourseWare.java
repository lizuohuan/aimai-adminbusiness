package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 课时、课件  entity
 * Created by Eric Xie on 2017/7/13 0013.
 */
public class CourseWare implements Serializable {

    /** 课件ID */
    private Integer id;

    /** 课件名称 */
    private String courseWareName;

    /** 是否有效 0：无效 1：有效 缺省值 1 */
    private Integer isValid;

    /** 创建者ID */
    private Integer createUserId;

    /** 创建者 */
    private String createUserName;

    /** 讲义地址 以逗号隔开 */
    private String ppt;

    /** 创建时间 */
    private Date createTime;

    /** 课程ID */
    private Integer curriculumId;

    /** 课时下的 视频 */
    private List<Video> videos;

    /** 讲师名称 */
    private String teacherName;

    /** 讲师名称介绍 */
    private String teacherIntroduce;

    /** 封面图 */
    private String cover;

    /** 课程名 */
    private String curriculumName;

    /** 行业名 */
    private String tradeName;

    /** 总高清视频时长 秒 */
    private Integer hdSeconds;

    /** 已学习的视频时长 */
    private Integer expendSeconds;

    /** 学习开始时间 */
    private Date studyStartTime;

    /** 学习完成时间 */
    private Date studyEndTime;

    /** 视频数量 */
    private Integer videoNum;

    /** 习题数量 */
    private Integer examinationNum;

    /** 习题总数量 */
    private Integer examinationTotalNum;

    /** 是否已经练习了  0：没有练习  1：练习 */
    private Integer isFinish;

    /** 试卷id */
    private Integer paperId;

    /** 适用人群 */
    private String applyPerson;

    /** 订单id */
    private String orderId;

    /** 是否可做联系题 0：否  1：可以 */
    private Integer isExamination;

    /** 获取 课件ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 课件ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 课件名称 */
    public String getCourseWareName() {
        return this.courseWareName;
    }

    /** 设置 课件名称 */
    public void setCourseWareName(String courseWareName) {
        this.courseWareName = courseWareName;
    }

    /** 获取 是否有效 0：无效 1：有效 缺省值 1 */
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 是否有效 0：无效 1：有效 缺省值 1 */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /** 获取 创建者ID */
    public Integer getCreateUserId() {
        return this.createUserId;
    }

    /** 设置 创建者ID */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /** 获取 创建者 */
    public String getCreateUserName() {
        return this.createUserName;
    }

    /** 设置 创建者 */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /** 获取 讲义地址 以逗号隔开 */
    public String getPpt() {
        return this.ppt;
    }

    /** 设置 讲义地址 以逗号隔开 */
    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 课程ID */
    public Integer getCurriculumId() {
        return this.curriculumId;
    }

    /** 设置 课程ID */
    public void setCurriculumId(Integer curriculumId) {
        this.curriculumId = curriculumId;
    }

    /** 获取 课时下的 视频 */
    public List<Video> getVideos() {
        return this.videos;
    }

    /** 设置 课时下的 视频 */
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


    /** 获取 讲师名称 */
    public String getTeacherName() {
        return this.teacherName;
    }

    /** 设置 讲师名称 */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /** 获取 讲师名称介绍 */
    public String getTeacherIntroduce() {
        return this.teacherIntroduce;
    }

    /** 设置 讲师名称介绍 */
    public void setTeacherIntroduce(String teacherIntroduce) {
        this.teacherIntroduce = teacherIntroduce;
    }

    /** 获取 封面图 */
    public String getCover() {
        return this.cover;
    }

    /** 设置 封面图 */
    public void setCover(String cover) {
        this.cover = cover;
    }


    /** 获取 课程名 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 行业名 */
    public String getTradeName() {
        return this.tradeName;
    }

    /** 设置 行业名 */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /** 获取 总高清视频时长 秒 */
    public Integer getHdSeconds() {
        return this.hdSeconds;
    }

    /** 设置 总高清视频时长 秒 */
    public void setHdSeconds(Integer hdSeconds) {
        this.hdSeconds = hdSeconds;
    }

    /** 获取 已学习的视频时长 */
    public Integer getExpendSeconds() {
        if (null ==this.expendSeconds) {
            return 0;
        }
        return this.expendSeconds;
    }

    /** 设置 已学习的视频时长 */
    public void setExpendSeconds(Integer expendSeconds) {
        this.expendSeconds = expendSeconds;
    }

    /** 获取 学习开始时间 */
    public Date getStudyStartTime() {
        return this.studyStartTime;
    }

    /** 设置 学习开始时间 */
    public void setStudyStartTime(Date studyStartTime) {
        this.studyStartTime = studyStartTime;
    }

    /** 获取 视频数量 */
    public Integer getVideoNum() {
        return this.videoNum;
    }

    /** 设置 视频数量 */
    public void setVideoNum(Integer videoNum) {
        this.videoNum = videoNum;
    }

    /** 获取 习题数量 */
    public Integer getExaminationNum() {
        return this.examinationNum;
    }

    /** 设置 习题数量 */
    public void setExaminationNum(Integer examinationNum) {
        this.examinationNum = examinationNum;
    }

    /** 获取 是否已经练习了  0：没有练习  1：练习 */
    public Integer getIsFinish() {
        return this.isFinish;
    }

    /** 设置 是否已经练习了  0：没有练习  1：练习 */
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    /** 获取 习题总数量 */
    public Integer getExaminationTotalNum() {
        return this.examinationTotalNum;
    }

    /** 设置 习题总数量 */
    public void setExaminationTotalNum(Integer examinationTotalNum) {
        this.examinationTotalNum = examinationTotalNum;
    }

    /** 获取 试卷id */
    public Integer getPaperId() {
        return this.paperId;
    }

    /** 设置 试卷id */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /** 获取 适用人群 */
    public String getApplyPerson() {
        return this.applyPerson;
    }

    /** 设置 适用人群 */
    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    /** 获取 订单id */
    public String getOrderId() {
        return this.orderId;
    }

    /** 设置 订单id */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /** 获取 学习完成时间 */
    public Date getStudyEndTime() {
        return this.studyEndTime;
    }

    /** 设置 学习完成时间 */
    public void setStudyEndTime(Date studyEndTime) {
        this.studyEndTime = studyEndTime;
    }

    /** 获取 是否可做联系题 0：否  1：可以 */
    public Integer getIsExamination() {
        return this.isExamination;
    }

    /** 设置 是否可做联系题 0：否  1：可以 */
    public void setIsExamination(Integer isExamination) {
        this.isExamination = isExamination;
    }
}
