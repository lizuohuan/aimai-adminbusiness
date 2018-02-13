package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Banner;
import com.magic.aimai.business.entity.Curriculum;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Banner Mapper
 * Created by Eric Xie on 2017/7/24 0024.
 */
public interface IBannerMapper {


    Integer addBanner(Banner banner);


    Integer updateBanner(@Param("banner") Banner banner);


    Integer delBanner(@Param("bannerId") Integer bannerId);


    Banner queryBannerById(@Param("bannerId") Integer bannerId);


    /**
     * 获取有效期内的所有banner
     * @param time
     * @return
     */
    List<Banner> queryBannerList(@Param("time") Date time,@Param("isBanner") Integer isBanner);

    /**
     * 后台页面 分页获取Banner
     * @param map map
     * @return
     */
    List<Banner> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取Banner条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);


    /**
     * 查询此广告位是否存在广告
     * @param location
     * @return
     */
    Banner isHaveAd(@Param("location") Integer location);
}
