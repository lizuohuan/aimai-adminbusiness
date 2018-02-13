package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.ExaminationItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考题选项信息 Mapper
 * Created by Eric Xie on 2017/7/21 0021.
 */
public interface IExaminationItemsMapper {




    /**
     * 新增 考题选项信息
     * @param examinationItems
     * @return
     */
    Integer addExaminationItems(ExaminationItems examinationItems);

    /**
     * 批量新增 考题选项信息
     * @param examinationItemsList
     * @return
     */
    Integer batchAddExaminationItems(@Param("examinationItemsList") List<ExaminationItems> examinationItemsList);


    /**
     * 通过ID 修改更新考题选项信息
     * @param examinationItems
     * @return
     */
    Integer updateExaminationItems(@Param("examinationItems") ExaminationItems examinationItems);

    /**
     * 通过考题ID 查询考题下的选项
     * @param examinationId
     * @return
     */
    List<ExaminationItems> queryExaminationByExamination(@Param("examinationId") Integer examinationId);


    /**
     * 通过考题ID 查询考题下的选项
     * @param examinationId
     * @return
     */
    List<ExaminationItems> queryExaminationItemsByExamination(@Param("examinationId") Integer examinationId,
                                                              @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    Integer countExaminationItemsByExamination(@Param("examinationId") Integer examinationId);


    /**
     * 删除考题的答案
     * @param examinationId
     */
    void deteleByExaminationId(@Param("examinationId") Integer examinationId);


    /**
     * 答案详情
     * @param id
     */
    ExaminationItems info(@Param("id") Integer id);
}
