package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 行业分类 entity
 * Created by Eric Xie on 2017/7/12 0012.
 */
public class Trade implements Serializable {

    /** 分类ID */
    private Integer id;

    /** 分类名称 */
    private String tradeName;

    /** 行业下的课程 */
    private List<Curriculum> curriculumList;

    /** 获取 分类ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 分类ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 分类名称 */
    public String getTradeName() {
        return this.tradeName;
    }

    /** 设置 分类名称 */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /** 获取 行业下的课程 */
    public List<Curriculum> getCurriculumList() {
        return this.curriculumList;
    }

    /** 设置 行业下的课程 */
    public void setCurriculumList(List<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }
}
