package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.mapper.ICourseWareMapper;
import com.magic.aimai.business.mapper.IVideoMapper;
import com.magic.aimai.business.mapper.IVideoWareHouseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 课时/课件
 * @author lzh
 * @create 2017/7/21 11:52
 */
@Service
public class CourseWareService {

    @Resource
    private ICourseWareMapper courseWareMapper;
    @Resource
    private IVideoMapper videoMapper;
    @Resource
    private IVideoWareHouseMapper videoWareHouseMapper;

    /**
     * 获取 学习模块 错题库列表
     * @param orderId
     * @param userId
     * @return
     */
    public List<ExaminationList> queryCourseWareError(Integer orderId,Integer userId,Integer pageNO,Integer pageSize){
        return courseWareMapper.queryCourseWareError(orderId,userId,(pageNO - 1) * pageSize, pageSize);
    }

    /**
     * 获取 学习模块 练习题列表
     * @param orderId
     * @param userId
     * @return
     */
    public List<ExaminationList> statisticsCourseWare(Integer orderId,Integer userId,Integer pageNO,Integer pageSize){
        return courseWareMapper.statisticsCourseWare(orderId,userId,(pageNO - 1) * pageSize, pageSize);
    }

    /**
     * 获取 学习模块 练习题列表
     * @param orderId
     * @param userId
     * @return
     */
    public Page<ExaminationList> statisticsCourseWareOfWeb(Integer orderId, Integer userId, Integer pageNO, Integer pageSize){
        int count = courseWareMapper.countStatisticsCourseWare(orderId, userId);
        List<ExaminationList> examinationLists = null;
        if (count > 0) {
            examinationLists = courseWareMapper.statisticsCourseWare(orderId, userId, (pageNO - 1) * pageSize, pageSize);
        }
        return new Page<ExaminationList>(examinationLists,count,0);
    }

    /**
     *  通过课程ID 查询课时集合
     * @param curriculumId
     * @return
     */
    public List<CourseWare> queryCourseWareByCurriculum(Integer curriculumId,Integer orderId,Integer userId){
        return courseWareMapper.queryCourseWareByCurriculumId(curriculumId,orderId,userId);
    }


    public List<CourseWare> queryBaseCourseWare(){
        return courseWareMapper.queryBaseCourseWare();
    }

    /**
     * 添加课时
     * @param courseWare
     */
    public void save(CourseWare courseWare) {
        courseWareMapper.addCourseWare(courseWare);
    }

    /**
     * 更新课时
     * @param courseWare
     */
    public void update(CourseWare courseWare) {
        courseWareMapper.update(courseWare);
    }

    /**
     * 更新课时 包括为空的字段
     * @param courseWare
     */
    public void updateAll(CourseWare courseWare) {
        courseWareMapper.updateAll(courseWare);
    }

    /**
     * 后台页面 分页获取课时分类
     * @param pageArgs 分页属性
     * @param courseWareName 课时名
     * @param isValid 课时名
     * @param createTimeStart 创建的开始时间
     * @param createTimeEnd 创建的结束时间
     * @param curriculumId 课程id
     * @return
     */
    public PageList<CourseWare> listForAdmin(PageArgs pageArgs , String courseWareName , Integer isValid , Date createTimeStart , Date createTimeEnd ,Integer curriculumId) {
        PageList<CourseWare> pageList = new PageList<CourseWare>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("courseWareName",courseWareName);
        map.put("isValid",isValid);
        map.put("createTimeStart",createTimeStart);
        map.put("createTimeEnd",createTimeEnd);
        map.put("curriculumId",curriculumId);
        int count = courseWareMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(courseWareMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }


    /**
     * 后台页面 分页获取用户课时分类
     * @param pageArgs 分页属性
     * @param orderId 订单id
     * @param curriculumId 课程id
     * @return
     */
    public PageList<CourseWare> listForAdminUser(PageArgs pageArgs , Integer orderId , Integer curriculumId,Integer userId) {
        PageList<CourseWare> pageList = new PageList<CourseWare>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("orderId",orderId);
        map.put("curriculumId",curriculumId);
        map.put("userId",userId);
        int count = courseWareMapper.listForAdminUserCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(courseWareMapper.listForAdminUser(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 后台页面 分页获取用户课时分类
     * @param orderId 订单id
     * @param curriculumId 课程id
     * @return
     */
    public Page<CourseWare> listForWebUser(Integer pageNO,Integer pageSize , Integer orderId , Integer curriculumId,Integer userId) {
        List<CourseWare> pageList = new ArrayList<CourseWare>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("orderId",orderId);
        map.put("curriculumId",curriculumId);
        map.put("userId",userId);
        int count = courseWareMapper.listForAdminUserCount(map);
        if (count > 0) {
            map.put("limit",(pageNO - 1) * pageSize);
            map.put("limitSize",pageSize);
            pageList = courseWareMapper.listForWebUser(map);
        }
        return new Page<CourseWare>(pageList,count,0);
    }

    /**
     * 课时详情
     * @param id 课时id
     * @return
     */
    public CourseWare info(Integer id) {
        return courseWareMapper.info(id);
    }

    /**
     * 删除课时 和视频
     * @param id 课时id
     * @return
     */
    @Transactional
    public void delete(Integer id) {
        List<Video> videos = videoMapper.querySimpleVideoByCourseWare(id);
        if(null != videos && videos.size() > 0){
            Set<Integer> vIds = new HashSet<Integer>();
            for (Video video : videos) {
                if(1 == video.getSourceLow() || 2 == video.getSourceLow()){
                    vIds.add(video.getVideoWareHouseLowId());
                }
                if(1 == video.getSourceHigh() || 2 == video.getSourceHigh()){
                    vIds.add(video.getVideoWareHouseLowId());
                }
            }
            if(vIds.size() > 0){
                videoWareHouseMapper.updateIsBind(vIds);
            }
        }
        videoMapper.deleteByCourseWareId(id);
        courseWareMapper.delete(id);
    }

}
