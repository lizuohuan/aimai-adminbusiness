package com.magic.aimai.business.service;

import com.magic.aimai.business.cache.MemcachedUtil;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.entity.Video;
import com.magic.aimai.business.entity.VideoWareHouse;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.IVideoMapper;
import com.magic.aimai.business.mapper.IVideoWareHouseMapper;
import com.magic.aimai.business.util.StatusConstant;
import org.apache.commons.collections.list.TreeList;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课时视频
 * @author lzh
 * @create 2017/7/21 14:43
 */
@Service
public class VideoService {

    @Resource
    private IVideoMapper videoMapper;

    @Resource
    private IVideoWareHouseMapper videoWareHouseMapper;

    /**
     * 后台页面 分页获取行业分类
     * @param pageArgs 分页属性
     * @param name 行业名
     * @return
     */
    public PageList<Video> listForAdmin(PageArgs pageArgs , String name ,Integer courseWareId,Integer source) {
        PageList<Video> pageList = new PageList<Video>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("source",source);
        map.put("courseWareId",courseWareId);
        int count = videoMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(videoMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 添加课程课时
     * @param video
     */
    @Transactional
    public void save(Video video){
        videoMapper.addVideo(video);
        List<VideoWareHouse> list = new ArrayList<VideoWareHouse>();
        if (video.getSourceHigh() == 1 || video.getSourceLow() == 1) {
            if (video.getSourceHigh() == 1) {
                if (null == video.getVideoWareHouseHighId()) {
                    throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请选择的高清视频");
                }
//                if (video.getVideoWareHouseHighId().equals(video.getVideoWareHouseLowId())) {
//                    throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请不要选择同样的视频");
//                }
                VideoWareHouse videoWareHouse = new VideoWareHouse();
                videoWareHouse.setIsBand(1);
                videoWareHouse.setId(video.getVideoWareHouseHighId());
                list.add(videoWareHouse);
            }
            if (video.getSourceLow() == 1) {
                if (null == video.getVideoWareHouseLowId()) {
                    throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请选择的流程视频");
                }

//                if (video.getVideoWareHouseLowId().equals(video.getVideoWareHouseHighId())) {
//                    throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请不要选择同样的视频");
//                }
                VideoWareHouse videoWareHouse = new VideoWareHouse();
                videoWareHouse.setIsBand(1);
                videoWareHouse.setId(video.getVideoWareHouseLowId());
                list.add(videoWareHouse);
            }
            videoWareHouseMapper.updateList(list);
        }

    }

    /**
     * 更新课程课时
     * @param video
     */
    public void update(Video video){
        //修改前的视频
        Video oldVideo = videoMapper.info(video.getId());

        String urls = null;
        if (oldVideo.getSourceHigh() == 1 && videoMapper.countVideoByHouseId(oldVideo.getVideoWareHouseHighId(),video.getId()) == 0) {
            VideoWareHouse old1 = new VideoWareHouse();
            old1.setIsBand(0);
            old1.setId(oldVideo.getVideoWareHouseHighId());
        } else {
            if (!oldVideo.getHighDefinition().equals(video.getHighDefinition())) {
                urls = oldVideo.getHighDefinition();
            }
        }

        if (oldVideo.getSourceLow() == 1 && videoMapper.countVideoByHouseId(oldVideo.getVideoWareHouseLowId(),video.getId()) == 0) {
            VideoWareHouse old2 = new VideoWareHouse();
            old2.setIsBand(0);
            old2.setId(oldVideo.getVideoWareHouseLowId());
        } else {
            if (!oldVideo.getLowDefinition().equals(video.getLowDefinition())) {
                if (null == urls) {
                    urls = oldVideo.getLowDefinition();
                } else {
                    urls += "," + oldVideo.getLowDefinition();
                }
            }
        }
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
        videoMapper.update(video);
        List<VideoWareHouse> list = new ArrayList<VideoWareHouse>();
        if (video.getSourceHigh() == 1) {
            if (null == video.getVideoWareHouseHighId()) {
                throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请选择的高清视频 ");
            }
//            if (video.getVideoWareHouseHighId().equals(video.getVideoWareHouseLowId())) {
//                throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请不要选择同样的视频");
//            }
            VideoWareHouse videoWareHouse = new VideoWareHouse();
            videoWareHouse.setIsBand(1);
            videoWareHouse.setId(video.getVideoWareHouseHighId());
            list.add(videoWareHouse);
        }
        if (video.getSourceLow() == 1) {
            if (null == video.getVideoWareHouseLowId()) {
                throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请选择的流程视频 ");
            }
//            if (video.getVideoWareHouseLowId().equals(video.getVideoWareHouseHighId())) {
//                throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请不要选择同样的视频");
//            }
            VideoWareHouse videoWareHouse = new VideoWareHouse();
            videoWareHouse.setIsBand(1);
            videoWareHouse.setId(video.getVideoWareHouseLowId());
            list.add(videoWareHouse);
        }
        if (oldVideo.getSourceHigh() == 1 || oldVideo.getSourceLow() == 1 || video.getSourceHigh() == 1 || video.getSourceLow() == 1) {
            videoWareHouseMapper.updateList(list);
        }
    }

    /**
     * 课时视频详情
     * @param id
     */
    public Video info(Integer id){
        return videoMapper.info(id);
    }

    /**
     * 删除课时视频
     * @param id
     */
    public void delete(Integer id){

        Video video = videoMapper.info(id);
        videoMapper.delete(id);
        if (null != video) {
            String urls = null;
            if (null == video.getVideoWareHouseLowId()) {
                urls = video.getHighDefinition();
            }
            if (null == video.getVideoWareHouseLowId()) {
                if (null == urls){
                    urls = video.getLowDefinition();
                } else {
                    urls += "," + video.getLowDefinition();
                }
            }
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


    public List<String> getAllVideo(){
        return videoMapper.getAllVideo();
    }


    @Transactional
    public void saveCloud(String name, String cover, Integer courseWareId, Integer cloudVideoId) throws Exception {

        // FD(流畅)，LD(标清)，SD(高清)，HD(超清)，OD(原画)，2K(2K)，4K(4K)
        VideoWareHouse info = videoWareHouseMapper.info(cloudVideoId);
        if(null == info){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"云视频不存在");
        }
        Video video = new Video();
        video.setName(name);
        video.setCourseWareId(courseWareId);
        video.setCover(cover);
        video.setHighDefinition(info.getLd());
        video.setHighDefinitionSeconds(info.getSeconds());
        video.setSourceHigh(2);
        video.setVideoWareHouseHighId(cloudVideoId);

        video.setLowDefinition(info.getFd());
        video.setLowDefinitionSeconds(info.getSeconds());
        video.setSourceLow(2);
        video.setVideoWareHouseLowId(cloudVideoId);
        videoMapper.addVideo(video);

        VideoWareHouse v = new VideoWareHouse();
        v.setId(info.getId());
        v.setIsBand(1);
        videoWareHouseMapper.update(v);
    }


    @Transactional
    public void updateCloud(String name, String cover, Integer courseWareId, Integer cloudVideoId,Integer id) throws Exception {

        // FD(流畅)，LD(标清)，SD(高清)，HD(超清)，OD(原画)，2K(2K)，4K(4K)
        VideoWareHouse info = videoWareHouseMapper.info(cloudVideoId);
        if(null == info){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"云视频不存在");
        }
        Video video = new Video();
        video.setId(id);
        video.setName(name);
        video.setCourseWareId(courseWareId);
        video.setCover(cover);
        video.setHighDefinition(info.getLd());
        video.setHighDefinitionSeconds(info.getSeconds());
        video.setSourceHigh(2);
        video.setVideoWareHouseHighId(cloudVideoId);

        video.setLowDefinition(info.getFd());
        video.setLowDefinitionSeconds(info.getSeconds());
        video.setSourceLow(2);
        video.setVideoWareHouseLowId(cloudVideoId);
        videoMapper.update(video);

        VideoWareHouse v = new VideoWareHouse();
        v.setId(info.getId());
        v.setIsBand(1);
        videoWareHouseMapper.update(v);
    }


}
