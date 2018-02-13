package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 公司内容管理
 * @author lzh
 * @create 2017/8/3 21:16
 */
public class Content implements Serializable {


    /***/
    private Integer id;

    /** 名 */
    private String name;

    /** 内容 */
    private String content;

    /***/
    public Integer getId() {
        return this.id;
    }

    /***/
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 名 */
    public String getName() {
        return this.name;
    }

    /** 设置 名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 内容 */
    public String getContent() {
        return this.content;
    }

    /** 设置 内容 */
    public void setContent(String content) {
        this.content = content;
    }
}
