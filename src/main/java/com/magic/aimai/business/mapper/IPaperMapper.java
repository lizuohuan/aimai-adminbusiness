package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.ExaminationPaper;
import com.magic.aimai.business.entity.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 试卷 mapper
 * Created by Eric Xie on 2017/7/21 0021.
 */
public interface IPaperMapper {


    /**
     * 通过试卷ID 查询试卷 包含试卷下所有的 考题
     * @param paperId 试卷ID
     * @return
     */
    Paper queryPaperById(@Param("paperId") Integer paperId);

    /**
     *  查询试卷的基本信息
     * @param paperId 试卷ID
     * @return
     */
    Paper queryBasePaperById(@Param("paperId") Integer paperId);

    /**
     *  试卷 和 试题 绑定
     * @param examinationPapers
     * @return
     */
    Integer bindPaper(@Param("examinationPapers") List<ExaminationPaper> examinationPapers);


    /**
     * 后台 查询 试卷
     * @param map
     * @return
     */
    List<Paper> queryPaperForAdmin(Map<String,Object> map);

    Integer countPaperForAdmin(Map<String,Object> map);

    /**
     *  通过目标ID 和 类型 查询该目标下的试卷集合
     * @param targetId
     * @param type
     * @return
     */
    List<Paper> queryPaperByItems(@Param("targetId") Integer targetId,@Param("type") Integer type);


    /**
     * 新增试卷
     * @param paper
     * @return
     */
    Integer addPaper(Paper paper);

    /**
     * 更新不为空的字段
     * @param paper
     * @return
     */
    Integer updatePaper(@Param("paper") Paper paper);

    /**
     * 根据课程id查询是否存在课程
     * @param type
     * @param type
     * @return
     */
    int isHavePaperByCurriculumId(@Param("type") Integer type, @Param("curriculumId") Integer curriculumId);


    /**
     * 多条件查询 试卷
     * @param type 试卷类型
     * @return
     */
    List<Paper> queryPaperByItem(@Param("type") Integer type);

    /**
     * 分页查询 试卷基本字段集合 参数可为null
     * @param type
     * @param limit
     * @param limitSize
     * @return
     */
    List<Paper> queryBasePaperByItem(@Param("type") Integer type,@Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


}
