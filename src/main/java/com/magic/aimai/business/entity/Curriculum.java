package com.magic.aimai.business.entity;

import net.sf.json.JSONArray;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 课程 entity
 * Created by Eric Xie on 2017/7/13 0013.
 */
public class Curriculum implements Serializable {

    /** ID */
    private Integer id;

    /** 课程名 */
    private String curriculumName;

    /** 课程介绍 */
    private String curriculumDescribe;

    /** 年度 */
    private Date year;

    /** 封面 横 */
    private String cover;

    /** 封面 竖*/
    private String coverH;

    /** 行业ID */
    private Integer tradeId;

    /** 行业名称 */
    private String tradeName;

    /** 地区ID */
    private Integer cityId;

    /** 培训阶段ID */
    private Integer curriculumStageId;

    /** 培训阶段 */
    private String stageName;

    /** 单价 */
    private double price;

    /** 课程类型ID */
    private Integer curriculumTypeId;

    /** 课程类型 */
    private String typeName;

    /** 发布状态 0:未发布  1:已发布 缺省值 0 */
    private Integer releaseStatus;

    /** 发布时间 */
    private Date releaseTime;

    /** 创建时间 */
    private Date createTime;

    /** 发布人 */
    private Integer releaseUserId;

    /** 发布人名字 */
    private String releaseUserName;

    /** 是否有效 0：无效 1：有效  缺省值 1*/
    private Integer isValid;

    /** 课程类别  0：试听课程  1：收费课程 */
    private Integer type;

    /** 是否是推荐课程 0：否 1：是 缺省值 1 */
    private Integer isRecommend;

    /** 排序ID 降序排列 */
    private Integer sortNum;

    /** 课件集合 */
    private List<CourseWare> courseWares;

    /** 课程下的 视频数量 */
    private Integer videoNum;

    /** 总高清视频时长 秒 */
    private Integer hdSeconds;

    /** 总流畅视频时长 秒 */
    private Integer lowSeconds;

    /** 已学习的视频时长 */
    private Integer expendSeconds;

    /** 用户 是否拥有该课程 0：不拥有  1：拥有 */
    private Integer isOwn;

    /** 存放 级联 地区 不做存储操作 */
    private JSONArray cityJsonAry;

    /** 订单ID */
    private Integer orderId;

    /** 试卷id */
    private Integer paperId;

    /** 讲师名称 */
    private String teacherName;

    /** 讲师名称介绍 */
    private String teacherIntroduce;

    /** 适用人群 */
    private String applyPerson;

    /** 学习开始时间 */
    private Date studyStartTime;

    /** 学习完成时间 */
    private Date studyEndTime;

    /** 错题数量 */
    private Integer errorNum;

    /** 还有多少份 */
    private Integer number;

    /** 总份数 */
    private Integer countNum;

    /** 已经分配份数 */
    private Integer allocationNum;

    /** 已学习的课时 */
    private Integer studyNum;

    /** 是否通过考核  0:没有  1：通过 */
    private Integer isPass;

    /** 通过时间 */
    private Date passTime;

    /** 考试次数 */
    private Integer examNum;

    /** 考试时间 */
    private Date examTime;

    /** 观看人员集合 */
    private List<User> watchUsers;

    /** 安全人员 集合 */
    private List<User> safeUsers;

    /** 课程下 总的课时数量 */
    private Integer courseWareNum;

    /** 已观看人数 */
    private Integer watchNum;

    /** 已考核人数 */
    private Integer finishExamine;

    /** 公司总人数 */
    private Integer countUser;

    /** 试卷集合 */
    private List<Paper> paperList;

    /** 已做习题数量 */
    private Integer examinationNum;

    /** 习题总数量 */
    private Integer examinationTotalNum;

    /** 及格分数 */
    private Integer passScore;

    /** 考试得分 */
    private Integer resultScore;

    /** 习题用时 */
    private Integer useTime;

    /** 导出的单元格行数 */
    private Integer excelNum;

    /** 是否有考卷 */
    private Integer isHavePaper;

    /** 是否通过记录id */
    private Integer parId;

    /** 是否可做联系题 0：否  1：可以 */
    private Integer isExamination;

    /** 视频验证记录 */
    private List<FaceRecord> faceRecordList;

    /** 学习状态  1：可以学习   0：停止  1：暂停 */
    private Integer studyStatus;

    /** 课程获取类型 0：购买  1：分配 */
    private Integer buyType;


    /** 获取 ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 课程名 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 课程名 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 课程介绍 */
    public String getCurriculumDescribe() {
        return this.curriculumDescribe;
    }

    /** 设置 课程介绍 */
    public void setCurriculumDescribe(String curriculumDescribe) {
        this.curriculumDescribe = curriculumDescribe;
    }

    /** 获取 年度 */
    public Date getYear() {
        return this.year;
    }

    /** 设置 年度 */
    public void setYear(Date year) {
        this.year = year;
    }

    /** 获取 封面 */
    public String getCover() {
        return this.cover;
    }

    /** 设置 封面 */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /** 获取 行业ID */
    public Integer getTradeId() {
        return this.tradeId;
    }

    /** 设置 行业ID */
    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    /** 获取 行业名称 */
    public String getTradeName() {
        return this.tradeName;
    }

    /** 设置 行业名称 */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /** 获取 地区ID */
    public Integer getCityId() {
        return this.cityId;
    }

    /** 设置 地区ID */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /** 获取 培训阶段ID */
    public Integer getCurriculumStageId() {
        return this.curriculumStageId;
    }

    /** 设置 培训阶段ID */
    public void setCurriculumStageId(Integer curriculumStageId) {
        this.curriculumStageId = curriculumStageId;
    }

    /** 获取 培训阶段 */
    public String getStageName() {
        return this.stageName;
    }

    /** 设置 培训阶段 */
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    /** 获取 单价 */
    public double getPrice() {
        return this.price;
    }

    /** 设置 单价 */
    public void setPrice(double price) {
        this.price = price;
    }

    /** 获取 课程类型ID */
    public Integer getCurriculumTypeId() {
        return this.curriculumTypeId;
    }

    /** 设置 课程类型ID */
    public void setCurriculumTypeId(Integer curriculumTypeId) {
        this.curriculumTypeId = curriculumTypeId;
    }

    /** 获取 课程类型 */
    public String getTypeName() {
        return this.typeName;
    }

    /** 设置 课程类型 */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /** 获取 发布状态 0:未发布  1:已发布 缺省值 0 */
    public Integer getReleaseStatus() {
        return this.releaseStatus;
    }

    /** 设置 发布状态 0:未发布  1:已发布 缺省值 0 */
    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    /** 获取 发布时间 */
    public Date getReleaseTime() {
        return this.releaseTime;
    }

    /** 设置 发布时间 */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 发布人 */
    public Integer getReleaseUserId() {
        return this.releaseUserId;
    }

    /** 设置 发布人 */
    public void setReleaseUserId(Integer releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    /** 获取 发布人名字 */
    public String getReleaseUserName() {
        return this.releaseUserName;
    }

    /** 设置 发布人名字 */
    public void setReleaseUserName(String releaseUserName) {
        this.releaseUserName = releaseUserName;
    }

    /** 获取 是否有效 0：无效 1：有效  缺省值 1*/
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 是否有效 0：无效 1：有效  缺省值 1*/
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /** 获取 课程类别  0：试听课程  1：收费课程 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 课程类别  0：试听课程  1：收费课程 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 是否是推荐课程 0：否 1：是 缺省值 1 */
    public Integer getIsRecommend() {
        return this.isRecommend;
    }

    /** 设置 是否是推荐课程 0：否 1：是 缺省值 1 */
    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    /** 获取 排序ID 降序排列 */
    public Integer getSortNum() {
        return this.sortNum;
    }

    /** 设置 排序ID 降序排列 */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    /** 获取 课件集合 */
    public List<CourseWare> getCourseWares() {
        return this.courseWares;
    }

    /** 设置 课件集合 */
    public void setCourseWares(List<CourseWare> courseWares) {
        this.courseWares = courseWares;
    }

    /** 获取 课程下的 视频数量 */
    public Integer getVideoNum() {
        return this.videoNum;
    }

    /** 设置 课程下的 视频数量 */
    public void setVideoNum(Integer videoNum) {
        this.videoNum = videoNum;
    }

    /** 获取 总高清视频时长 秒 */
    public Integer getHdSeconds() {
        return this.hdSeconds;
    }

    /** 设置 总高清视频时长 秒 */
    public void setHdSeconds(Integer hdSeconds) {
        this.hdSeconds = hdSeconds;
    }

    /** 获取 总流畅视频时长 秒 */
    public Integer getLowSeconds() {
        return this.lowSeconds;
    }

    /** 设置 总流畅视频时长 秒 */
    public void setLowSeconds(Integer lowSeconds) {
        this.lowSeconds = lowSeconds;
    }

    /** 获取 用户 是否拥有该课程 0：不拥有  1：拥有 */
    public Integer getIsOwn() {
        return this.isOwn;
    }

    /** 设置 用户 是否拥有该课程 0：不拥有  1：拥有 */
    public void setIsOwn(Integer isOwn) {
        this.isOwn = isOwn;
    }


    /** 获取 存放 级联 地区 不做存储操作 */
    public JSONArray getCityJsonAry() {
        return this.cityJsonAry;
    }

    /** 设置 存放 级联 地区 不做存储操作 */
    public void setCityJsonAry(JSONArray cityJsonAry) {
        this.cityJsonAry = cityJsonAry;
    }

    /** 获取 订单ID */
    public Integer getOrderId() {
        return this.orderId;
    }

    /** 设置 订单ID */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 已学习的视频时长 */
    public Integer getExpendSeconds() {
        if (null == this.expendSeconds ) {
            return 0;
        }
        return this.expendSeconds;
    }

    /** 设置 已学习的视频时长 */
    public void setExpendSeconds(Integer expendSeconds) {
        this.expendSeconds = expendSeconds;
    }

    /** 获取 学习开始时间 */
    public Date getStudyStartTime() {
        return this.studyStartTime;
    }

    /** 设置 学习开始时间 */
    public void setStudyStartTime(Date studyStartTime) {
        this.studyStartTime = studyStartTime;
    }

    /** 获取 学习完成时间 */
    public Date getStudyEndTime() {
        return this.studyEndTime;
    }

    /** 设置 学习完成时间 */
    public void setStudyEndTime(Date studyEndTime) {
        this.studyEndTime = studyEndTime;
    }

    /** 获取 错题数量 */
    public Integer getErrorNum() {
        return this.errorNum;
    }

    /** 设置 错题数量 */
    public void setErrorNum(Integer errorNum) {
        this.errorNum = errorNum;
    }

    /** 获取 已学习的课时 */
    public Integer getStudyNum() {
        return this.studyNum;
    }

    /** 设置 已学习的课时 */
    public void setStudyNum(Integer studyNum) {
        this.studyNum = studyNum;
    }

    /** 获取 是否通过考核  0:没有  1：通过 */
    public Integer getIsPass() {
        return this.isPass;
    }

    /** 设置 是否通过考核  0:没有  1：通过 */
    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    /** 获取 还有多少份 */
    public Integer getNumber() {
        return this.number;
    }

    /** 设置 还有多少份 */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /** 获取 观看人员集合 */
    public List<User> getWatchUsers() {
        return this.watchUsers;
    }

    /** 设置 观看人员集合 */
    public void setWatchUsers(List<User> watchUsers) {
        this.watchUsers = watchUsers;
    }

    /** 获取 安全人员 集合 */
    public List<User> getSafeUsers() {
        return this.safeUsers;
    }

    /** 设置 安全人员 集合 */
    public void setSafeUsers(List<User> safeUsers) {
        this.safeUsers = safeUsers;
    }

    /** 获取 课程下 总的课时数量 */
    public Integer getCourseWareNum() {
        return this.courseWareNum;
    }

    /** 设置 课程下 总的课时数量 */
    public void setCourseWareNum(Integer courseWareNum) {
        this.courseWareNum = courseWareNum;
    }

    /** 获取 总份数 */
    public Integer getCountNum() {
        return this.countNum;
    }

    /** 设置 总份数 */
    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    /** 获取 已经分配份数 */
    public Integer getAllocationNum() {
        return this.allocationNum;
    }

    /** 设置 已经分配份数 */
    public void setAllocationNum(Integer allocationNum) {
        this.allocationNum = allocationNum;
    }

    /** 获取 已观看人数 */
    public Integer getWatchNum() {
        return this.watchNum;
    }

    /** 设置 已观看人数 */
    public void setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
    }

    /** 获取 已考核人数 */
    public Integer getFinishExamine() {
        return this.finishExamine;
    }

    /** 设置 已考核人数 */
    public void setFinishExamine(Integer finishExamine) {
        this.finishExamine = finishExamine;
    }

    /** 获取 公司总人数 */
    public Integer getCountUser() {
        return this.countUser;
    }

    /** 设置 公司总人数 */
    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }

    /** 获取 试卷集合 */
    public List<Paper> getPaperList() {
        return this.paperList;
    }

    /** 设置 试卷集合 */
    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }

    /** 获取 已做习题数量 */
    public Integer getExaminationNum() {
        return this.examinationNum;
    }

    /** 设置 已做习题数量 */
    public void setExaminationNum(Integer examinationNum) {
        this.examinationNum = examinationNum;
    }

    /** 获取 习题总数量 */
    public Integer getExaminationTotalNum() {
        return this.examinationTotalNum;
    }

    /** 设置 习题总数量 */
    public void setExaminationTotalNum(Integer examinationTotalNum) {
        this.examinationTotalNum = examinationTotalNum;
    }

    /** 获取 考试次数 */
    public Integer getExamNum() {
        return this.examNum;
    }

    /** 设置 考试次数 */
    public void setExamNum(Integer examNum) {
        this.examNum = examNum;
    }

    /** 获取 通过时间 */
    public Date getPassTime() {
        return this.passTime;
    }

    /** 设置 通过时间 */
    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    /** 获取 试卷id */
    public Integer getPaperId() {
        return this.paperId;
    }

    /** 设置 试卷id */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /** 获取 习题用时 */
    public Integer getUseTime() {
        return this.useTime;
    }

    /** 设置 习题用时 */
    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    /** 获取 讲师名称 */
    public String getTeacherName() {
        return this.teacherName;
    }

    /** 设置 讲师名称 */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /** 获取 讲师名称介绍 */
    public String getTeacherIntroduce() {
        return this.teacherIntroduce;
    }

    /** 设置 讲师名称介绍 */
    public void setTeacherIntroduce(String teacherIntroduce) {
        this.teacherIntroduce = teacherIntroduce;
    }

    /** 获取 适用人群 */
    public String getApplyPerson() {
        return this.applyPerson;
    }

    /** 设置 适用人群 */
    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    /** 获取 考试时间 */
    public Date getExamTime() {
        return this.examTime;
    }

    /** 设置 考试时间 */
    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    /** 获取 及格分数 */
    public Integer getPassScore() {
        return this.passScore;
    }

    /** 设置 及格分数 */
    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    /** 获取 考试得分 */
    public Integer getResultScore() {
        return this.resultScore;
    }

    /** 设置 考试得分 */
    public void setResultScore(Integer resultScore) {
        this.resultScore = resultScore;
    }

    /** 获取 导出的单元格行数 */
    public Integer getExcelNum() {
        if (null == this.excelNum || this.excelNum == 0) {
            return 1;
        }
        return this.excelNum;
    }

    /** 设置 导出的单元格行数 */
    public void setExcelNum(Integer excelNum) {
        this.excelNum = excelNum;
    }

    /** 获取 是否有考卷 */
    public Integer getIsHavePaper() {
        return this.isHavePaper;
    }

    /** 设置 是否有考卷 */
    public void setIsHavePaper(Integer isHavePaper) {
        this.isHavePaper = isHavePaper;
    }

    /** 获取 是否通过记录id */
    public Integer getParId() {
        return this.parId;
    }

    /** 设置 是否通过记录id */
    public void setParId(Integer parId) {
        this.parId = parId;
    }

    /** 获取 封面 竖*/
    public String getCoverH() {
        return this.coverH;
    }

    /** 设置 封面 竖*/
    public void setCoverH(String coverH) {
        this.coverH = coverH;
    }

    /** 获取 是否可做联系题 0：否  1：可以 */
    public Integer getIsExamination() {
        return this.isExamination;
    }

    /** 设置 是否可做联系题 0：否  1：可以 */
    public void setIsExamination(Integer isExamination) {
        this.isExamination = isExamination;
    }

    /** 获取 视频验证记录 */
    public List<FaceRecord> getFaceRecordList() {
        return this.faceRecordList;
    }

    /** 设置 视频验证记录 */
    public void setFaceRecordList(List<FaceRecord> faceRecordList) {
        this.faceRecordList = faceRecordList;
    }

    /** 获取 学习状态  1：可以学习   0：停止  1：暂停 */
    public Integer getStudyStatus() {
        return this.studyStatus;
    }

    /** 设置 学习状态  1：可以学习   0：停止  1：暂停 */
    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }

    /** 获取 课程获取类型 0：购买  1：分配 */
    public Integer getBuyType() {
        return this.buyType;
    }

    /** 设置 课程获取类型 0：购买  1：分配 */
    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }
}
