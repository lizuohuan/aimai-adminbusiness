package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.VideoWareHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 视频库 持久层接口
 * @author lzh
 * @create 2017/8/15 10:46
 */
public interface IVideoWareHouseMapper {


    /**
     * 批量添加视频
     * @param list
     */
    void save(List<VideoWareHouse> list);

    /**
     * 删除视频
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 更新
     * @param videoWareHouse
     */
    void update(VideoWareHouse videoWareHouse);


    void updateIsBind(@Param("vIds") Set<Integer> vIds);

    /**
     * 批量更新
     * @param list
     */
    void updateList(List<VideoWareHouse> list);

    /**
     * 分页 视频库
     * @param map map
     * @return
     */
    List<VideoWareHouse> listForAdmin(Map<String , Object> map);


    /**
     * 后台页面 统计视频库
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);


    /**
     * 通过ID 视频库中的视频
     * @param id
     * @return
     */
    VideoWareHouse info(@Param("id") Integer id);

}
