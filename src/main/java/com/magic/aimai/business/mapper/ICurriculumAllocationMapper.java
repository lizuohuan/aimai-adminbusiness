package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.CurriculumAllocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 分配课程 Mapper
 * Created by Eric Xie on 2017/7/14 0014.
 */
public interface ICurriculumAllocationMapper {


    Integer addCurriculumAllocation(@Param("curriculumAllocation") CurriculumAllocation curriculumAllocation);


    /**
     * 批量新增分配
     * @param allocations
     * @return
     */
    Integer batchAddAllocation(@Param("allocations") List<CurriculumAllocation> allocations);


    /**
     * 查询订单 剩余可分配的数量
     * @param orderId 订单ID
     * @return
     */
    Integer querySurplusAllocation(@Param("orderId") Integer orderId);


    /**
     * 后台页面 分页 通过各种条件 查询用户集合
     * @param map map
     * @return
     */
    List<CurriculumAllocation> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 统计 用户数量
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 更新分配的课程 用户是否可以学习
     * @param studyStatus 学习状态  1：可以学习   0：停止  1：暂停
     * @param userId
     * @param orderId
     */
    void updateIsCanStudy(@Param("studyStatus") Integer studyStatus ,
                          @Param("userId") Integer userId,
                          @Param("orderId") Integer orderId);

    /**
     * 更新分配的课程 用户是否可以学习 2
     * @param studyStatus 学习状态  1：可以学习   0：停止  1：暂停
     * @param id
     */
    void updateIsCanStudy2(@Param("studyStatus") Integer studyStatus ,
                          @Param("id") Integer id);
}
