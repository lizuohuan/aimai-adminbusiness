package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Xie on 2017/8/3 0003.
 */
public class PaperDetailResult implements Serializable {


    private Integer resultScore;

    private Integer correctNum;

    private Integer passScore;

    private Integer count;

    private List<Answers> jsonArray = new ArrayList<Answers>();

    public Integer getResultScore() {
        return this.resultScore;
    }

    public void setResultScore(Integer resultScore) {
        this.resultScore = resultScore;
    }

    public Integer getCorrectNum() {
        return this.correctNum;
    }

    public void setCorrectNum(Integer correctNum) {
        this.correctNum = correctNum;
    }

    public Integer getPassScore() {
        return this.passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    public List<Answers> getJsonArray() {
        return this.jsonArray;
    }

    public void setJsonArray(List<Answers> jsonArray) {
        this.jsonArray = jsonArray;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
