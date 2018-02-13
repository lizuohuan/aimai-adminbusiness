package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * web端 政府首页统计
 * Created by Eric Xie on 2017/8/3 0003.
 */
public class WebStatistics implements Serializable {


    /** 展示名称 */
    private String showName;

    /** 企业数量 */
    private int companyNum;

    /** 安全人数 */
    private int safeNum;

    /** 全员培训认证通过人数 */
    private int allSafeNum;

    /** 三项培训认证通过人数 */
    private int threeSafeNum;

    /** 所在地 */
    private String cityName;

    /** 行业 */
    private String tradeName;

    /** 参培人数 */
    private int joinNum;

    private Date year;

    /** 获取 企业数量 */
    public int getCompanyNum() {
        return this.companyNum;
    }

    /** 设置 企业数量 */
    public void setCompanyNum(int companyNum) {
        this.companyNum = companyNum;
    }

    /** 获取 安全人数 */
    public int getSafeNum() {
        return this.safeNum;
    }

    /** 设置 安全人数 */
    public void setSafeNum(int safeNum) {
        this.safeNum = safeNum;
    }

    /** 获取 全员培训认证通过人数 */
    public int getAllSafeNum() {
        return this.allSafeNum;
    }

    /** 设置 全员培训认证通过人数 */
    public void setAllSafeNum(int allSafeNum) {
        this.allSafeNum = allSafeNum;
    }

    /** 获取 三项培训认证通过人数 */
    public int getThreeSafeNum() {
        return this.threeSafeNum;
    }

    /** 设置 三项培训认证通过人数 */
    public void setThreeSafeNum(int threeSafeNum) {
        this.threeSafeNum = threeSafeNum;
    }

    /** 获取 展示名称 */
    public String getShowName() {
        return this.showName;
    }

    /** 设置 展示名称 */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTradeName() {
        return this.tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public Date getYear() {
        return this.year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    /** 获取 参培人数 */
    public int getJoinNum() {
        return this.joinNum;
    }

    /** 设置 参培人数 */
    public void setJoinNum(int joinNum) {
        this.joinNum = joinNum;
    }
}
