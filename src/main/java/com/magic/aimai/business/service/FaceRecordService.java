package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.FaceRecord;
import com.magic.aimai.business.entity.Page;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.mapper.IFaceRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Eric Xie on 2017/7/25 0025.
 */
@Service
public class FaceRecordService {

    @Resource
    private IFaceRecordMapper faceRecordMapper;


    /**
     * 新增人脸验证记录
     * @param faceRecord
     */
    public void addFaceRecord(FaceRecord faceRecord){
        faceRecordMapper.addFaceRecord(faceRecord);
    }


    /**
     * 获取人脸验证记录集合
     * @param userId 用户ID
     * @param videoId 视频ID
     * @param status 验证的状态
     * @return
     */
    public List<FaceRecord> queryFaceRecord(Integer userId,Integer videoId,Integer status,Integer orderId){
        List<FaceRecord> faceRecords = faceRecordMapper.queryFaceRecordByItems(userId, videoId, status,orderId);
        return faceRecords;
    }


    /**
     * 后台页面  分页人脸验证
     * @param pageArgs 分页属性
     * @param courseWareId 课时id
     * @param orderId 订单id
     * @param videoName 视频名
     * @param courseWareName 课时名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public PageList<FaceRecord> listForAdmin(PageArgs pageArgs, Integer courseWareId ,
                                             Integer orderId, String videoName,
                                             String courseWareName , Date startTime ,Date endTime ,Integer userId) {
        PageList<FaceRecord> pageList = new PageList<FaceRecord>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("courseWareId",courseWareId);
        map.put("orderId",orderId);
        map.put("videoName",videoName);
        map.put("courseWareName",courseWareName);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("userId",userId);
        int count = faceRecordMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(faceRecordMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     *  分页人脸验证
     * @param courseWareId 课时id
     * @param orderId 订单id
     * @param videoName 视频名
     * @param courseWareName 课时名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public Page<FaceRecord> listForAdmin(Integer pageNO,Integer pageSize, Integer courseWareId ,
                                         Integer orderId, String videoName,
                                         String courseWareName , Date startTime , Date endTime , Integer userId) {
        List<FaceRecord> pageList = new ArrayList<FaceRecord>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("courseWareId",courseWareId);
        map.put("orderId",orderId);
        map.put("videoName",videoName);
        map.put("courseWareName",courseWareName);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("userId",userId);
        int count = faceRecordMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("limit",(pageNO - 1) * pageSize);
            map.put("limitSize",pageSize);
            pageList = faceRecordMapper.listForWeb(map);
        }
        return new Page<FaceRecord>(pageList,count,0);
    }
}
