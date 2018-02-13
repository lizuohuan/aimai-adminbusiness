package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.ExaminationPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 试卷试题
 * @author lzh
 * @create 2017/8/3 16:37
 */
public interface IExaminationPaperMapper {

    /**
     * 后台页面 分页试卷的试题
     * @param map map
     * @return
     */
    List<ExaminationPaper> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 分页获取试卷的试题条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);


    /**
     * 删除试题
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 统计是否被试卷绑定
     * @param examinationId 试题id
     * @return
     */
    int countExaminationPaperByExaminationId(@Param("examinationId") Integer examinationId);


}

