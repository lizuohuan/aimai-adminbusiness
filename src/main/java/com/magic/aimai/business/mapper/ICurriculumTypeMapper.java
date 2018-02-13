package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.CurriculumType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/7/14 0014.
 */
public interface ICurriculumTypeMapper {


    List<CurriculumType> queryCurriculumTypeIncludeCurriculum(@Param("provinceId") Integer provinceId,
                                                              @Param("townId") Integer townId,@Param("countyId") Integer countyId,
                                                              @Param("tradeId") Integer tradeId,@Param("userId") Integer userId,
                                                              @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 后台页面 分页获取课程分类
     * @param map map
     * @return
     */
    List<CurriculumType> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取课程分类 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

}
