package com.magic.aimai.business.entity;

import java.io.Serializable;

/**
 * 统计考题 entity
 * Created by Eric Xie on 2017/8/2 0002.
 */
public class StatisticsExamination implements Serializable {

    private static final long serialVersionUID = -3486920032126434638L;

    /** 已练习 */
    private int countNum;

    /** 练习题正确率 */
    private double correctPercent;

    /** 已模拟 */
    private int simulationCountNum;

    /** 模拟题正确率 */
    private double simulationCorrectPercent;

    /** 通过数量 */
    private int passNum;

    /** 未通过 */
    private int nonPassNum;

    /** 错题库数量 */
    private int errorNum;


    /** 获取 已练习 */
    public int getCountNum() {
        return this.countNum;
    }

    /** 设置 已练习 */
    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    /** 获取 练习题正确率 */
    public double getCorrectPercent() {
        return this.correctPercent;
    }

    /** 设置 练习题正确率 */
    public void setCorrectPercent(double correctPercent) {
        this.correctPercent = correctPercent;
    }

    /** 获取 已模拟 */
    public int getSimulationCountNum() {
        return this.simulationCountNum;
    }

    /** 设置 已模拟 */
    public void setSimulationCountNum(int simulationCountNum) {
        this.simulationCountNum = simulationCountNum;
    }

    /** 获取 模拟题正确率 */
    public double getSimulationCorrectPercent() {
        return this.simulationCorrectPercent;
    }

    /** 设置 模拟题正确率 */
    public void setSimulationCorrectPercent(double simulationCorrectPercent) {
        this.simulationCorrectPercent = simulationCorrectPercent;
    }

    /** 获取 通过数量 */
    public int getPassNum() {
        return this.passNum;
    }

    /** 设置 通过数量 */
    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    /** 获取 未通过 */
    public int getNonPassNum() {
        return this.nonPassNum;
    }

    /** 设置 未通过 */
    public void setNonPassNum(int nonPassNum) {
        this.nonPassNum = nonPassNum;
    }

    /** 获取 错题库数量 */
    public int getErrorNum() {
        return this.errorNum;
    }

    /** 设置 错题库数量 */
    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }
}
