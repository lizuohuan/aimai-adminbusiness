package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.VideoStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Eric Xie on 2017/7/17 0017.
 */
public interface IVideoStatusMapper {


    /**
     * 添加播放记录
     * @param videoStatus
     * @return
     */
    Integer addVideoStatus(VideoStatus videoStatus);

    /**
     * 更新状态
     * @param videoStatus
     * @return
     */
    Integer updateVideoStatus(@Param("videoStatus") VideoStatus videoStatus);

    /**
     *  获取 最近的播放状态
     * @param userId
     * @param videoId
     * @return
     */
    VideoStatus queryNewStatus(@Param("userId") Integer userId,@Param("videoId") Integer videoId,@Param("orderId") Integer orderId);
    /**
     *  获取 播放状态
     * @param userId
     * @param videoId
     * @return
     */
    VideoStatus queryVideoStatus(@Param("userId") Integer userId,@Param("videoId") Integer videoId,
                                 @Param("orderId") Integer orderId,@Param("status") Integer status);


    /**
     *  获取 最近的播放状态
     * @return
     */
    VideoStatus queryNewStatusByUserId(Map<String,Object> map);


    /**
     * 视频播放完后，查询是否有练习题  以及 该课程下的 课件 是否全部播放完
     * @param videoId
     * @return
     */
    Map<String,Long> judge(@Param("videoId") Integer videoId);

    /**
     * 视频播放完后，查询是否有 考试题 以及 模拟题
     * @param videoId
     * @return
     */
    Map<String,Long> judgeExamination(@Param("videoId") Integer videoId);

}
