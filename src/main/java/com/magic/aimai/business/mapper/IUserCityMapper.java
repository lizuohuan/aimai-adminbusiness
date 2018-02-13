package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.City;
import com.magic.aimai.business.entity.UserCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Eric Xie on 2018/1/18 0018.
 */
public interface IUserCityMapper {

    int batchAdd(List<UserCity> userCities);


    int del(@Param("userId") Integer userId);

    /**
     * 获取经销商下的城市
     * @param userId
     * @return
     */
    List<City> queryCityByUser(@Param("userId") Integer userId);

}
