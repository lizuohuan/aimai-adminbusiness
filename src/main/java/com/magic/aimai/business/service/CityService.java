package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.City;
import com.magic.aimai.business.mapper.ICityMapper;
import com.magic.aimai.business.mapper.IUserCityMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/12 0012.
 */
@Service
public class CityService {

    @Resource
    private ICityMapper cityMapper;
    @Resource
    private IUserCityMapper userCityMapper;


    public List<City> queryCityByUser(Integer userId){
        return userCityMapper.queryCityByUser(userId);
    }


    public List<City> queryCitys(List<Integer> cIds){
        return cityMapper.queryCitys(cIds);
    }

    /**
     * 通过城市ID、城市级别  查询该城市Id下 所有级别的城市
     * @param cityId
     * @param levelType
     * @return
     */
    public List<City> queryCityByParentId(Integer cityId,Integer levelType){
        return cityMapper.queryCityByParentId(cityId,levelType);
    }

    /**
     * 通过城市ID、城市级别  查询该城市Id下 所有级别的城市 2
     * @param cityIds
     * @param levelType
     * @return
     */
    public List<City> queryCityByParentIds(List<Integer> cityIds,Integer levelType){
        return cityMapper.queryCityByParentIds(cityIds,levelType);
    }

    public List<City> queryAllCity() {
        List<City> cityList = cityMapper.queryAll();
        // 装配城市
        return setCity(cityList);
    }

    public City queryCity(Integer city){
        return cityMapper.queryCity(city);
    }


    private List<City> setCity(List<City> cityList) {
        // 省
        List<City> provinceList = new ArrayList<City>();
        // 市
        List<City> townList = new ArrayList<City>();
        // 区县
        List<City> countyList = new ArrayList<City>();
        if (cityList.size() > 0) {
            for (City City : cityList) {
                if (City.getLevelType() == 3) {
                    countyList.add(City);
                }
                if (City.getLevelType() == 2) {
                    townList.add(City);
                }
                if (City.getLevelType() == 1) {
                    provinceList.add(City);
                }
            }
            // 设置 区县级列表到市级集合
            for (City town : townList) {
                for (City county : countyList) {
                    if(county.getParentId().equals(town.getId())){
                        town.getCityList().add(county);
                    }
                }
            }
            for (City province : provinceList) {
                for (City town : townList) {
                    if(town.getParentId().equals(province.getId())){
                        province.getCityList().add(town);
                    }
                }
            }
        }
        return provinceList;
    }
    /**
     * 查询城市的  市级ID  和 省级Id
     * @param id
     * @return
     */
    public City getThreeId(Integer id) {
        return cityMapper.getThreeId(id);
    }

}
