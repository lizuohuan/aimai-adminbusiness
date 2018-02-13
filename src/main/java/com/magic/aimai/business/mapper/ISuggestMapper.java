package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Suggest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/7/13 0013.
 */
public interface ISuggestMapper {


    Integer addSuggest(Suggest suggest);

    /**
     * 后台页面 分页获取意见反馈
     * @param map map
     * @return
     */
    List<Suggest> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取意见反馈 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 删除意见反馈
     * @param id
     */
    void delete(@Param("id")Integer id);
}
