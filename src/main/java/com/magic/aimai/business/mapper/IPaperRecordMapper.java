package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.PaperRecord;
import com.magic.aimai.business.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 试卷记录 Mapper
 * Created by Eric Xie on 2017/8/1 0001.
 */
public interface IPaperRecordMapper {


    Integer addPaperRecord(PaperRecord record);

    PaperRecord queryPaperRecord(@Param("userId") Integer userId,@Param("orderId") Integer orderId,@Param("paperId") Integer paperId);

    /**
     * 统计用户考试 已经考了多少次
     * @param userId
     * @param orderId
     * @param paperId
     * @return
     */
    Integer countPager(@Param("userId") Integer userId,@Param("orderId") Integer orderId,@Param("paperId") Integer paperId);


    /**
     * 后台页面 获取用户考试记录
     * @param map map
     * @return
     */
    List<PaperRecord> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 获取用户考试记录
     * @param map map
     * @return
     */
    List<PaperRecord> listForWeb(Map<String , Object> map);

    /**
     * 后台页面 统计 获取用户考试记录数量
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 修改考试状态
     * @param record
     */
    void updateIsPass(PaperRecord record);


    /**
     * 获取 没有考试试卷的情况下 直接记录通过或者不通过的记录
     * @param type
     * @param curriculumId
     * @param userId
     * @param orderId
     * @return
     */
    PaperRecord getInfoByCurriculumId(@Param("type") Integer type,
                                      @Param("curriculumId") Integer curriculumId ,
                                      @Param("userId") Integer userId ,
                                      @Param("orderId") Integer orderId );
}
