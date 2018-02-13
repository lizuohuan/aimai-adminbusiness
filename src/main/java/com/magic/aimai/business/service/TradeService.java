package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.entity.Trade;
import com.magic.aimai.business.mapper.ITradeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行业分类 业务
 * @author lzh
 * @create 2017/7/17 14:24
 */
@Service
public class TradeService {

    @Resource
    private ITradeMapper tradeMapper;


    public Trade queryTradeById(Integer tradeId){
        return tradeMapper.queryTradeById(tradeId);
    }

    public List<Trade> queryAllTrade(Integer pageNO,Integer pageSize){
        Integer limit = null == pageNO || null == pageSize ? null : (pageNO - 1) * pageSize;
        Integer limitSize = null == pageSize ? null : pageSize;
        return tradeMapper.queryTrade(limit, limitSize);
    }


    /**
     * 后台页面 分页获取行业分类
     * @param pageArgs 分页属性
     * @param tradeName 行业名
     * @return
     */
    public PageList<Trade> listForAdmin(PageArgs pageArgs ,String tradeName ) {
        PageList<Trade> pageList = new PageList<Trade>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("tradeName",tradeName);
        int count = tradeMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(tradeMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 添加行业分类
     * @param trade
     */
    public void save(Trade trade){
        tradeMapper.save(trade);
    }

    /**
     * 更新行业分类
     * @param trade
     */
    public void update(Trade trade){
        tradeMapper.update(trade);
    }

}
