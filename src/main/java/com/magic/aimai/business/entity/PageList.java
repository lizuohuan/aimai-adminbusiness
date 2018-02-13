package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回数据
 * @author lzh
 * @create 2017/4/20 11:38
 */
public class PageList<T> implements Serializable {

    private List<T> list = new ArrayList<T>();


    /** 总条数 */
    private Integer totalSize;

    public PageList(List<T> list, Integer totalSize) {
        this.list = list;
        this.totalSize = totalSize;
    }

    public PageList() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
