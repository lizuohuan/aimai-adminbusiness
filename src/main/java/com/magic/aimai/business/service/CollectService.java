package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.enums.CollectEnum;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.ICollectMapper;
import com.magic.aimai.business.mapper.ICurriculumMapper;
import com.magic.aimai.business.mapper.IExaminationMapper;
import com.magic.aimai.business.mapper.INewsMapper;
import com.magic.aimai.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收藏 Service
 * Created by Eric Xie on 2017/7/14 0014.
 */
@Service
public class CollectService {

    @Resource
    private ICollectMapper collectMapper;
    @Resource
    private ICurriculumMapper curriculumMapper;
    @Resource
    private INewsMapper newsMapper;
    @Resource
    private IExaminationMapper examinationMapper;



    public int isCollect(Integer userId,Integer type,Integer targetId){
        return null == collectMapper.checkCollect(userId, type, targetId) ? 0 : 1;
    }

    public void addCollect(Collect collect) throws Exception {
        Collect temp = collectMapper.checkCollect(collect.getUserId(), collect.getType(), collect.getTargetId());
        if (null != temp) {
            collectMapper.del(temp.getId());
        }else{
            collectMapper.addCollect(collect);
        }
    }

    /**
     * 获取收藏 列表
     * @param type 类型
     * @param userId 用户ID
     * @param pageNO 页码
     * @param pageSize 尺寸
     * @throws Exception
     */
    public Object queryCollectByType(Integer type, Integer userId, Integer pageNO, Integer pageSize) throws Exception {
        Object obj = null;
        if (CollectEnum.Curriculum.ordinal() == type) {
            obj = curriculumMapper.queryCurriculumByCollect(userId, (pageNO - 1) * pageSize, pageSize);
        }
        else if(CollectEnum.News.ordinal() == type){
            obj = newsMapper.queryNewsByCollect(userId, (pageNO - 1) * pageSize, pageSize);
        }
        else if(CollectEnum.Exam.ordinal() == type){
            obj = examinationMapper.queryExaminationByCollect(userId, (pageNO - 1) * pageSize, pageSize);
        }else{
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"参数异常");
        }
        return obj;
    }


    /**
     * 获取收藏 列表
     * @param type 类型
     * @param userId 用户ID
     * @param pageNO 页码
     * @param pageSize 尺寸
     * @throws Exception
     */
    public Object queryCollectByTypeOfWeb(Integer type, Integer userId, Integer pageNO, Integer pageSize) throws Exception {
        int count = 0;
        if (CollectEnum.Curriculum.ordinal() == type) {
            List<Curriculum> curriculumList = curriculumMapper.queryCurriculumByCollect(userId, (pageNO - 1) * pageSize, pageSize);
            count = null == curriculumList ? 0 : curriculumList.size();
            List<Curriculum> list = curriculumMapper.queryCurriculumByCollect(userId, (pageNO - 1) * pageSize, pageSize);
            return new Page<Curriculum>(list,count,0);

        }
        else if(CollectEnum.News.ordinal() == type){
            List<News> newses = newsMapper.queryNewsByCollect(userId, (pageNO - 1) * pageSize, pageSize);
            count = null == newses ? 0 : newses.size();
            List<News> list = newsMapper.queryNewsByCollect(userId, (pageNO - 1) * pageSize, pageSize);
            return new Page<News>(list,count,0);
        }
        else if(CollectEnum.Exam.ordinal() == type){
            List<Examination> examinations = examinationMapper.queryExaminationByCollect(userId, (pageNO - 1) * pageSize, pageSize);
            count = null == examinations ? 0 : examinations.size();
            List<Examination> list = examinationMapper.queryExaminationByCollect(userId, (pageNO - 1) * pageSize, pageSize);
            return new Page<Examination>(list,count,0);
        }else{
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"参数异常");
        }
    }


    public void delete(Integer userId, Integer type, Integer targetId){
        collectMapper.delete(userId, type, targetId);
    }
}
