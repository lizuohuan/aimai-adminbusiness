package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.CurriculumNewRecord;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Eric Xie on 2017/8/1 0001.
 */
public interface ICurriculumNewRecordMapper {


    /**
     * 新建记录
     * @param record
     * @return
     */
    Integer addCurriculumNewRecord(CurriculumNewRecord record);

    /**
     *  通过用户ID 查询记录
     * @param userId
     * @return
     */
    CurriculumNewRecord queryCurriculumNewRecord(@Param("userId") Integer userId);

    /**
     * 通过ID 更新 OrderId
     * @param id
     * @param orderId
     * @return
     */
    Integer updateCurriculumNewRecord(@Param("id") Integer id,@Param("orderId") Integer orderId);


}
