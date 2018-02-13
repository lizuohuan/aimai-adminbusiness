package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Curriculum;
import com.magic.aimai.business.entity.CurriculumStudy;
import com.magic.aimai.business.entity.ExaminationList;
import com.magic.aimai.business.entity.StatisticsExamination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/7/13 0013.
 */
public interface ICurriculumMapper {

    /**
     * 获取企业下 未购买的课程列表
     * @param map
     * @return
     */
    List<Curriculum> queryCurriculumNotBuy(Map<String,Object> map);
    /**
     * 统计企业下 未购买的课程列表
     * @param map
     * @return
     */
    int countCurriculumNotBuy(Map<String,Object> map);


    //************************************************************************************//
    //*******************************以上为 V2 JPA****************************************//
    //************************************************************************************//

    /**
     * 通过公司ID 获取 公司下的课程列表
     * @param companyId
     * @return
     */
    List<Curriculum> queryCurriculumByCompany(@Param("companyId") Integer companyId,@Param("limit") Integer limit,
                                              @Param("limitSize") Integer limitSize);

    /**
     * 通过用户 查询 该用户待分配的课程列表
     * @param userId
     * @param limit
     * @param limitSize
     * @return
     */
    List<Curriculum> queryWaitAllocationCurriculumByUser(@Param("companyId") Integer companyId,@Param("userId") Integer userId,
                                                         @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 通过用户ID 查询用户下所有的 课程情况
     * @param userId
     * @return
     */
    List<Curriculum> queryCurriculumByUser(@Param("userId") Integer userId);

    /**
     * 错题库列表
     * @param userId
     * @param limit
     * @param limitSize
     * @return
     */
    List<Curriculum> queryCurriculumError(@Param("userId") Integer userId,
                                          @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    /**
     * 错题库列表
     * @param userId
     * @return
     */
    int countCurriculumError(@Param("userId") Integer userId);


    /**
     * 获取考试列表
     * @param userId
     * @param limit
     * @param limitSize
     * @return
     */
    List<CurriculumStudy> queryCurriculumPass(@Param("userId") Integer userId,
                                              @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);



    /**
     * 获取考试列表
     * @param userId
     * @return
     */
    int countCurriculumPass(@Param("userId") Integer userId);




    /**
     * 学习模块  获取 模拟题列表接口
     * @param userId
     * @return
     */
    List<CurriculumStudy> queryCurriculumStudyForExamination(@Param("userId") Integer userId,
                                                             @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 学习模块  获取 模拟题列表接口
     * @param userId
     * @return
     */
    Integer countCurriculumStudyForExamination(@Param("userId") Integer userId);

    /**
     * 统计用户 最新一次 考题记录得分情况
     * @param userId
     * @return
     */
    StatisticsExamination statisticsCurriculum(@Param("userId") Integer userId);

    /**
     * 查询 课程列表 仅名称 ID 字段
     * @return
     */
    List<Curriculum> queryCurriculumForSeclect();

    /**
     * 统计 该用户 是否看完该课程下的所有的视频
     * @param userId
     * @param orderId
     * @param curriculumId
     * @return
     */
    Integer countCurriculumFinish(@Param("userId") Integer userId,@Param("orderId") Integer orderId,
                                  @Param("curriculumId") Integer curriculumId);

    /**
     * 个人 学习模块 或者 订单列表 入口 获取 课程详情
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return
     */
    Curriculum queryCurriculumByOrderId(@Param("orderId") Integer orderId,@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    /**
     * 企业用户 查询课程详情
     * @param userId
     * @param curriculumId
     * @return
     */
    Curriculum queryCurriculumByCurriculumId(@Param("userId") Integer userId,@Param("curriculumId") Integer curriculumId);

    /**
     * 公司 学习模块 课程学习 列表
     * @param userId
     * @param limit
     * @param limitSize
     * @return
     */
    List<CurriculumStudy> queryCurriculumStudyByItemsOfCompany(@Param("userId") Integer userId,@Param("limit") Integer limit,
                                                               @Param("limitSize") Integer limitSize);

    /**
     * 个人学习中心 学习模块 课程模块 课程列表
     * @param userId 用户ID
     * @param limit
     * @param limitSize
     * @return
     */
    List<CurriculumStudy> queryCurriculumStudyByItems(@Param("userId") Integer userId,@Param("limit") Integer limit,
                                                      @Param("limitSize") Integer limitSize);


    /**
     * 通过ID 获取 课程 以及其他属性
     * @param id
     * @return
     */
    Curriculum queryCurriculumById(@Param("id") Integer id,@Param("userId") Integer userId);

    /**
     * 查询课程的基础字段
     * @param id
     * @return
     */
    Curriculum queryBaseInfo(@Param("id") Integer id);

    /**
     * 查询用户收藏的课程
     * @param userId
     * @param limit
     * @param limitSize
     * @return
     */
    List<Curriculum> queryCurriculumByCollect(@Param("userId") Integer userId,@Param("limit") Integer limit,
                                              @Param("limitSize") Integer limitSize);

    /**
     * 新增课程
     * @param curriculum
     * @return
     */
    Integer addCurriculum(Curriculum curriculum);



    /**
     * 通过ID 更新不为空的字段
     * @param curriculum
     * @return
     */
    Integer updateCurriculum(@Param("curriculum") Curriculum curriculum);

    /**
     * 修改传回来的所有字段
     * @param curriculum
     */
    void updateForAdmin(Curriculum curriculum);

    /**
     * 动态查询课程
     * @return
     */
    List<Curriculum> queryCurriculumByItems(Map<String,Object> map);


    /**
     * 通过类型  三级三项 动态查询课程
     * @return
     */
    List<Curriculum> queryCurriculumByItemsAndType(Map<String,Object> map);




    /**
     * 动态查询试听课程
     * @param provinceId 省级ID
     * @param townId 市级ID
     * @param countyId 区县级ID
     * @param tradeId 行业ID
     * @param userId 用户ID
     * @return
     */
    List<Curriculum> queryCurriculumByAudition(@Param("provinceId") Integer provinceId,
                                            @Param("townId") Integer townId,@Param("countyId") Integer countyId,
                                            @Param("tradeId") Integer tradeId,@Param("userId") Integer userId);





    /**
     * 动态查询推荐课程
     * @param provinceId 省级ID
     * @param townId 市级ID
     * @param countyId 区县级ID
     * @param tradeId 行业ID
     * @param userId 用户ID
     * @return
     */
    List<Curriculum> queryCurriculumByRecommend(@Param("provinceId") Integer provinceId,
                                            @Param("townId") Integer townId,@Param("countyId") Integer countyId,
                                            @Param("tradeId") Integer tradeId,@Param("userId") Integer userId,
                                                @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    /**
     * API 首页搜索课程列表
     * @param searchParam
     * @param limit
     * @param limitSize
     * @return
     */
    List<Curriculum> queryCurriculumBySearch(@Param("searchParam") String searchParam,@Param("provinceId") Integer provinceId,
                                             @Param("townId") Integer townId,@Param("countyId") Integer countyId,
                                             @Param("tradeId") Integer tradeId,@Param("userId") Integer userId,
                                             @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);




    /**
     * 通过行业分类ID 动态查询
     * @param provinceId 省级ID
     * @param townId 市级ID
     * @param countyId 区县级ID
     * @param tradeId 行业ID
     * @return
     */
    List<Curriculum> queryCurriculumByTradeId(@Param("provinceId") Integer provinceId,
                                                @Param("townId") Integer townId,@Param("countyId") Integer countyId,
                                                @Param("tradeId") Integer tradeId,
                                              @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);



    /**
     * 通过 三级三项等参数 动态查询
     * @param provinceId 省级ID
     * @param townId 市级ID
     * @param countyId 区县级ID
     * @param tradeId 行业ID
     * @return
     */
    List<Curriculum> queryCurriculumByType(@Param("provinceId") Integer provinceId,@Param("townId") Integer townId,
                                           @Param("countyId") Integer countyId,@Param("tradeId") Integer tradeId,
                                           @Param("isRecommend") Integer isRecommend,
                                           @Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                           @Param("curriculumStageId") Integer curriculumStageId,
                                           @Param("curriculumTypeId") Integer curriculumTypeId);

    /**
     * 通过 三级三项等参数 动态查询
     * @param provinceId 省级ID
     * @param townId 市级ID
     * @param countyId 区县级ID
     * @param tradeId 行业ID
     * @param searchParam 课程名
     * @return
     */
    List<Curriculum> queryCurriculumByTypeOfWeb(@Param("provinceId") Integer provinceId,@Param("townId") Integer townId,
                                           @Param("countyId") Integer countyId,@Param("tradeId") Integer tradeId,
                                           @Param("isRecommend") Integer isRecommend,
                                           @Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                           @Param("curriculumStageId") Integer curriculumStageId,
                                           @Param("searchParam") String searchParam,
                                           @Param("curriculumTypeId") Integer curriculumTypeId);


    /**
     * 通过 三级三项等参数 动态查询
     * @param provinceId 省级ID
     * @param townId 市级ID
     * @param countyId 区县级ID
     * @param tradeId 行业ID
     * @return
     */
    int countCurriculumByType(@Param("provinceId") Integer provinceId,@Param("townId") Integer townId,
                                           @Param("countyId") Integer countyId,@Param("tradeId") Integer tradeId,
                                           @Param("isRecommend") Integer isRecommend,
                                           @Param("curriculumStageId") Integer curriculumStageId,
                                           @Param("searchParam") String searchParam,
                                           @Param("curriculumTypeId") Integer curriculumTypeId);




    /**
     * 后台页面 分页获取课程
     * @param map map
     * @return
     */
    List<Curriculum> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取课程 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 后台页面 分页获取课程（用户）
     * @param map map
     * @return
     */
    List<Curriculum> listForAdminUser(Map<String , Object> map);
  /**
     * web页面 分页获取课程（用户）
     * @param map map
     * @return
     */
    List<Curriculum> listForWebUser(Map<String , Object> map);


    /**
     * 后台页面 分页获取课程 条数 (用户)
     * @param map map
     * @return
     */
    int listForAdminUserCount(Map<String , Object> map);




    /**
     * PC端 分页获取课程 获取试卷
     * @param map map
     * @return
     */
    List<Curriculum> listForPC(Map<String , Object> map);


    /**
     *  是否购买过此课程
     * @param userId
     * @param curriculumId
     * @return
     */
    Integer isBuy(@Param("userId") Integer userId , @Param("curriculumId") Integer curriculumId);

    /**
     * 删除课程
     * @param id
     */
    void delete(@Param("id") Integer id);


    /**
     * 获取用户已购买课程 并有试卷且为考试
     * @param userId
     * @return
     */
    List<Curriculum> getUserCurriculum(Integer userId);
}
