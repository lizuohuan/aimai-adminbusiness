package com.magic.aimai.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sf.json.JSONArray;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户 entity
 * Created by Eric Xie on 2017/7/12 0012.
 */
@ApiModel
public class User implements Serializable {

    private static final long serialVersionUID = 5054004104432889647L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /** 角色ID */
    private Integer roleId;

    /** 角色名 */
    private String roleName;

    /** 电话号码 */
    private String phone;

    /** 密码 */
    private String pwd;

    /** 昵称 */
    private String nickName;

    /** 展示名称 个人：姓名 政府：机构名称 企业：企业名称 */
    private String showName;

    /** 个人：身份证 政府：机构代码 企业：营业执照编码 */
    private String pid;

    /** 所在地 */
    private Integer cityId;

    /** 城市对象 */
    private City city;

    /** 头像 */
    private String avatar;

    /** 个人用户  人脸识别采集图像地址 */
    private String veriFaceImages;

    /** 行业分类ID  企业用户 */
    private Integer tradeId;

    /** 行业名称 */
    private String tradeName;

    /** 企业：营业执照 政府：介绍信 */
    private String licenseFile;

    /** 介绍 */
    private String introduce;

    /** 用户 - 积分 */
    private Integer accumulate;

    /** 状态 企业、政府用户注册 后台审核状态
     * 0：未通过 1：审核通过  2：审核中 */
    private Integer status;

    /** 父级ID-用户字段 所属的企业用户ID */
    private Integer parentId;

    /** 账户是否有效 0：无效 1：有效  缺省值：1 */
    private Integer isValid;

    /** 请求令牌 */
    private String token;

    /** 设备类型 0：android 1:ios */
    private Integer deviceType;

    /** 设备注册ID */
    private String deviceToken;

    /** 创建时间 */
    private Date createTime;

    /** 最后登录时间 */
    private Date lastLoginTime;

    /** 更新时间 */
    private Date updateTime;

    /** 清晰度 0：流畅  1：高清 缺省值 0 */
    private Integer definition;

    /** 微信登录时候 的openId */
    private String openId;

    /** 用户脸部ID */
    private String faceId;

    /** 人脸识别后返回的信息 */
    private String faceInfo;

    /** 部门名称 */
    private String departmentName;

    /** 职位 */
    private String jobTitle;

    /** 公司名称 */
    private String companyName;

    /** 人脸识别 注册ID */
    private String peopleId;

    /** 备注 */
    private String notes;

    /** 存放 级联 地区 不做存储操作 */
    private JSONArray cityJsonAry;

    /** 参见培训人数 */
    private Integer peopleNum;

    /** 用户下的所有课程详情 */
    private List<Curriculum> curriculumList;

    /** 最新学习的课程名 */
    private String curriculumName;

    /** 培训阶段 */
    private String stageName;

    /** 课程类型 */
    private String curriculumTypeName;

    /** 已经学习完的课时数量 */
    private Integer finishCourseWareNum;

    /** 正在培训的总课时 */
    private Integer totalCourseWareNum;

    /** 是否考核通过 */
    private Integer isPass;

    /** 安全人数 */
    private Integer safeNum;
    /** 参培人数 */
    private Integer joinNum;

    /** 某一课程的id */
    private Integer curriculumId;

    /** 用户所在地 全称 */
    private String cityMergerName;

    /** 邮箱 */
    private String email;


    /** 通过时间 */
    private Date passTime;

    /** 导出的单元格行数 */
    private Integer excelNum;

    /** 经销商用户下的 区域列表 */
    private List<City> userCityList;

    /** 此次登录是否异常  0：否  1 ：是 */
    private Integer isException = 0;

    public Integer getIsException() {
        return isException;
    }

    public User setIsException(Integer isException) {
        this.isException = isException;
        return this;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }


    /** 获取 主键ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 主键ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 角色ID */
    public Integer getRoleId() {
        return this.roleId;
    }

    /** 设置 角色ID */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 获取 电话号码 */
    public String getPhone() {
        return this.phone;
    }

    /** 设置 电话号码 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 获取 密码 */
    public String getPwd() {
        return this.pwd;
    }

    /** 设置 密码 */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /** 获取 昵称 */
    public String getNickName() {
        return this.nickName;
    }

    /** 设置 昵称 */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /** 获取 展示名称 个人：姓名 政府：机构名称 企业：企业名称 */
    public String getShowName() {
        return this.showName;
    }

    /** 设置 展示名称 个人：姓名 政府：机构名称 企业：企业名称 */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /** 获取 个人：身份证 政府：机构代码 企业：营业执照编码 */
    public String getPid() {
        return this.pid;
    }

    /** 设置 个人：身份证 政府：机构代码 企业：营业执照编码 */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /** 获取 所在地 */
    public Integer getCityId() {
        return this.cityId;
    }

    /** 设置 所在地 */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /** 获取 城市对象 */
    public City getCity() {
        return this.city;
    }

    /** 设置 城市对象 */
    public void setCity(City city) {
        this.city = city;
    }

    /** 获取 头像 */
    public String getAvatar() {
        return this.avatar;
    }

    /** 设置 头像 */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /** 获取 个人用户  人脸识别采集图像地址 */
    public String getVeriFaceImages() {
        return this.veriFaceImages;
    }

    /** 设置 个人用户  人脸识别采集图像地址 */
    public void setVeriFaceImages(String veriFaceImages) {
        this.veriFaceImages = veriFaceImages;
    }

    /** 获取 行业分类ID  企业用户 */
    public Integer getTradeId() {
        return this.tradeId;
    }

    /** 设置 行业分类ID  企业用户 */
    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    /** 获取 企业：营业执照 政府：介绍信 */
    public String getLicenseFile() {
        return this.licenseFile;
    }

    /** 设置 企业：营业执照 政府：介绍信 */
    public void setLicenseFile(String licenseFile) {
        this.licenseFile = licenseFile;
    }

    /** 获取 介绍 */
    public String getIntroduce() {
        return this.introduce;
    }

    /** 设置 介绍 */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /** 获取 用户 - 积分 */
    public Integer getAccumulate() {
        if (null == this.accumulate) {
            return 0;
        }
        return this.accumulate;
    }

    /** 设置 用户 - 积分 */
    public void setAccumulate(Integer accumulate) {
        this.accumulate = accumulate;
    }

    /** 状态 企业、政府用户注册 后台审核状态
     * 0：未通过 1：审核通过  2：审核中 */
    public Integer getStatus() {
        return this.status;
    }

    /** 状态 企业、政府用户注册 后台审核状态
     * 0：未通过 1：审核通过  2：审核中 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 父级ID-用户字段 所属的企业用户ID */
    public Integer getParentId() {
        return this.parentId;
    }

    /** 设置 父级ID-用户字段 所属的企业用户ID */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /** 获取 账户是否有效 0：无效 1：有效  缺省值：1 */
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 账户是否有效 0：无效 1：有效  缺省值：1 */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /** 获取 请求令牌 */
    public String getToken() {
        return this.token;
    }

    /** 设置 请求令牌 */
    public void setToken(String token) {
        this.token = token;
    }

    /** 获取 设备类型 0：android 1:ios */
    public Integer getDeviceType() {
        return this.deviceType;
    }

    /** 设置 设备类型 0：android 1:ios */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /** 获取 设备注册ID */
    public String getDeviceToken() {
        return this.deviceToken;
    }

    /** 设置 设备注册ID */
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 最后登录时间 */
    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    /** 设置 最后登录时间 */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /** 获取 更新时间 */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /** 设置 更新时间 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /** 获取 清晰度 0：流畅  1：高清 缺省值 0 */
    public Integer getDefinition() {
        return this.definition;
    }

    /** 设置 清晰度 0：流畅  1：高清 缺省值 0 */
    public void setDefinition(Integer definition) {
        this.definition = definition;
    }

    /** 获取 微信登录时候 的openId */
    public String getOpenId() {
        return this.openId;
    }

    /** 设置 微信登录时候 的openId */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /** 获取 用户脸部ID */
    public String getFaceId() {
        return this.faceId;
    }

    /** 设置 用户脸部ID */
    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    /** 获取 人脸识别后返回的信息 */
    public String getFaceInfo() {
        return this.faceInfo;
    }

    /** 设置 人脸识别后返回的信息 */
    public void setFaceInfo(String faceInfo) {
        this.faceInfo = faceInfo;
    }

    /** 获取 部门名称 */
    public String getDepartmentName() {
        return this.departmentName;
    }

    /** 设置 部门名称 */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /** 获取 职位 */
    public String getJobTitle() {
        return this.jobTitle;
    }

    /** 设置 职位 */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /** 获取 公司名称 */
    public String getCompanyName() {
        return this.companyName;
    }

    /** 设置 公司名称 */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** 获取 行业名称 */
    public String getTradeName() {
        return this.tradeName;
    }

    /** 设置 行业名称 */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /** 获取 人脸识别 注册ID */
    public String getPeopleId() {
        return this.peopleId;
    }

    /** 设置 人脸识别 注册ID */
    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    /** 获取 角色名 */
    public String getRoleName() {
        return this.roleName;
    }

    /** 设置 角色名 */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /** 获取 审核备注 */
    public String getNotes() {
        return this.notes;
    }

    /** 设置 审核备注 */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /** 获取 存放 级联 地区 不做存储操作 */
    public JSONArray getCityJsonAry() {
        return this.cityJsonAry;
    }

    /** 设置 存放 级联 地区 不做存储操作 */
    public void setCityJsonAry(JSONArray cityJsonAry) {
        this.cityJsonAry = cityJsonAry;
    }

    /** 获取 参见培训人数 */
    public Integer getPeopleNum() {
        return this.peopleNum;
    }

    /** 设置 参见培训人数 */
    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    /** 获取 用户下的所有课程详情 */
    public List<Curriculum> getCurriculumList() {
        return this.curriculumList;
    }

    /** 设置 用户下的所有课程详情 */
    public void setCurriculumList(List<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }

    /** 获取 最新学习的课程名 */
    public String getCurriculumName() {
        return this.curriculumName;
    }

    /** 设置 最新学习的课程名 */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /** 获取 已经学习完的课时数量 */
    public Integer getFinishCourseWareNum() {
        return this.finishCourseWareNum;
    }

    /** 设置 已经学习完的课时数量 */
    public void setFinishCourseWareNum(Integer finishCourseWareNum) {
        this.finishCourseWareNum = finishCourseWareNum;
    }

    /** 获取 是否考核通过 */
    public Integer getIsPass() {
        return this.isPass;
    }

    /** 设置 是否考核通过 */
    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    /** 获取 安全人数 */
    public Integer getSafeNum() {
        return this.safeNum;
    }

    /** 设置 安全人数 */
    public void setSafeNum(Integer safeNum) {
        this.safeNum = safeNum;
    }

    /** 获取 通过时间 */
    public Date getPassTime() {
        return this.passTime;
    }

    /** 设置 通过时间 */
    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    /** 获取 某一课程的id */
    public Integer getCurriculumId() {
        return this.curriculumId;
    }

    /** 设置 某一课程的id */
    public void setCurriculumId(Integer curriculumId) {
        this.curriculumId = curriculumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return phone.equals(user.phone);

    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }

    /** 获取 培训阶段 */
    public String getStageName() {
        return this.stageName;
    }

    /** 设置 培训阶段 */
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }


    /** 获取 课程类型 */
    public String getCurriculumTypeName() {
        return this.curriculumTypeName;
    }

    /** 设置 课程类型 */
    public void setCurriculumTypeName(String curriculumTypeName) {
        this.curriculumTypeName = curriculumTypeName;
    }

    /** 获取 正在培训的总课时 */
    public Integer getTotalCourseWareNum() {
        return this.totalCourseWareNum;
    }

    /** 设置 正在培训的总课时 */
    public void setTotalCourseWareNum(Integer totalCourseWareNum) {
        this.totalCourseWareNum = totalCourseWareNum;
    }

    /** 获取 用户所在地 全称 */
    public String getCityMergerName() {
        return this.cityMergerName;
    }

    /** 设置 用户所在地 全称 */
    public void setCityMergerName(String cityMergerName) {
        this.cityMergerName = cityMergerName;
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

    /** 获取 邮箱 */
    public String getEmail() {
        return this.email;
    }

    /** 设置 邮箱 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 获取 经销商用户下的 区域列表 */
    public List<City> getUserCityList() {
        return this.userCityList;
    }

    /** 设置 经销商用户下的 区域列表 */
    public void setUserCityList(List<City> userCityList) {
        this.userCityList = userCityList;
    }
}
