package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Xie on 2017/8/3 0003.
 */
public class Answers implements Serializable {

    private List<Integer> answers = new ArrayList<Integer>();

    private Integer examinationId;

    private Integer isCorrect;


    public List<Integer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public Integer getExaminationId() {
        return this.examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }
}
