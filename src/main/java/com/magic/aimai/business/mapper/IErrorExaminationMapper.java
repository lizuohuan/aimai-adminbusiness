package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.ErrorExamination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Eric Xie on 2017/8/2 0002.
 */
public interface IErrorExaminationMapper {


    /**
     * 批量新增错题记录
     * @param errors
     * @return
     */
    Integer batchAddErrorExamination(@Param("errors") List<ErrorExamination> errors);


    /**
     * 查询用户的错题记录
     * @param orderId
     * @param userId
     * @return
     */
    List<ErrorExamination> queryErrorExamination(@Param("orderId") Integer orderId,@Param("userId") Integer userId);


    Integer delErrorExamination(@Param("orderId") Integer orderId,@Param("userId") Integer userId);

}
