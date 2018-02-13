package com.magic.aimai.business.service;

import com.magic.aimai.business.cache.MemcachedUtil;
import com.magic.aimai.business.entity.ForbiddenWords;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.mapper.IForbiddenWordsMapper;
import com.magic.aimai.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 禁词库 Service
 * Created by Eric Xie on 2017/7/26 0026.
 */
@Service
public class ForbiddenWordsService {

    @Resource
    private IForbiddenWordsMapper forbiddenWordsMapper;


    /**
     * 判断是否有禁词
     * @param word
     * @return
     */
    public boolean judgeWord(String word){
        List<ForbiddenWords> forbiddenWordses = (List<ForbiddenWords>) MemcachedUtil.getInstance().get(StatusConstant.WORD_CC);
        if(null == forbiddenWordses){
            forbiddenWordses = forbiddenWordsMapper.queryAllWord();
            MemcachedUtil.getInstance().add(StatusConstant.WORD_CC,forbiddenWordses);
        }
        for (ForbiddenWords forbiddenWord : forbiddenWordses) {
            if(word.indexOf(forbiddenWord.getWord()) >= 0){
                return true;
            }
        }
        return false;
    }






    public void updateForbiddenWords(ForbiddenWords forbiddenWords){
        forbiddenWordsMapper.updateForbiddenWords(forbiddenWords);
    }


    public void addForbiddenWords(ForbiddenWords forbiddenWords){
        forbiddenWordsMapper.addForbiddenWords(forbiddenWords);
    }

    /**
     * 后台页面 分页获取禁词
     * @param pageArgs 分页属性
     * @param word 禁词
     * @return
     */
    public PageList<ForbiddenWords> listForAdmin(PageArgs pageArgs , String word ) {
        PageList<ForbiddenWords> pageList = new PageList<ForbiddenWords>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("word",word);
        int count = forbiddenWordsMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(forbiddenWordsMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 禁词详情
     * @param id
     * @return
     */
    public ForbiddenWords info(Integer id) {
        return forbiddenWordsMapper.info(id);
    }
}
