package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * Created by Eric Xie on 2017/8/2 0002.
 */
public class ExaminationList implements Serializable {

    private static final long serialVersionUID = 2663538314805006618L;

    /** 课件ID */
    private Integer courseWareId;

    /** 课件名称 */
    private String courseWareName;

    /** 练习题数量 */
    private int examinationNum;

    /** 是否练习 0:否  1:是 */
    private int isExamination;

    /** 所属订单ID */
    private Integer orderId;

    /** 是否学习完 0:否  1:是 */
    private int isStudy;

    /** 错题数量 */
    private int errorNum;

    /** 试卷ID */
    private Integer paperId;

    /** 用时 */
    private Integer useTime;

    /** 获取 课件ID */
    public Integer getCourseWareId() {
        return this.courseWareId;
    }

    /** 设置 课件ID */
    public void setCourseWareId(Integer courseWareId) {
        this.courseWareId = courseWareId;
    }

    /** 获取 课件名称 */
    public String getCourseWareName() {
        return this.courseWareName;
    }

    /** 设置 课件名称 */
    public void setCourseWareName(String courseWareName) {
        this.courseWareName = courseWareName;
    }

    /** 获取 练习题数量 */
    public int getExaminationNum() {
        return this.examinationNum;
    }

    /** 设置 练习题数量 */
    public void setExaminationNum(int examinationNum) {
        this.examinationNum = examinationNum;
    }

    /** 获取 是否练习 0:否  1:是 */
    public int getIsExamination() {
        return this.isExamination;
    }

    /** 设置 是否练习 0:否  1:是 */
    public void setIsExamination(int isExamination) {
        this.isExamination = isExamination;
    }

    /** 获取 是否学习完 0:否  1:是 */
    public int getIsStudy() {
        return this.isStudy;
    }

    /** 设置 是否学习完 0:否  1:是 */
    public void setIsStudy(int isStudy) {
        this.isStudy = isStudy;
    }

    /** 获取 所属订单ID */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 所属订单ID */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 错题数量 */
    public int getErrorNum() {
        return this.errorNum;
    }

    /** 设置 错题数量 */
    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    /** 获取 试卷ID */
    public Integer getPaperId() {
        return this.paperId;
    }

    /** 设置 试卷ID */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /** 获取 用时 */
    public Integer getUseTime() {
        return this.useTime;
    }

    /** 设置 用时 */
    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }
}
