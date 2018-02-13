package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 试卷绑定 entity
 * Created by Eric Xie on 2017/7/21 0021.
 */
public class BindPager implements Serializable {

    private static final long serialVersionUID = 5474588289079554171L;

    /** ID */
    private Integer id;

    /** 被绑定的ID */
    private Integer bindId;

    /** 绑定的类型  0:课时 1:课程 说明 以上字段的ID取值 */
    private Integer bindType;

    /** 试卷ID */
    private Integer paperId;


    /** 获取 ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 被绑定的ID */
    public Integer getBindId() {
        return this.bindId;
    }

    /** 设置 被绑定的ID */
    public void setBindId(Integer bindId) {
        this.bindId = bindId;
    }

    /** 获取 绑定的类型  0:课时 1:课程 说明 以上字段的ID取值 */
    public Integer getBindType() {
        return this.bindType;
    }

    /** 设置 绑定的类型  0:课时 1:课程 说明 以上字段的ID取值 */
    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    /** 获取 试卷ID */
    public Integer getPaperId() {
        return this.paperId;
    }

    /** 设置 试卷ID */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
