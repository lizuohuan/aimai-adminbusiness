package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.enums.VideoStatusEnum;
import com.magic.aimai.business.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频播放记录 Service
 * Created by Eric Xie on 2017/7/17 0017.
 */
@Service
public class VideoStatusService {

    @Resource
    private IVideoStatusMapper videoStatusMapper;
    @Resource
    private IFinishRecordMapper finishRecordMapper;
    @Resource
    private ICourseWareMapper courseWareMapper;
    @Resource
    private ICurriculumMapper curriculumMapper;
    @Resource
    private IPaperMapper paperMapper;
    @Resource
    private IPaperRecordMapper paperRecordMapper;

    /**
     * 新增播放记录
     * @param videoStatus
     */
    @Transactional
    public Map<String,Object> addVideoStatus(VideoStatus videoStatus){
        Map<String,Object> data = new HashMap<String, Object>();
        long isShowExercises = 0l; //是否弹出练习题
        long isSimulationExercise = 0l; // 是否弹出模拟题
        long isExaminationQuestion = 0l;//是否弹出考试题
        int isShowNote = 0;//是否提示
        VideoStatus v = videoStatusMapper.queryVideoStatus(videoStatus.getUserId(), videoStatus.getVideoId(),
                videoStatus.getOrderId(),videoStatus.getStatus());
        if(null != v && v.getSeconds() < videoStatus.getSeconds() && v.getStatus() == VideoStatusEnum.PausePlay.ordinal()){
            videoStatus.setId(v.getId());
            videoStatusMapper.updateVideoStatus(videoStatus);
        }
        Integer currIsFinish = 0;
        Integer isFinish = 0;
        if(null == v) {
            videoStatusMapper.addVideoStatus(videoStatus);
            // 播放结束后，记录播放状态
            // 查询是否存在 完成记录
            // type 0:课件  1:课程
            if(VideoStatusEnum.FinishPlay.ordinal() == videoStatus.getStatus()){
                CourseWare target = courseWareMapper.queryCourseWareByVideo(videoStatus.getVideoId());
                List<FinishRecord> finishRecordList = new ArrayList<FinishRecord>();
                // 统计该课件下的视频 是否看完
                isFinish = courseWareMapper.countCourseWareFinish(videoStatus.getUserId(), videoStatus.getOrderId(), target.getId());
                if(1 == isFinish){
                    Integer courseWare = finishRecordMapper.queryTargetFinishRecord(0, videoStatus.getUserId(), videoStatus.getVideoId(), videoStatus.getOrderId());
                    if(0 == courseWare){
                        finishRecordList.add(createFinishRecord(videoStatus.getOrderId(),videoStatus.getUserId(),target.getId(),0));
                    }
                }
                // 统计该课程下的视频 是否看完
                currIsFinish = curriculumMapper.countCurriculumFinish(videoStatus.getUserId(), videoStatus.getOrderId(), target.getCurriculumId());
                if(1 == currIsFinish){
                    Integer curriculum = finishRecordMapper.queryTargetFinishRecord(1, videoStatus.getUserId(), videoStatus.getVideoId(), videoStatus.getOrderId());
                    if(0 == curriculum){
                        finishRecordList.add(createFinishRecord(videoStatus.getOrderId(),videoStatus.getUserId(),target.getCurriculumId(),1));
                    }
                    //在没有试卷的情况下  完成直接进行考试通过记录插入
                    if (paperMapper.isHavePaperByCurriculumId(2,target.getCurriculumId()) == 0) {
                        PaperRecord  paperRecord = paperRecordMapper.getInfoByCurriculumId(2,target.getCurriculumId(),videoStatus.getUserId(),videoStatus.getOrderId());
                        if (null == paperRecord) {
                            paperRecord = new PaperRecord();
                            paperRecord.setType(2);
                            paperRecord.setOrderId(videoStatus.getOrderId());
                            paperRecord.setPaperId(target.getCurriculumId());
                            paperRecord.setPassScore(0);
                            paperRecord.setResultScore(1);
                            paperRecord.setUserId(videoStatus.getUserId());
                            paperRecordMapper.addPaperRecord(paperRecord);
                            isShowNote = 1;
                        }
                    }
                }
                if(finishRecordList.size() > 0){
                    finishRecordMapper.batchAddFinishRecord(finishRecordList);
                }
            }
        }
        // 如果状态是播放完成，判断是否返回试卷信息
        // 练习题、模拟题、考试题

        if(VideoStatusEnum.FinishPlay.ordinal() == videoStatus.getStatus()){
            if (1 == isFinish) {
                Map<String, Long> resultMap = videoStatusMapper.judge(videoStatus.getVideoId());
                if(resultMap.get("isShowExercises") == 1){
                    isShowExercises = 1;
                }
            }
            if(currIsFinish == 1){
                // 如果 课程下的课件全部播放完成
                //判断该课程下 是否有模拟题 和 考试题
                Map<String, Long> result = videoStatusMapper.judgeExamination(videoStatus.getVideoId());
                if(result.get("isSimulationExercise") > 0){
                    isSimulationExercise = 1;
                }

//                if(result.get("isExaminationQuestion") <= 0){
//                    // 课程完成后，如果没有考试题
//                    isExaminationQuestion = 1;
//                    // 新增记录
//                    VideoApprovedRecord record = new VideoApprovedRecord();
//                    record.setUserId(videoStatus.getUserId());
//                    record.setOrderId(videoStatus.getOrderId());
//                    videoApprovedRecordMapper.addVideoApprovedRecord(record);
//                }
            }
        }
        data.put("isShowExercises",isShowExercises);
        data.put("isSimulationExercise",isSimulationExercise);
        data.put("isExaminationQuestion",isExaminationQuestion);
        data.put("isShowNote",isShowNote);
        return data;
    }

    private FinishRecord createFinishRecord(Integer orderId,Integer userId,Integer targetId,Integer type){
        FinishRecord finishRecord = new FinishRecord();
        finishRecord.setOrderId(orderId);
        finishRecord.setUserId(userId);
        finishRecord.setTargetId(targetId);
        finishRecord.setType(type);
        return finishRecord;
    }


    /**
     * 获取最新的播放记录
     * @param userId
     * @param videoId
     * @return
     */
    public VideoStatus queryNewVideoStatus(Integer userId,Integer videoId,Integer orderId){
        return videoStatusMapper.queryNewStatus(userId,videoId,orderId);
    }


}
