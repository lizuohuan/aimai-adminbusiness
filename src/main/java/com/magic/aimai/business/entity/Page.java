package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 分页实体
 * Created by Eric Xie on 2017/2/15 0015.
 */
public class Page<T> implements Serializable {

    /**分页数据*/
    private List<T> dataList = new ArrayList<T>();

    /**总数量*/
    private Integer count;

    /**总页数*/
    private Integer totalPage;

    public Page(List<T> dataList, Integer count, Integer totalPage) {
        this.dataList = dataList;
        this.count = count;
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
