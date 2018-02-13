package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 培训阶段 方便扩展
 * 暂时不提供其他新增修改接口
 * Created by Eric Xie on 2017/7/14 0014.
 */
public class CurriculumType implements Serializable {


    /** 类型ID */
    private Integer id;

    /** 类型名称 */
    private String curriculumTypeName;

    /** 类型下的课程集合 */
    private List<Curriculum> curriculumList;

    /** 获取 类型ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 类型ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 类型名称 */
    public String getCurriculumTypeName() {
        return this.curriculumTypeName;
    }

    /** 设置 类型名称 */
    public void setCurriculumTypeName(String curriculumTypeName) {
        this.curriculumTypeName = curriculumTypeName;
    }

    /** 获取 类型下的课程集合 */
    public List<Curriculum> getCurriculumList() {
        return this.curriculumList;
    }

    /** 设置 类型下的课程集合 */
    public void setCurriculumList(List<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }
}
