package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.enums.Common;
import com.magic.aimai.business.enums.PaperEnum;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.IErrorExaminationMapper;
import com.magic.aimai.business.mapper.IPaperMapper;
import com.magic.aimai.business.mapper.IPaperRecordMapper;
import com.magic.aimai.business.util.StatusConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 试卷 Service
 * Created by Eric Xie on 2017/7/21 0021.
 */
@Service
public class PaperService {


    @Resource
    private IPaperMapper paperMapper;
    @Resource
    private IPaperRecordMapper paperRecordMapper;
    @Resource
    private IErrorExaminationMapper errorExaminationMapper;



    public Paper queryPaperById(Integer paperId){
        return paperMapper.queryPaperById(paperId);
    }




    public Paper queryBasePaper(Integer paperId){
        return paperMapper.queryBasePaperById(paperId);
    }


    public void bindPaper(JSONArray jsonArray){
        List<ExaminationPaper> list = JSONArray.toList(jsonArray,ExaminationPaper.class);

        paperMapper.bindPaper(list);
    }


    /**
     * 后台获取 试卷列表
     * @param pageArgs
     * @param params
     * @return
     */
    public PageList<Paper> queryPaper(PageArgs pageArgs, Map<String,Object> params){
        PageList<Paper> list = new PageList<Paper>();
        Integer count = paperMapper.countPaperForAdmin(params);
        if(count > 0){
            params.put("limit",pageArgs.getPageStart());
            params.put("limitSize",pageArgs.getPageSize());
            list.setList(paperMapper.queryPaperForAdmin(params));
        }
        list.setTotalSize(count);
        return list;
    }


    /**
     * 试卷提交 业务处理
     * @param jsonArray 提交的数据格式
     *    [
     *      {
     *       "examinationId":1,
     *       "answers":[1,2]
     *      },
     *      {
     *       "examinationId":2,
     *       "answers":[1]
     *      }
     *    ]
     * @param paperId 试卷ID
     * @param orderId 订单ID
     * @param user 参考者
     * @return 答案数组
     */
    @Transactional
    public Map<String,Object> submitPaper(JSONArray jsonArray, Integer paperId, Integer orderId, User user,Integer seconds) throws Exception{
        Map<String,Object> data = new HashMap<String, Object>();
        Paper paper = paperMapper.queryPaperById(paperId);
        if(null == paper){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"试卷不存在");
        }
        Integer count = 0;
        if(PaperEnum.ExaminationQuestion.ordinal() == paper.getType()){
            // 如果是正式考试，已考的次数
            count = paperRecordMapper.countPager(user.getId(), orderId, paper.getId());
            if(count > 3){
                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"考试次数已用完");
            }
        }
        if(null == jsonArray){
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"参数异常");
        }
        if(null == paper.getExaminationList() ||  paper.getExaminationList().size() == 0){
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"数据异常");
        }
        // 错题集合
        List<ErrorExamination> errors = new ArrayList<ErrorExamination>();
        // 正确集合
        List<ErrorExamination> rights = new ArrayList<ErrorExamination>();
        // 记录对象
        PaperRecord record = new PaperRecord();
        record.setUserId(user.getId());
        record.setOrderId(orderId);
        record.setPaperId(paperId);
        record.setPassScore(paper.getPassScore());
        record.setSeconds(seconds);
        int exerciseNum = 0; // 练习题数
        int countNum = 0; // 试题总数
        int correctNum = 0; // 试题正确数量
        int paperScore = 0; // 试卷总分数
        int resultScore = 0; // 考试得分
        JSONArray resultArr = new JSONArray();
        for (Examination examination : paper.getExaminationList()) {
            if(null == examination.getExaminationItemsList() || examination.getExaminationItemsList().size() == 0){
                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"数据异常");
            }
            boolean isExist = false;
            // 寻找匹配的题
            for (Object o : jsonArray) {
                JSONObject jsonObject = JSONObject.fromObject(o);
                Integer examinationId = jsonObject.getInt("examinationId");
                if(examinationId.equals(examination.getId())){
                    // 题目相同 判断提交的答案是否正确
                    isExist = true;
                    JSONArray answers = jsonObject.getJSONArray("answers"); // 用户提交的答案集合
                    JSONArray correctAnswers = new JSONArray(); // 获取正确答案集合
                    for (ExaminationItems items : examination.getExaminationItemsList()) {
                        if( Common.YES.ordinal() == items.getIsCorrect()){
                            correctAnswers.add(items.getId());
                        }
                    }
                    if(answers.equals(correctAnswers)){
                        // 答案正确
                        correctNum++;
                        resultScore+=examination.getScore();
                        jsonObject.put("isCorrect",1);
                        ErrorExamination right = new ErrorExamination();
                        right.setOrderId(orderId);
                        right.setUserId(user.getId());
                        right.setExaminationId(examinationId);
                        rights.add(right);
                        resultArr.add(jsonObject);
                    }else{
                        jsonObject.put("isCorrect",0);
                        ErrorExamination error = new ErrorExamination();
                        error.setOrderId(orderId);
                        error.setUserId(user.getId());
                        error.setExaminationId(examinationId);
                        errors.add(error);
                        resultArr.add(jsonObject);
                    }
                    break;
                }
            }
            if(!isExist){
                exerciseNum ++;
                ErrorExamination error = new ErrorExamination();
                error.setOrderId(orderId);
                error.setUserId(user.getId());
                error.setExaminationId(examination.getId());
                errors.add(error);
            }
            countNum ++;
            paperScore += examination.getScore();
        }

        // 新增错题集合
        // 用户现有的错题集合
        List<ErrorExamination> sqlErrors = errorExaminationMapper.queryErrorExamination(orderId, user.getId());
        if(null != sqlErrors && sqlErrors.size() > 0){
            // 合并错题集合
            for (ErrorExamination sqlError : sqlErrors) {
                if(!errors.contains(sqlError) && !rights.contains(sqlError)){
                    errors.add(sqlError);
                }
            }
        }
        if(errors.size() > 0){
            // 执行删除后 添加数据
            errorExaminationMapper.delErrorExamination(orderId, user.getId());
            errorExaminationMapper.batchAddErrorExamination(errors);
        }

        data.put("jsonArray",resultArr);
        data.put("correctNum",correctNum);
        data.put("passScore",paper.getPassScore());
        data.put("resultScore",resultScore);
        data.put("count",3 - count - 1);
        record.setExerciseNum(exerciseNum);
        record.setCorrectNum(correctNum);
        record.setCountNum(countNum);
        record.setPaperScore(paperScore);
        record.setResultScore(resultScore);
        record.setResultDetail(JSONObject.fromObject(data).toString());
        record.setType(0);
        paperRecordMapper.addPaperRecord(record);
//        if(resultScore >= paper.getPassScore()){
//            // 新增通过记录
//            VideoApprovedRecord approved = new VideoApprovedRecord();
//            approved.setUserId(record.getUserId());
//            approved.setOrderId(record.getOrderId());
//            videoApprovedRecordMapper.addVideoApprovedRecord(approved);
//
//        }
        return data;
    }


    public PaperDetailResult queryPaperRecord(Integer userId,Integer orderId,Integer paperId){

        PaperDetailResult result = new PaperDetailResult();
        PaperRecord record = paperRecordMapper.queryPaperRecord(userId, orderId, paperId);
        if(null != record){
            JSONObject jsonObject = JSONObject.fromObject(record.getResultDetail());
            JSONArray jsonArray = jsonObject.getJSONArray("jsonArray");
            for (Object o : jsonArray) {
                JSONObject object = JSONObject.fromObject(o);
                Answers answers = new Answers();
                answers.setExaminationId(object.getInt("examinationId"));
                answers.setIsCorrect(object.getInt("isCorrect"));
                answers.setAnswers(JSONArray.toList(object.getJSONArray("answers"),Integer.class));
                result.getJsonArray().add(answers);
            }
            result.setCorrectNum(jsonObject.getInt("correctNum"));
            result.setResultScore(jsonObject.getInt("resultScore"));
            result.setPassScore(jsonObject.getInt("passScore"));
            result.setCount(record.getCount() > 3 ? 0 : 3 - record.getCount());
        }
        return result;
    }

    /**
     *  通过目标ID 和 类型 查询该目标下的试卷集合
     * @param targetId
     * @param type
     * @return
     */
    public List<Paper> queryPaperByItems(Integer targetId,Integer type){
        return paperMapper.queryPaperByItems(targetId,type);
    }


    /**
     * 分页查询试卷 基础信息 参数可为null
     * @param type
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Paper> queryBasePaperByItem(Integer type,Integer pageNO,Integer pageSize){
        return paperMapper.queryBasePaperByItem(type,null != pageNO && null != pageSize ? (pageNO - 1) * pageSize : null,
                null == pageSize ? null : pageSize);
    }


    /**
     * 通过ID 更新试卷不为空的字段
     * @param paper
     * @throws Exception
     */
    public void updatePaper(Paper paper) {
        paperMapper.updatePaper(paper);
    }

    /**
     * 新增试卷
     * @param paper
     */
    public void addPaper(Paper paper){
        paperMapper.addPaper(paper);
    }


}
