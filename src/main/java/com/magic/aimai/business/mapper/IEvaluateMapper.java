package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Evaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/7/17 0017.
 */
public interface IEvaluateMapper {


    Evaluate queryEvaluateById(@Param("id") Integer id);

    /**
     * 新增评论
     * @param evaluate
     * @return
     */
    Integer addEvaluate(Evaluate evaluate);

    /**
     * 更新不为空的字段 通过ID
     * @param evaluate
     * @return
     */
    Integer updateEvaluate(@Param("evaluate") Evaluate evaluate);


    /**
     * 移动端 获取评论列表  自己未审核 和 非本人审核通过的
     * @param limit
     * @param limitSize
     * @param userId 当前获取人的用户
     * @return
     */
    List<Evaluate> queryEvaluate(@Param("userId") Integer userId,@Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                 @Param("curriculumId") Integer curriculumId);



    /**
     * 后台页面 分页获取评论
     * @param map map
     * @return
     */
    List<Evaluate> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取评论条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 删除评论
     * @param id
     */
    void delete(@Param("id") Integer id);

    /***
     * 批量删除
     * @param ids
     */
    void deleteList(@Param("ids") List<Integer> ids);

    /***
     * 批量通过
     * @param list
     */
    void updateList(@Param("list") List<Evaluate> list);
}
