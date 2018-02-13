package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Content;

/**
 * 公司内容管理
 * @author lzh
 * @create 2017/8/3 21:19
 */
public interface IContentMapper {


    /**
     * 更新
     * @param content
     */
    void update(Content content);

    /**
     * 详情
     * @param id
     * @return
     */
    Content info(Integer id);

}
