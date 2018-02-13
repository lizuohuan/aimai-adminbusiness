package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.entity.Suggest;
import com.magic.aimai.business.mapper.ISuggestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/7/13 0013.
 */
@Service
public class SuggestService {

    @Resource
    private ISuggestMapper suggestMapper;


    public void addSuggest(Suggest suggest){
        suggestMapper.addSuggest(suggest);
    }



    /**
     * 后台页面 分页获取意见反馈
     * @param pageArgs 分页属性
     * @param userName 反馈人
     * @param startTime  创建开始时间
     * @param endTime 创建结束时间
     * @return
     */
    public PageList<Suggest> listForAdmin(PageArgs pageArgs , String userName ,
                                       Date startTime , Date endTime ) {
        PageList<Suggest> pageList = new PageList<Suggest>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        int count = suggestMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(suggestMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }


    /**
     * 删除意见反馈
     * @param id
     */
    public void delete(Integer id){
        suggestMapper.delete(id);
    }
}
