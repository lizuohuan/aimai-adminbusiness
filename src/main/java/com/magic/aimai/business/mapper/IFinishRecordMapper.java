package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.FinishRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Eric Xie on 2017/7/26 0026.
 */
public interface IFinishRecordMapper {


    Integer addFinishRecord(FinishRecord finishRecord);


    Integer batchAddFinishRecord(@Param("finishRecordList") List<FinishRecord> finishRecordList);

    Integer updateFinishRecord(@Param("finishRecord") FinishRecord finishRecord);


    FinishRecord queryFinishRecord(@Param("type") Integer type,@Param("userId") Integer userId,
                                   @Param("targetId") Integer targetId,@Param("orderId") Integer orderId);


    /**
     * 通过条件查询 该视频是否有记录
     * @param type
     * @param userId
     * @param videoId
     * @param orderId
     * @return 如果返回 0，则不存在记录 否则已经存在
     */
    Integer queryTargetFinishRecord(@Param("type") Integer type,@Param("userId") Integer userId,
                                    @Param("videoId") Integer videoId,@Param("orderId") Integer orderId);


}
