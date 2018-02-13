package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试结果 记录 entity
 * Created by Eric Xie on 2017/8/1 0001.
 */
public class PaperRecord implements Serializable {

    private static final long serialVersionUID = -5028857448779460751L;

    /** 记录ID */
    private Integer id;

    /** 订单ID */
    private Integer orderId;

    /** 考试者用户ID */
    private Integer userId;

    /** 试卷ID */
    private Integer paperId;

    /** 练习的数量 做了多少题 */
    private Integer exerciseNum;

    /** 总题数 */
    private Integer countNum;

    /** 正确数量 */
    private Integer correctNum;

    /** 及格分数 */
    private Integer passScore;

    /** 试卷总分数 */
    private Integer paperScore;

    /** 此次考试得分 */
    private Integer resultScore;

    /** 用时  秒 */
    private Integer seconds;

    /** 提交后的结果 */
    private String resultDetail;

    /** 创建时间 */
    private Date  createTime;

    /** 正式考试 还剩下多少次 */
    private Integer count;

    /** 试卷的名字 */
    private String paperTitle;

    /** 课程名 */
    private String curriculumName;

    /** 0:试卷  1:课程 */
    private Integer type;

    /** 获取 记录ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 记录ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 订单ID */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单ID */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 考试者用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 考试者用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 试卷ID */
    public Integer getPaperId() {
        return this.paperId;
    }

    /** 设置 试卷ID */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /** 获取 练习的数量 做了多少题 */
    public Integer getExerciseNum() {
        return this.exerciseNum;
    }

    /** 设置 练习的数量 做了多少题 */
    public void setExerciseNum(Integer exerciseNum) {
        this.exerciseNum = exerciseNum;
    }

    /** 获取 总题数 */
    public Integer getCountNum() {
        return this.countNum;
    }

    /** 设置 总题数 */
    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    /** 获取 正确数量 */
    public Integer getCorrectNum() {
        return this.correctNum;
    }

    /** 设置 正确数量 */
    public void setCorrectNum(Integer correctNum) {
        this.correctNum = correctNum;
    }

    /** 获取 及格分数 */
    public Integer getPassScore() {
        return this.passScore;
    }

    /** 设置 及格分数 */
    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    /** 获取 试卷总分数 */
    public Integer getPaperScore() {
        return this.paperScore;
    }

    /** 设置 试卷总分数 */
    public void setPaperScore(Integer paperScore) {
        this.paperScore = paperScore;
    }

    /** 获取 此次考试得分 */
    public Integer getResultScore() {
        return this.resultScore;
    }

    /** 设置 此次考试得分 */
    public void setResultScore(Integer resultScore) {
        this.resultScore = resultScore;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 用时  秒 */
    public Integer getSeconds() {
        return this.seconds;
    }

    /** 设置 用时  秒 */
    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    /** 获取 提交后的结果 */
    public String getResultDetail() {
        return this.resultDetail;
    }

    /** 设置 提交后的结果 */
    public void setResultDetail(String resultDetail) {
        this.resultDetail = resultDetail;
    }

    /** 获取 正式考试 还剩下多少次 */
    public Integer getCount() {
        return this.count;
    }

    /** 设置 正式考试 还剩下多少次 */
    public void setCount(Integer count) {
        this.count = count;
    }

    /** 获取 试卷的名字 */
    public String getPaperTitle() {
        return this.paperTitle;
    }

    /** 设置 试卷的名字 */
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    /** 获取 课程名 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 0:试卷  1:课程 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 0:试卷  1:课程 */
    public void setType(Integer type) {
        this.type = type;
    }
}
