package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.mapper.ICityMapper;
import com.magic.aimai.business.mapper.INewsMapper;
import com.magic.aimai.business.mapper.ISystemInfoMapper;
import com.magic.aimai.business.push.PushMessageUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 资讯业务
 * Created by Eric Xie on 2017/7/24 0024.
 */
@Service
public class NewsService {

    @Resource
    private INewsMapper newsMapper;
    @Resource
    private ISystemInfoMapper systemInfoMapper;
    @Resource
    private ICityMapper cityMapper;
    /**
     * 动态获取资讯
     * @param isRecommend
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<News> queryNewsByItems(Integer isRecommend,Integer pageNO,Integer pageSize){
        return newsMapper.queryNewsByItems(isRecommend,null == pageNO || null == pageSize ?
                null : (pageNO - 1) * pageSize,null == pageSize ? null : pageSize );
    }


    public List<News> queryNews(Integer pageNO,Integer pageSize,String searchParam,Integer cityId,
                                Integer type,Integer sort){
        //
        Integer provinceId = null;
        Integer townId = null;
        Integer countyId = null;
        if(null != cityId){
            City city = cityMapper.queryCity(cityId);
            if(null != city){
                if(city.getLevelType() == 1){
                    provinceId = city.getId();
                }
                else if(city.getLevelType() == 2){
                    townId = city.getId();
                }
                else if(city.getLevelType() == 3){
                    countyId = city.getId();
                }
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("provinceId",provinceId);
        map.put("townId",townId);
        map.put("countyId",countyId);
        map.put("type",type);
        map.put("sort",sort);
        map.put("limit",(pageNO - 1) * pageSize);
        map.put("limitSize",pageSize);
        map.put("searchParam",searchParam);
        return newsMapper.queryNews(map);
    }


    public Page<News> queryNewsOfWeb(Integer pageNO, Integer pageSize){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("provinceId",null);
        map.put("townId",null);
        map.put("countyId",null);
        map.put("type",null);
        map.put("sort",null);

        map.put("searchParam",null);
        List<News> counts = newsMapper.queryNews(map);
        List<News> newses = new ArrayList<News>();
        if(null != counts && counts.size() > 0){
            map.put("limit",(pageNO - 1) * pageSize);
            map.put("limitSize",pageSize);
             newses = newsMapper.queryNews(map);
        }
        return new Page<News>(newses,null == counts ? 0 : counts.size(),0);
    }

    public News info(Integer id){
        News info = newsMapper.info(id);
        //封装省市区
        if (null != info.getCityId()) {
            City city = cityMapper.getThreeId(info.getCityId());
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(city.getProvinceId());
            jsonArray.add(city.getTownId());
            jsonArray.add(city.getId());
            info.setCityJsonAry(jsonArray);
        }
        return info;
    }


    /**
     * 后台页面 分页获取新闻资讯
     * @param pageArgs 分页属性
     * @param title 标题
     * @param editor 责任编辑人
     * @param type 类型 0行业动态、1重大新闻、2安全事故、3安全常识、4考试、5其他
     * @param startTime  创建开始时间
     * @param endTime 创建结束时间
     * @return
     */
    public PageList<News> listForAdmin(PageArgs pageArgs , String title , String editor , Integer type ,
                                       Date startTime , Date endTime ) {
        PageList<News> pageList = new PageList<News>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("title",title);
        map.put("editor",editor);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("type",type);
        int count = newsMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(newsMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 新增新闻资讯
     * @param news
     * @throws Exception
     */
    @Transactional
    public void save(News news) throws Exception{
        newsMapper.addNews(news);
        SystemInfo info = new SystemInfo();
        info.setType(0);
        info.setDigest(news.getDigest());
        info.setNewsId(news.getId());
        info.setTitle(news.getTitle());
        systemInfoMapper.addSystemInfo(info);
        // 广播所有人
        PushMessageUtil.pushMessages(null,info.getTitle(),null);
    }
    /**
     * 更新不为空的字段
     * @param news
     * @throws Exception
     */
    public void update(News news){
        newsMapper.update(news);
    }

    /**
     * 删除
     * @param id
     * @throws Exception
     */
    @Transactional
    public void delete(Integer id){
        //1：删除查看记录
        systemInfoMapper.deleteForUser(id,0);
        //2：删除绑定消息
        systemInfoMapper.delete(id,0);
        //3：删除新闻资讯
        newsMapper.delete(id);
    }

}
