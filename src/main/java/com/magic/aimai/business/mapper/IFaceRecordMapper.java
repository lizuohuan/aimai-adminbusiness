package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.FaceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 人脸验证 记录Mapper
 * Created by Eric Xie on 2017/7/25 0025.
 */
public interface IFaceRecordMapper {


    /**
     * 新增记录
     * @param faceRecord
     * @return
     */
    Integer addFaceRecord(FaceRecord faceRecord);


    /**
     *  动态查询记录集合
     * @param userId
     * @param videoId
     * @param status
     * @return
     */
    List<FaceRecord> queryFaceRecordByItems(@Param("userId") Integer userId,@Param("videoId") Integer videoId,
                                            @Param("status") Integer status,@Param("orderId") Integer orderId);



    /**
     * 后台页面 分页获取人脸采集列表
     * @param map map
     * @return
     */
    List<FaceRecord> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 分页获取人脸采集列表
     * @param map map
     * @return
     */
    List<FaceRecord> listForWeb(Map<String , Object> map);

    /**
     * 后台页面 分页获取人脸采集列表 条数
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

}
