package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * Created by Eric Xie on 2017/7/26 0026.
 */
public class ForbiddenWords implements Serializable {

    private static final long serialVersionUID = -5806772122064856280L;

    private Integer id;

    /** 禁词 */
    private String word;

    /** 描述 */
    private String describe;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 禁词 */
    public String getWord() {
        return this.word;
    }

    /** 设置 禁词 */
    public void setWord(String word) {
        this.word = word;
    }

    /** 获取 描述 */
    public String getDescribe() {
        return this.describe;
    }

    /** 设置 描述 */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
