package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.Content;
import com.magic.aimai.business.mapper.IContentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 公司内容管理
 * @author lzh
 * @create 2017/8/3 21:26
 */
@Service
public class ContentService {

    @Resource
    private IContentMapper contentMapper;

    public void update(Content content) {
        contentMapper.update(content);
    }

    public Content info(Integer id) {
        return contentMapper.info(id);
    }
}
