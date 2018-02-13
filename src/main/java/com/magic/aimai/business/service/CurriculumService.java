package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.enums.RoleEnum;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.*;
import com.magic.aimai.business.util.StatusConstant;
import io.swagger.models.auth.In;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 课程 业务层
 * Created by Eric Xie on 2017/7/13 0013.
 */
@Service
public class CurriculumService {

    @Resource
    private ICurriculumMapper curriculumMapper;
    @Resource
    private ICityMapper cityMapper;
    @Resource
    private ICurriculumTypeMapper curriculumTypeMapper;
    @Resource
    private ICurriculumNewRecordMapper curriculumNewRecordMapper;
    @Resource
    private IOrderMapper orderMapper;
    @Resource
    private ICourseWareMapper courseWareMapper;
    @Resource
    private IVideoMapper videoMapper;



    /**
     * 通过公司ID 获取 公司没有购买的课程列表 WEB端
     * @param companyId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public Page<Curriculum> getCurriculumNotBuy(Integer companyId,Integer pageNO,Integer pageSize){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("companyId",companyId);
        map.put("limit",(pageNO - 1) * pageSize);
        map.put("limitSize",pageSize);
        int i = curriculumMapper.countCurriculumNotBuy(map);
        List<Curriculum> curricula = new ArrayList<Curriculum>();
        if (i > 0) {
            curricula = curriculumMapper.queryCurriculumNotBuy(map);
        }
        return new Page<Curriculum>(curricula,i,0);
    }



    /**
     * 通过公司ID 获取 公司下的课程列表
     * @param companyId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Curriculum> queryCurriculumByCompany(Integer companyId,Integer pageNO,Integer pageSize){
        List<Curriculum> curricula = curriculumMapper.queryCurriculumByCompany(companyId, (pageNO - 1) * pageSize, pageSize);
        return curricula;
    }

    /**
     * 通过用户 查询 该用户待分配的课程列表
     * @param companyId
     * @param userId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Curriculum> queryWaitAllocationCurriculumByUser(Integer companyId,Integer userId,Integer pageNO,Integer pageSize){
        List<Curriculum> curriculumList = curriculumMapper.queryWaitAllocationCurriculumByUser(companyId, userId,
                (pageNO - 1) * pageSize, pageSize);
        if(null != curriculumList && curriculumList.size() > 0){
            Iterator<Curriculum> iterator = curriculumList.iterator();
            while (iterator.hasNext()){
                if(iterator.next().getNumber() <= 0){
                    iterator.remove();
                }
            }
        }
        return curriculumList;
    }

    /**
     * 获取错题库 课程列表
     * @param userId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Curriculum> queryCurriculumError(Integer userId,Integer pageNO,Integer pageSize){
        return curriculumMapper.queryCurriculumError(userId,(pageNO - 1) * pageSize,pageSize);
    }

    /**
     * 获取错题库 课程列表
     * @param userId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public Page<Curriculum> queryCurriculumErrorOfWeb(Integer userId,Integer pageNO,Integer pageSize){
        int count = curriculumMapper.countCurriculumError(userId);
        List<Curriculum> curriculumList = null;
        if (count > 0) {
            curriculumList = curriculumMapper.queryCurriculumError(userId, (pageNO - 1) * pageSize, pageSize);
        }
        return new Page<Curriculum>(curriculumList,count,0);
    }

    /**
     * 学习模块  获取 模拟题列表接口
     * @param userId
     * @return
     */
    public List<CurriculumStudy> queryCurriculumStudyForExamination(Integer userId,Integer pageNO,Integer pageSize){
        return curriculumMapper.queryCurriculumStudyForExamination(userId,(pageNO - 1) * pageSize,pageSize);
    }

    /**
     * 学习模块  获取 模拟题列表接口
     * @param userId
     * @return
     */
    public Page<CurriculumStudy> queryCurriculumStudyForExaminationOfWeb(Integer userId,Integer pageNO,Integer pageSize){
        Integer count = curriculumMapper.countCurriculumStudyForExamination(userId);
        List<CurriculumStudy> curriculumStudies = null;
        if (null != count && count > 0) {
            curriculumStudies = curriculumMapper.queryCurriculumStudyForExamination(userId, (pageNO - 1) * pageSize, pageSize);
        }
        return new Page<CurriculumStudy>(curriculumStudies,null == count ? 0 : count,0);
    }


    /**
     * 学习模块  获取 考试题列表接口
     * @param userId
     * @return
     */
    public List<CurriculumStudy> queryCurriculumPass(Integer userId,Integer pageNO,Integer pageSize){
        return curriculumMapper.queryCurriculumPass(userId,(pageNO - 1) * pageSize,pageSize);
    }


    /**
     * 学习模块  获取 考试题列表接口
     * @param userId
     * @return
     */
    public Page<CurriculumStudy> queryCurriculumPassOfWeb(Integer userId,Integer pageNO,Integer pageSize){
        int count = curriculumMapper.countCurriculumPass(userId);
        List<CurriculumStudy> curriculumStudies = null;
        if (count > 0) {
            curriculumStudies = curriculumMapper.queryCurriculumPass(userId, (pageNO - 1) * pageSize, pageSize);
        }
        return new Page<CurriculumStudy>(curriculumStudies,count,0);
    }


    /**
     * 统计用户 最新一次 考题记录得分情况
     * @param userId
     * @return
     */
    public StatisticsExamination statisticsCurriculum(Integer userId){
        StatisticsExamination statisticsExamination = curriculumMapper.statisticsCurriculum(userId);
        statisticsExamination = null == statisticsExamination ? new StatisticsExamination() : statisticsExamination;
        return statisticsExamination;
    }

    /**
     * 查询 课程列表 仅名称 ID 字段
     * @return
     */
    public List<Curriculum> queryCurriculumForSeclect(){
        return curriculumMapper.queryCurriculumForSeclect();
    }

    /**
     * 通过订单ID 查询 课程详情
     * @param userId
     * @param orderId
     * @return
     */
    @Transactional
    public Curriculum queryCurriculumByOrderId(Integer userId,Integer orderId,Integer roleId)throws Exception{
        CurriculumNewRecord curriculumNewRecord = curriculumNewRecordMapper.queryCurriculumNewRecord(userId);
        if(null == curriculumNewRecord){
            CurriculumNewRecord record = new CurriculumNewRecord();
            record.setUserId(userId);
            record.setOrderId(orderId);
            curriculumNewRecordMapper.addCurriculumNewRecord(record);
        }else{
            curriculumNewRecordMapper.updateCurriculumNewRecord(curriculumNewRecord.getId(),orderId);
        }
        Curriculum curriculum = curriculumMapper.queryCurriculumByOrderId(orderId, userId,roleId);
        //计算 课程下的 视频数量/总高清视频时长/总流畅视频时长
        buildVideoNumHdSecondsLowSeconds(curriculum);
        return curriculum;
    }

    /**
     * 企业通过课程ID 查询 课程详情
     * @param userId
     * @param curriculumId
     * @return
     */
    @Transactional
    public Curriculum queryCurriculumOfCompany(Integer userId,Integer curriculumId)throws Exception{
        CurriculumNewRecord curriculumNewRecord = curriculumNewRecordMapper.queryCurriculumNewRecord(userId);
        if(null == curriculumNewRecord){
            CurriculumNewRecord record = new CurriculumNewRecord();
            record.setUserId(userId);
            record.setOrderId(curriculumId);
            curriculumNewRecordMapper.addCurriculumNewRecord(record);
        }else{
            curriculumNewRecordMapper.updateCurriculumNewRecord(curriculumNewRecord.getId(),curriculumId);
        }
        Curriculum curriculum = curriculumMapper.queryCurriculumByCurriculumId(userId,curriculumId);
        //计算 课程下的 视频数量/总高清视频时长/总流畅视频时长
        buildVideoNumHdSecondsLowSeconds(curriculum);
        return curriculum;
    }

    /**
     * 学习中心  课程模块 课程列表
     * @param userId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<CurriculumStudy> queryCurriculumStudyByItems(Integer roleId,Integer userId,Integer pageNO,Integer pageSize){
        List<CurriculumStudy> curriculumStudies = null;
        if(RoleEnum.USER.ordinal() == roleId){
            curriculumStudies = curriculumMapper.queryCurriculumStudyByItems(userId,
                    null == pageNO || null == pageSize ? null : (pageNO - 1) * pageSize, null == pageSize ? null : pageSize);

        }
        else if(RoleEnum.COMPANY_USER.ordinal() == roleId){
            curriculumStudies = curriculumMapper.queryCurriculumStudyByItemsOfCompany(userId,
                    null == pageNO || null == pageSize ? null : (pageNO - 1) * pageSize, null == pageSize ? null : pageSize);
        }
        return curriculumStudies;
    }

    /**
     * 学习中心  课程模块 课程列表
     * @param userId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public Page<CurriculumStudy> queryCurriculumStudyByItemsOfWeb(Integer roleId,Integer userId,Integer pageNO,Integer pageSize){
        List<CurriculumStudy> curriculumStudies = null;
        int count = 0;
        if(RoleEnum.USER.ordinal() == roleId){
            List<CurriculumStudy> counts = curriculumMapper.queryCurriculumStudyByItems(userId,
                    null, null);
            if(null != counts && counts.size() > 0){
                curriculumStudies = curriculumMapper.queryCurriculumStudyByItems(userId,
                        null == pageNO || null == pageSize ? null : (pageNO - 1) * pageSize, null == pageSize ? null : pageSize);
            }
            count = null == counts ? 0 : counts.size();
        }
        else if(RoleEnum.COMPANY_USER.ordinal() == roleId || RoleEnum.BUSINESS_USER.ordinal() == roleId){
            List<CurriculumStudy> counts  = curriculumMapper.queryCurriculumStudyByItemsOfCompany(userId,null,null);
            if(null != counts && counts.size() > 0){
                curriculumStudies = curriculumMapper.queryCurriculumStudyByItemsOfCompany(userId,
                        null == pageNO || null == pageSize ? null : (pageNO - 1) * pageSize, null == pageSize ? null : pageSize);
            }
            count = null == counts ? 0 : counts.size();
        }
        return new Page<CurriculumStudy>(curriculumStudies,count,0);
    }






    /**
     * 通过课程ID 获取课程详情
     * @param curriculumId 课程ID
     * @return
     * @throws Exception
     */
    public Curriculum queryCurriculumById(Integer curriculumId,Integer userId) throws Exception{
        Curriculum curriculum = curriculumMapper.queryCurriculumById(curriculumId,userId);
        if(null == curriculum){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"课程不存在");
        }
        //计算 课程下的 视频数量/总高清视频时长/总流畅视频时长
        buildVideoNumHdSecondsLowSeconds(curriculum);
        return curriculum;
    }


    /**
     * 查询课程的基础字段
     * @param curriculumId
     * @return
     */
    public Curriculum queryBaseInfo(Integer curriculumId){
        return curriculumMapper.queryBaseInfo(curriculumId);
    }


    /**
     * 课程页面 查询 试听课程 以及 课程集合
     * @param user 如果登录 不为null
     * @param pageNO 页码
     * @param pageSize 页码
     * @return
     */
    public Map<String,Object> queryCurriculumByItems(User user,Integer pageNO,Integer pageSize){
        Map<String,Object> data = new HashMap<String, Object>();
        Integer provinceId = null;
        Integer townId = null;
        Integer countyId = null;
        Integer tradeId = null;
        Integer userId = null;
        if(null != user){
            // 登录用户 查询
            // 查询当前用户的 省级ID  市级ID
            City city = cityMapper.queryCityId(user.getCityId());
            provinceId = city.getProvinceId();townId = city.getTownId();countyId = city.getId();
            tradeId = user.getTradeId();userId = user.getId();
        }
        // 三级 三项 类型分组课程
        List<CurriculumType> curriculumTypes = curriculumTypeMapper.queryCurriculumTypeIncludeCurriculum(provinceId, townId, countyId, tradeId,
                userId, (pageNO - 1) * pageSize, pageSize);

        // 查询试听课程
        List<Curriculum> curricula = curriculumMapper.queryCurriculumByAudition(provinceId, townId, countyId, tradeId, userId);
        if (curricula.size() > 0) {
            curricula.removeAll(Collections.singleton(null));
        }

        // 推荐视频
        List<Curriculum> recommendCurriculumList = curriculumMapper.queryCurriculumByRecommend(provinceId, townId, countyId, tradeId, userId,
                (pageNO - 1) * pageSize,pageSize);

        data.put("curriculumTypes",curriculumTypes);
        data.put("curriculumByAudition",curricula);
        data.put("recommendCurriculumList",recommendCurriculumList);
        return data;
    }


    /**
     * API 首页搜索课程列表
     * @param user
     * @param searchParam
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Curriculum> queryCurriculumBySearch(User user,String searchParam,Integer pageNO,Integer pageSize){
        Integer provinceId = null;
        Integer townId = null;
        Integer countyId = null;
        Integer tradeId = null;
        Integer userId = null;
        if(null != user){
            // 登录用户 查询
            // 查询当前用户的 省级ID  市级ID
            City city = cityMapper.queryCityId(user.getCityId());
            provinceId = city.getProvinceId();townId = city.getTownId();countyId = city.getId();
            tradeId = user.getTradeId();userId = user.getId();
        }
        return curriculumMapper.queryCurriculumBySearch(searchParam,provinceId,townId,countyId,tradeId,
                userId,(pageNO - 1) * pageSize,pageSize);
    }


    /**
     * 通过 类型 或者 是否推荐 查询课程列表
     * @param user
     * @param isRecommend
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Curriculum> queryCurriculumByType(User user, Integer isRecommend, Integer pageNO, Integer pageSize, Integer curriculumStageId,
                                                  Integer curriculumTypeId){
        // 查询当前用户的 省级ID  市级ID
        Integer provinceId = null;
        Integer townId = null;
        Integer countyId = null;
        Integer tradeId = null;
        if(null != user){
            City city = cityMapper.queryCityId(user.getCityId());
            provinceId = city.getProvinceId();
            townId = city.getTownId();
            countyId = city.getId();
            tradeId = user.getTradeId();
        }
        // 课程列表
        return  curriculumMapper.queryCurriculumByType(provinceId,townId,countyId,tradeId,isRecommend,
                (pageNO - 1 ) * pageSize,pageSize,curriculumStageId,curriculumTypeId);
    }



    /**
     * 通过 类型 或者 是否推荐 查询课程列表
     * @param user
     * @param isRecommend
     * @param pageNO
     * @param pageSize
     * @return
     */
    public Page<Curriculum> queryCurriculumByTypeOfWeb(User user, Integer isRecommend, Integer pageNO, Integer pageSize, Integer curriculumStageId,
                                                  Integer curriculumTypeId,Integer tradeId ,String searchParam){
        // 查询当前用户的 省级ID  市级ID
        Integer provinceId = null;
        Integer townId = null;
        Integer countyId = null;
        if(null != user){
            City city = cityMapper.queryCityId(user.getCityId());
            provinceId = city.getProvinceId();
            townId = city.getTownId();
            countyId = city.getId();
            tradeId = null == tradeId ? user.getTradeId() : tradeId;
        }
        int count = curriculumMapper.countCurriculumByType(provinceId, townId, countyId, tradeId, isRecommend,
                curriculumStageId ,searchParam, curriculumTypeId);
        List<Curriculum> curriculumList = null;
        if (count > 0) {
            // 课程列表
            curriculumList = curriculumMapper.queryCurriculumByTypeOfWeb(provinceId, townId, countyId, tradeId, isRecommend,
                    (pageNO - 1) * pageSize, pageSize, curriculumStageId,searchParam, curriculumTypeId);
        }
        return new Page<Curriculum>(curriculumList,count,0);
    }


    /**
     * 通过行业类别查询课程列表
     * @param user
     * @param tradeId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Curriculum> queryCurriculumByTradeId(User user,Integer tradeId,Integer pageNO,Integer pageSize){
        // 查询当前用户的 省级ID  市级ID
        City city = cityMapper.queryCityId(user.getCityId());
        Integer provinceId = city.getProvinceId();
        Integer townId = city.getTownId();
        Integer countyId = city.getId();
        // 课程列表
        return  curriculumMapper.queryCurriculumByTradeId(provinceId, townId, countyId,
                tradeId, (pageNO - 1) * pageSize, pageSize);
    }


    /**
     * 获取用户已购买课程 并有试卷且为考试
     * @param userId
     * @return
     */
    public List<Curriculum> getUserCurriculum(Integer userId){
        // 课程列表
        return  curriculumMapper.getUserCurriculum(userId);
    }


    /**
     * 新增课程
     * @param curriculum
     */
    public void save(Curriculum curriculum) {
        curriculumMapper.addCurriculum(curriculum);
    }


    /**
     * 更新课程
     * @param curriculum
     */
    public void update(Curriculum curriculum) {
        curriculumMapper.updateCurriculum(curriculum);
    }

    /**
     * 更新课程 修改传回来的所有字段
     * @param curriculum
     */
    public void updateForAdmin(Curriculum curriculum) {
        curriculumMapper.updateForAdmin(curriculum);
    }


    /**
     * 后台页面 分页获取课程
     * @param pageArgs 分页工具
     * @param curriculumName 课程名
     * @param provinceId 省id
     * @param cityId 市id
     * @param districtId 区县id
     * @param curriculumStageId 培训阶段ID
     * @param curriculumTypeId 课程类型ID
     * @param isValid 是否有效 0：无效 1：有效
     * @param year 年度
     * @param type 课程类别  0：试听课程  1：收费课程
     * @param releaseStatus 发布状态 0:未发布  1:已发布
     * @param isRecommend 是否是推荐课程 0：否 1：是
     * @param releaseTimeStart 发布开始时间
     * @param releaseTimeEnd 发布结束时间
     * @param tradeId 行业id
     * @return
     */
    public PageList<Curriculum> listForAdmin(PageArgs pageArgs ,String curriculumName , Integer curriculumStageId,
                                             Integer provinceId ,Integer cityId ,Integer districtId ,
                                             Integer curriculumTypeId , Integer isValid , Date year,
                                             Integer type, Integer releaseStatus ,Integer isRecommend ,
                                             Date releaseTimeStart, Date releaseTimeEnd ,Integer tradeId ,String releaseUserName) {
        PageList<Curriculum> pageList = new PageList<Curriculum>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("curriculumStageId",curriculumStageId);
        map.put("releaseUserName",releaseUserName);
        map.put("curriculumTypeId",curriculumTypeId);
        map.put("provinceId",provinceId);
        map.put("cityId",cityId);
        map.put("districtId",districtId);
        map.put("isValid",isValid);
        map.put("year",year);
        map.put("type",type);
        map.put("releaseStatus",releaseStatus);
        map.put("isRecommend",isRecommend);
        map.put("releaseTimeStart",releaseTimeStart);
        map.put("releaseTimeEnd",releaseTimeEnd);
        map.put("releaseStatus",releaseStatus);
        map.put("curriculumName",curriculumName);
        map.put("tradeId",tradeId);
        int count = curriculumMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            List<Curriculum> list = curriculumMapper.listForAdmin(map);
            for (Curriculum curriculum : list) {
                buildVideoNumHdSecondsLowSeconds(curriculum);
            }
            pageList.setList(list);
        }
        pageList.setTotalSize(count);
        return pageList;
    }



    /**
     * 通过课程ID 获取课程详情
     * @param curriculumId 课程ID
     * @return
     * @throws Exception
     */
    public Curriculum info(Integer curriculumId) throws Exception{
        Curriculum curriculum = curriculumMapper.queryCurriculumById(curriculumId,null);
        if(null == curriculum){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"课程不存在");
        }
        //课程下的 视频数量/总高清视频时长/总流畅视频时长
        buildVideoNumHdSecondsLowSeconds(curriculum);

        //封装省市区
        if (null != curriculum.getCityId()) {
            City city = cityMapper.getThreeId(curriculum.getCityId());
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(city.getProvinceId());
            jsonArray.add(city.getTownId());
            jsonArray.add(city.getId());
            curriculum.setCityJsonAry(jsonArray);
        }
        return curriculum;
    }



    /**
     * 计算 课程下的 视频数量/总高清视频时长/总流畅视频时长
     * @param curriculum
     */
    public void buildVideoNumHdSecondsLowSeconds(Curriculum curriculum) {
        int videoNum = 0;
        int hdSeconds = 0;
        int lowSeconds = 0;
        if(null != curriculum.getCourseWares()){
            for (CourseWare courseWare : curriculum.getCourseWares()) {
                if(null != courseWare.getVideos()){
                    for (Video video : courseWare.getVideos()) {
                        hdSeconds += video.getHighDefinitionSeconds();
                        lowSeconds += video.getLowDefinitionSeconds();
                    }
                    videoNum += courseWare.getVideos().size();
                }
            }
        }
        curriculum.setVideoNum(videoNum);
        curriculum.setHdSeconds(hdSeconds);
        curriculum.setLowSeconds(lowSeconds);
    }



    /**
     * 后台页面 分页获取用户课程 (档案管理)
     * @param pageArgs 分页工具
     * @param userId 用户id
     * @return
     */
    public PageList<Curriculum> listForAdminUser(PageArgs pageArgs ,Integer userId ) {
        PageList<Curriculum> pageList = new PageList<Curriculum>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        int count = curriculumMapper.listForAdminUserCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(curriculumMapper.listForAdminUser(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }




    /**
     * web页面 分页获取用户课程 (档案管理)
     * @param userId 用户id
     * @return
     */
    public Page<Curriculum> listForWebUser(Integer pageNO,Integer pageSize ,Integer userId ) {
        List<Curriculum> pageList = new ArrayList<Curriculum>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        int count = curriculumMapper.listForAdminUserCount(map);
        if (count > 0) {
            PageArgs pageArgs = new PageArgs();
            pageArgs.setPage(pageNO-1);
            pageArgs.setPageSize(pageSize);
            map.put("pageArgs",pageArgs);
            pageList = curriculumMapper.listForAdminUser(map);
        }
        return new Page<Curriculum>(pageList,count,0);
    }



    /**
     * 后台页面 分页获取用户课程 (档案管理)
     * @param pageArgs 分页工具
     * @param userId 用户id
     * @return
     */
    public List<Curriculum> listForPC(PageArgs pageArgs ,Integer userId,Integer type ) {
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("type",type);
        map.put("pageArgs",pageArgs);
        List<Curriculum> list = curriculumMapper.listForPC(map);
        List<Curriculum> list1 = new ArrayList<Curriculum>();
        Integer orderId = 0;
        Integer i = 0;
        for (Curriculum curriculum : list) {
            if (!curriculum.getOrderId().equals(orderId)) {
                orderId = curriculum.getOrderId();
                list1.add(curriculum);
                i ++;
            } else {
                if (curriculum.getCourseWares().size() > 0) {
                    list1.get(i - 1).getCourseWares().add(curriculum.getCourseWares().get(0));
                }
            }
        }

        return list1;
    }


    /**
     * 判断是否购买
     * @param userId
     * @param curriculumId
     * @return
     */
    public Integer isBuy(Integer userId , Integer curriculumId){
        return curriculumMapper.isBuy(userId,curriculumId);
    }

    /**
     * 删除课程
     * @param id
     */
    @Transactional
    public void delete(Integer id){
        //删除视频
        videoMapper.deleteByCurriculumId(id);
        //删除课时
        courseWareMapper.deleteByCurriculumId(id);
        //删除课程
        curriculumMapper.delete(id);
    }
}
