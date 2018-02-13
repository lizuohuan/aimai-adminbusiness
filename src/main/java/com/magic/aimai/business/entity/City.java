package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 城市 entity
 * Created by Eric Xie on 2017/7/12 0012.
 */
public class City implements Serializable {

    /** ID */
    private Integer id;

    /** 名称 */
    private String name;

    /** 父ID */
    private Integer parentId;

    /** 短名称 */
    private String shortName;

    /** 级别 */
    private Integer levelType;

    /** 城市区号 */
    private String cityCode;

    /** 城市邮编 */
    private String zipCode;

    /** 合并名称 */
    private String mergerName;

    /** 城市拼音 */
    private String pinYin;

    /** 市级ID */
    private Integer townId;

    /** 省级ID */
    private Integer provinceId;


    /** 城市下的城市列表 */
    private List<City> cityList = new ArrayList<City>();


    /** 获取 ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 名称 */
    public String getName() {
        return this.name;
    }

    /** 设置 名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 父ID */
    public Integer getParentId() {
        return this.parentId;
    }

    /** 设置 父ID */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /** 获取 短名称 */
    public String getShortName() {
        return this.shortName;
    }

    /** 设置 短名称 */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /** 获取 级别 */
    public Integer getLevelType() {
        return this.levelType;
    }

    /** 设置 级别 */
    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    /** 获取 城市区号 */
    public String getCityCode() {
        return this.cityCode;
    }

    /** 设置 城市区号 */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /** 获取 城市邮编 */
    public String getZipCode() {
        return this.zipCode;
    }

    /** 设置 城市邮编 */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /** 获取 合并名称 */
    public String getMergerName() {
        return this.mergerName;
    }

    /** 设置 合并名称 */
    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    /** 获取 城市拼音 */
    public String getPinYin() {
        return this.pinYin;
    }

    /** 设置 城市拼音 */
    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    /** 获取 市级ID */
    public Integer getTownId() {
        return this.townId;
    }

    /** 设置 市级ID */
    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    /** 获取 省级ID */
    public Integer getProvinceId() {
        return this.provinceId;
    }

    /** 设置 省级ID */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /** 获取 城市下的城市列表 */
    public List<City> getCityList() {
        return this.cityList;
    }

    /** 设置 城市下的城市列表 */
    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
