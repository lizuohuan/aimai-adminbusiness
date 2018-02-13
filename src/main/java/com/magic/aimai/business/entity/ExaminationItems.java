package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * Created by Eric Xie on 2017/7/21 0021.
 */
public class ExaminationItems implements Serializable {

    private static final long serialVersionUID = -8777231041243424706L;

    /** 选项ID */
    private Integer id;

    /** 选项名称 */
    private String itemTitle;

    /** 该选项是否是正确的 0:不是   1:是 该正确答案 */
    private Integer isCorrect;

    /** 试题ID */
    private Integer examinationId;

    /** 试题名称 */
    private String examinationName;

    /** 排序序号 升序 */
    private Integer  sortNum;


    /** 获取 选项ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 选项ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 选项名称 */
    public String getItemTitle() {
        return this.itemTitle;
    }

    /** 设置 选项名称 */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /** 获取 该选项是否是正确的 0:不是   1:是 该正确答案 */
    public Integer getIsCorrect() {
        return this.isCorrect;
    }

    /** 设置 该选项是否是正确的 0:不是   1:是 该正确答案 */
    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    /** 获取 试卷ID */
    public Integer getExaminationId() {
        return this.examinationId;
    }

    /** 设置 试卷ID */
    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    /** 获取 排序序号 升序 */
    public Integer getSortNum() {
        return this.sortNum;
    }

    /** 设置 排序序号 升序 */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    /** 获取 试题名称 */
    public String getExaminationName() {
        return this.examinationName;
    }

    /** 设置 试题名称 */
    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName;
    }
}
