package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.ForbiddenWords;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 禁词库 Mapper
 * Created by Eric Xie on 2017/7/26 0026.
 */
public interface IForbiddenWordsMapper {


    Integer addForbiddenWords(ForbiddenWords forbiddenWords);


    Integer updateForbiddenWords(@Param("forbiddenWords") ForbiddenWords forbiddenWords);


    List<ForbiddenWords> queryAllWord();

    /**
     * 后台页面 分页获取禁词
     * @param map map
     * @return
     */
    List<ForbiddenWords> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取禁词条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     *
     * @param id
     * @return
     */
    ForbiddenWords info(@Param("id")Integer id);

}
