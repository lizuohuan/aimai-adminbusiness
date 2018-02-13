package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.CourseWare;
import com.magic.aimai.business.entity.ExaminationList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课件 课时 Mapper
 * Created by Eric Xie on 2017/7/14 0014.
 */
public interface ICourseWareMapper {


    /**
     * 获取错题库 课时列表
     * @param orderId
     * @param userId
     * @param limit
     * @param limitSize
     * @return
     */
    List<ExaminationList>  queryCourseWareError(@Param("orderId") Integer orderId,@Param("userId") Integer userId,
                                                @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 学习模块 练习题列表
     * @param orderId 订单ID
     * @param userId  用户ID
     * @return
     */
    List<ExaminationList> statisticsCourseWare(@Param("orderId") Integer orderId,@Param("userId") Integer userId,
                                               @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);
    /**
     * 学习模块 练习题列表
     * @param orderId 订单ID
     * @param userId  用户ID
     * @return
     */
    int countStatisticsCourseWare(@Param("orderId") Integer orderId,@Param("userId") Integer userId);

    /**
     * 通过课程ID 查询 课件列表
     * @param curriculumId
     * @return
     */
    List<CourseWare> queryCourseWareByCurriculumId(@Param("curriculumId") Integer curriculumId,@Param("orderId") Integer orderId,
                                                   @Param("userId") Integer userId);

    /**
     * 通过课程ID 查询 课件列表
     * @param curriculumId
     * @return
     */
    List<CourseWare> querySimpleCourseWareByCurriculumId(@Param("curriculumId") Integer curriculumId);

    /**
     * 查询课件 基础字段
     * @return
     */
    List<CourseWare> queryBaseCourseWare();

    /**
     * 查询统计 该用户是否看完 该 课件下的视频
     * @param userId
     * @param orderId
     * @param courseWareId
     * @return 1 已经看完， 0 没有看完
     */
    Integer countCourseWareFinish(@Param("userId") Integer userId,@Param("orderId") Integer orderId,
                                  @Param("courseWareId") Integer courseWareId);

    /**
     * 通过视频ID 查询 课件对象
     * @param videoId
     * @return
     */
    CourseWare queryCourseWareByVideo(@Param("videoId") Integer videoId);


    Integer addCourseWare(CourseWare courseWare);


    /**
     * 通过课程 查询课件
     * @return
     */
    List<CourseWare> queryCourseWareByCurriculum(Map<String,Object> map);



    /**
     * 后台页面 分页获取课件
     * @param map map
     * @return
     */
    List<CourseWare> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取课件 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 更新课时
     * @param courseWare
     */
    void update(CourseWare courseWare);

    /**
     * 更新课时 包括为空的字段
     * @param courseWare
     */
    void updateAll(CourseWare courseWare);

    /**
     * 获取课时详情
     * @param id
     * @return
     */
    CourseWare info(@Param("id") Integer id);



    /**
     * 后台页面 分页获取用户课件（档案管理）
     * @param map map
     * @return
     */
    List<CourseWare> listForAdminUser(Map<String , Object> map);



    /**
     * 后台页面 分页获取用户课件（档案管理）
     * @param map map
     * @return
     */
    List<CourseWare> listForWebUser(Map<String , Object> map);


    /**
     * 后台页面 分页获取用户课件 条数（档案管理）
     * @param map map
     * @return
     */
    int listForAdminUserCount(Map<String , Object> map);


    /***
     * 删除课时
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 删除 课程下全部课时
     * @param curriculumId
     */
    void deleteByCurriculumId(@Param("id") Integer curriculumId);
}
