package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.SystemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Eric Xie on 2017/7/13 0013.
 */
public interface ISystemInfoMapper {


    /**
     *
     * @param userId
     * @return
     */
    List<SystemInfo> countCheckInfo(@Param("userId") Integer userId);


    Integer addSystemInfo(SystemInfo info);



    List<SystemInfo> querySystemInfo(@Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                     @Param("userId") Integer userId);

    Integer countSystemInfo(@Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                            @Param("userId") Integer userId);


    /**
     * 查看某消息
     * @param userId
     * @param systemInfoId
     * @return
     */
    Integer addSystemInfoUser(@Param("userId") Integer userId,@Param("systemInfoId") Integer systemInfoId);


    /**
     * 统计查询 某资讯是否查看过
     * @param userId 用户ID
     * @param systemInfoId 资讯ID
     * @return
     */
    Integer querySystemInfoIsCheck(@Param("userId") Integer userId,@Param("systemInfoId") Integer systemInfoId);

    /**
     * 删除新闻资讯 并删除用户查看记录 1
     * @param newsId
     */
    void deleteForUser(@Param("newsId") Integer newsId ,@Param("type") Integer type);

    /**
     * 删除新闻资讯 并删除消息 2
     * @param newsId
     */
    void delete(@Param("newsId") Integer newsId ,@Param("type") Integer type);


}
