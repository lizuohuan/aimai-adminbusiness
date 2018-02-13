package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * Created by Eric Xie on 2017/7/31 0031.
 */
public class ExaminationPaper implements Serializable {

    private static final long serialVersionUID = -5533615630776357409L;

    private Integer id;

    /** 试题ID */
    private Integer examinationId;

    /** 试卷ID */
    private Integer paperId;

    /** 分数 */
    private Integer score;

    /** 试题 */
    private Examination examination;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 试题ID */
    public Integer getExaminationId() {
        return this.examinationId;
    }

    /** 设置 试题ID */
    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    /** 获取 试卷ID */
    public Integer getPaperId() {
        return this.paperId;
    }

    /** 设置 试卷ID */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /** 获取 分数 */
    public Integer getScore() {
        return this.score;
    }

    /** 设置 分数 */
    public void setScore(Integer score) {
        this.score = score;
    }

    /** 获取 试题 */
    public Examination getExamination() {
        return this.examination;
    }

    /** 设置 试题 */
    public void setExamination(Examination examination) {
        this.examination = examination;
    }
}
