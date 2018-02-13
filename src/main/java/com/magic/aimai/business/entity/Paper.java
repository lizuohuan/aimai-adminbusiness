package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 试卷 entity
 * Created by Eric Xie on 2017/7/21 0021.
 */
public class Paper implements Serializable {

    private static final long serialVersionUID = 5028951403091539465L;

    /** 试卷ID */
    private Integer id;

    /** 试卷名称 */
    private String paperTitle;

    /** 绑定类型：0：练习题  1：模拟题 2：考试题 */
    private Integer type;

    /** 试卷绑定的ID，如果是练习题 则是课件ID 其他则是课程ID */
    private Integer targetId;

    /** 课件/课程 名称 */
    private String targetName;

    /** 创建时间 */
    private Date createTime;

    /** 考完此试卷需要的用时 单位:秒 */
    private Integer useTime;

    /** 及格分数 */
    private Integer passScore;

    /** 是否开启  0：否 1：是  缺省：0 */
    private Integer isValid;

    /** 试卷下的题目 */
    private List<Examination> examinationList;

    /** 考题数量 */
    private Integer examinationNum;

    /** 课程名称 */
    private String curriculumName;

    /** 获取 试卷ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 试卷ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 试卷名称 */
    public String getPaperTitle() {
        return this.paperTitle;
    }

    /** 设置 试卷名称 */
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 试卷下的题目 */
    public List<Examination> getExaminationList() {
        return this.examinationList;
    }

    /** 设置 试卷下的题目 */
    public void setExaminationList(List<Examination> examinationList) {
        this.examinationList = examinationList;
    }

    /** 获取 绑定类型：0：练习题  1：模拟题 2：考试题 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 绑定类型：0：练习题  1：模拟题 2：考试题 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 试卷绑定的ID，如果是练习题 则是课件ID 其他则是课程ID */
    public Integer getTargetId() {
        return this.targetId;
    }

    /** 设置 试卷绑定的ID，如果是练习题 则是课件ID 其他则是课程ID */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /** 获取 考完此试卷需要的用时 单位:秒 */
    public Integer getUseTime() {
        return this.useTime;
    }

    /** 设置 考完此试卷需要的用时 单位:秒 */
    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    /** 获取 及格分数 */
    public Integer getPassScore() {
        return this.passScore;
    }

    /** 设置 及格分数 */
    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    /** 获取 课件/课程 名称 */
    public String getTargetName() {
        return this.targetName;
    }

    /** 设置 课件/课程 名称 */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /** 获取 是否开启  0：否 1：是  缺省：0 */
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 是否开启  0：否 1：是  缺省：0 */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /** 获取 考题数量 */
    public Integer getExaminationNum() {
        return this.examinationNum;
    }

    /** 设置 考题数量 */
    public void setExaminationNum(Integer examinationNum) {
        this.examinationNum = examinationNum;
    }

    /** 获取 课程名称 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名称 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }
}
