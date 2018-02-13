package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Curriculum;
import com.magic.aimai.business.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单 Mapper
 * Created by Eric Xie on 2017/7/14 0014.
 */
public interface IOrderMapper {

    /**
     * 获取公司用户 有效的订单集合
     * 1、已经支付了的订单
     * 2、订单的数量大于0
     * @param userId
     * @param curriculumId
     * @return
     */
    List<Order> queryOrderByCompany(@Param("userId") Integer userId,@Param("curriculumId") Integer curriculumId);


    //*********************************
    //**********以上V2.0 JPA************//
    //*********************************//

    /**
     * 查询用户购买该课程的记录，仅限用户购买
     * @param userId
     * @param curriculumId
     * @return
     */
    List<Order> queryOrderByUser(@Param("userId") Integer userId,@Param("curriculumId") Integer curriculumId);

    /**
     * 新增订单
     * @param order
     * @return
     */
    Integer addOrder( Order order);


    /**
     * 更新部分字段 通过ID
     * @param order
     * @return
     */
    Integer updateOrder(@Param("order") Order order);


    /**
     * 通过ID 查询基础字段
     * @param id
     * @return
     */
    Order queryBaseOrder(@Param("id") Integer id);

    /**
     * 查询列表 部分字段
     * @param userId
     * @param payStatus
     * @param limit
     * @param limitSize
     * @return
     */
    List<Order> queryOrder(@Param("userId") Integer userId,@Param("payStatus") Integer payStatus,
                           @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    /**
     * 后台页面 分页获取订单列表
     * @param map map
     * @return
     */
    List<Order> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取订单 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);


    /**
     * 订单详情
     * @param id
     * @return
     */
    Order info(@Param("id") Integer id);

    /**
     * 更新购买的课程 用户是否可以学习
     * @param studyStatus 学习状态  1：可以学习   0：停止  1：暂停
     * @param id
     */
    void updateIsCanStudy(@Param("studyStatus") Integer studyStatus ,
                          @Param("id") Integer id);

}
