package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Trade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 行业分类 Mapper
 * Created by Eric Xie on 2017/7/13 0013.
 */
public interface ITradeMapper {




    List<Trade> queryTrade(@Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    /**
     * 查询 行业分类下的 课程列表
     * 仅限登录后的用户查询
     * @param provinceId 省级ID
     * @param townId 市级Id
     * @param countyId 区县级ID
     * @param tradeId 行业分类ID
     *                当用户是游离状态 没有行业分类时，则 查询所有的分类
     * @param limit 课程分页
     * @param limitSize 课程分页
     * @return
     */
    List<Trade> queryTradeIncludeCurriculum(@Param("provinceId") Integer provinceId,
                                            @Param("townId") Integer townId,@Param("countyId") Integer countyId,
                                            @Param("tradeId") Integer tradeId,
                                            @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    /**
     * 后台页面 分页获取行业分类
     * @param map map
     * @return
     */
    List<Trade> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 分页获取行业分类 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);


    /**
     * 添加行业分类
     * @param trade
     */
    void save(Trade trade);

    /**
     * 更新行业分类
     * @param trade
     */
    void update(Trade trade);


    Trade queryTradeById(@Param("tradeId") Integer tradeId);
}
