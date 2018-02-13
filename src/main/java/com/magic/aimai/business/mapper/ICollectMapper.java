package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Collect;
import org.apache.ibatis.annotations.Param;

/**
 * 收藏 Mapper
 * Created by Eric Xie on 2017/7/14 0014.
 */
public interface ICollectMapper {


    Integer addCollect(Collect collect);

    /**
     * 查询 用户 是否收藏过 该类型 该数据
     * @param userId
     * @param type
     * @param targetId
     * @return
     */
    Collect checkCollect(@Param("userId") Integer userId,@Param("type") Integer type,
                         @Param("targetId") Integer targetId);


    Integer del(@Param("id") Integer id);

    /**
     * 取消收藏
     */
    void delete(@Param("userId") Integer userId,@Param("type") Integer type,
                @Param("targetId") Integer targetId);
}
