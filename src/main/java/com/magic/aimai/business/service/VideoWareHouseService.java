package com.magic.aimai.business.service;

import com.alibaba.fastjson.JSONArray;
import com.magic.aimai.business.cache.MemcachedUtil;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.entity.Video;
import com.magic.aimai.business.entity.VideoWareHouse;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.IVideoMapper;
import com.magic.aimai.business.mapper.IVideoWareHouseMapper;
import com.magic.aimai.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频库 业务层
 * @author lzh
 * @create 2017/8/15 14:12
 */
@Service
public class VideoWareHouseService {

    @Resource
    private IVideoWareHouseMapper videoWareHouseMapper;
    @Resource
    private IVideoMapper videoMapper;
    /**
     * 批量新增
     * @param videoWareHouses
     */
    public void save(String videoWareHouses){
        List<VideoWareHouse> list = JSONArray.parseArray(videoWareHouses,VideoWareHouse.class);
        videoWareHouseMapper.save(list);
    }

    /**
     * 批量新增
     */
    public void save(List<VideoWareHouse> list){
        videoWareHouseMapper.save(list);
    }

    /**
     * 更新
     * @param videoWareHouse
     */
    public void update(VideoWareHouse videoWareHouse){
        VideoWareHouse videoWareHouse2 = videoWareHouseMapper.info(videoWareHouse.getId());
        if (!videoWareHouse2.getUrl().equals(videoWareHouse.getUrl())){
            String urls = videoWareHouse2.getUrl();
            if (null != urls) {
                //获取待删除的列表
                String session_urls = (String) MemcachedUtil.getInstance().get(Video.video_session_delete);
                if (null != session_urls && "".equals(session_urls)) {
                    session_urls += "," + urls;
                } else {
                    session_urls = urls;
                }
                //放入缓存
                MemcachedUtil.getInstance().add(Video.video_session_delete,session_urls);
            }
        }
        videoWareHouseMapper.update(videoWareHouse);
    }

    public void updateSigle(VideoWareHouse videoWareHouse){
        videoWareHouseMapper.update(videoWareHouse);
    }

    /**
     * 后台页面 分页获取视频库
     * @param pageArgs 分页属性
     * @param curriculumName 课程名
     * @param courseWareName 课时名
     * @param name 视频名
     * @param startTime  创建开始时间
     * @param endTime 创建结束时间
     * @param isBand 是否绑定 0：未绑定  1：已绑定
     * @param id 视频id
     * @return
     */
    public PageList<VideoWareHouse> listForAdmin(PageArgs pageArgs , String curriculumName , String courseWareName ,
                                                 String name , Date startTime , Date endTime ,Integer isBand,Integer id,
                                                 Integer isCloud) {
        PageList<VideoWareHouse> pageList = new PageList<VideoWareHouse>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("curriculumName",curriculumName);
        map.put("courseWareName",courseWareName);
        map.put("name",name);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("isBand",isBand);
        map.put("isCloud",isCloud);
        map.put("id",id);
        int count = videoWareHouseMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(videoWareHouseMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 获取视频详情
     * @param id
     * @return
     */
    public VideoWareHouse info(Integer id) {
        return videoWareHouseMapper.info(id);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public void delete(Integer id) {
        if (videoMapper.countVideoByHouseId(null,id) > 0) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"对不起，此视频已被绑定，不能删除");
        }
        VideoWareHouse videoWareHouse = videoWareHouseMapper.info(id);
        videoWareHouseMapper.delete(id);
        if (null != videoWareHouse) {
            String urls = videoWareHouse.getUrl();
            if (null != urls) {
                //获取待删除的列表
                String session_urls = (String) MemcachedUtil.getInstance().get(Video.video_session_delete);
                if (null != session_urls && "".equals(session_urls)) {
                    session_urls += "," + urls;
                } else {
                    session_urls = urls;
                }
                //放入缓存
                MemcachedUtil.getInstance().add(Video.video_session_delete,session_urls);
            }
        }
    }

}
