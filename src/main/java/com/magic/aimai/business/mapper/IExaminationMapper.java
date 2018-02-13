package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Examination;
import com.magic.aimai.business.entity.Trade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 考题 Mapper
 * Created by Eric Xie on 2017/7/21 0021.
 */
public interface IExaminationMapper {


    List<Examination> queryExaminationByCollect(@Param("userId") Integer userId,@Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 通过试题ID集合 查询错题解析
     * @param ids 错题ID集合
     * @return
     */
    List<Examination> queryErrorExaminationByIds(@Param("ids") List<Integer> ids);

    /**
     * 获取错题解析
     * @param orderId
     * @param userId
     * @param limit
     * @param limitSize
     * @return
     */
    List<Examination> queryErrorExamination(@Param("orderId") Integer orderId,@Param("userId") Integer userId,@Param("courseWareId") Integer courseWareId,
                                            @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    Examination queryBaseExamination(@Param("examinationId") Integer examinationId);

    /**
     * 新增考题
     * @param examination
     * @return
     */
    Integer addExamination(Examination examination);

    /**
     * 通过ID  更新不为空的字段
     * @param examination
     * @return
     */
    Integer updateExamination(@Param("examination") Examination examination);


    /**
     * 通过试卷ID 查询考题集合 包含了 考题的选项以及答案信息
     * @param paperId
     * @return
     */
    List<Examination> queryExaminationByPaper(@Param("paperId") Integer paperId);


    /**
     * 后台页面 分页获取考题 包含了 考题的选项以及答案信息
     * @param map map
     * @return
     */
    List<Examination> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 分页获取考题 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);


    /**
     * 考题详情
     * @param id 考题id
     * @return
     */
    Examination info(@Param("id") Integer id);


    /**
     * 考题详情
     * @param id 考题id
     * @return
     */
    Examination queryBaseExaminationById(@Param("id") Integer id);


    /**
     * 循环添加考题
     * @param list
     */
    void addList(@Param("list") List<Examination> list);


    /**
     * 删除试题
     * @param id
     */
    void delete(@Param("id") Integer id);
}
