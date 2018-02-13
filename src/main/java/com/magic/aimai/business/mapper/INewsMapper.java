package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/7/13 0013.
 */
public interface INewsMapper {


    /**
     * 分页查询
     * @param isRecommend
     * @param limit
     * @param limitSize
     * @return
     */
    List<News> queryNewsByItems(@Param("isRecommend") Integer isRecommend,@Param("limit") Integer limit,
                                @Param("limitSize") Integer limitSize);

    Integer addNews(News news);


    List<News> queryNewsByCollect(@Param("userId") Integer userId,@Param("limit") Integer limit,
                                  @Param("limitSize") Integer limitSize);


    /**
     * 获取资讯列表 包括被收藏的数量
     * @return
     */
    List<News> queryNews(Map<String,Object> map);


    /**
     * 获取资讯详情
     * @param id
     * @return
     */
    News info(@Param("id") Integer id);


    /**
     * 后台页面 分页获取新闻资讯
     * @param map map
     * @return
     */
    List<News> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 分页获取新闻资讯 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 更新
     * @param news
     */
    void update(News news);

    /**
     * 删除
     */
    void delete(@Param("id") Integer id);
}
