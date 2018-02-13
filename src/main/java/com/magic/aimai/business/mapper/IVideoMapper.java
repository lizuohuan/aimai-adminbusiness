package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Video Mapper
 * Created by Eric Xie on 2017/7/14 0014.
 */
public interface IVideoMapper {


    Integer addVideo(Video video);


    /**
     * 通过课时ID 查询 视频集合
     * @param courseWareId 课时ID
     * @return
     */
    List<Video> queryVideoByCourseWare(Map<String,Object> map);

    /**
     * 通过课时ID 查询 视频集合
     * @return
     */
    List<Video> querySimpleVideoByCourseWare(@Param("courseWareId") Integer courseWareId);

    /**
     * 更新视频
     * @param video
     */
    void update(Video video);

    /**
     * 后台页面 分页获取课时的视频
     * @param map map
     * @return
     */
    List<Video> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 分页获取课时的视频 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 课时视频详情
     * @param id 课时视频id
     * @return
     */
    Video info(@Param("id") Integer id);

    /**
     * 删除课时视频
     * @param id 视频id
     * @return
     */
    void delete(@Param("id") Integer id);

    /**
     * 删除课程课时视频
     * @param curriculumId 课程id
     * @return
     */
    void deleteByCurriculumId(@Param("curriculumId") Integer curriculumId);


    /**
     * 删除课时视频
     * @param courseWareId 课时id
     * @return
     */
    void deleteByCourseWareId(@Param("courseWareId") Integer courseWareId);



    /**
     * 此视频库中的视频除被此视频课件绑定 是否还被其他视频课件绑定
     * @param houseId
     * @return
     */
    Integer countVideoByHouseId(@Param("houseId") Integer houseId,@Param("id") Integer id);

    /**
     * 获取所有视频 的path路径
     * @return
     */
    List<String> getAllVideo();
}
