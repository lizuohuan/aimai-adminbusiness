package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.enums.Common;
import com.magic.aimai.business.enums.RoleEnum;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.face.EyeFaceConfig;
import com.magic.aimai.business.face.EyeFaceUtil;
import com.magic.aimai.business.mapper.ICityMapper;
import com.magic.aimai.business.mapper.ICurriculumMapper;
import com.magic.aimai.business.mapper.IUserCityMapper;
import com.magic.aimai.business.mapper.IUserMapper;
import com.magic.aimai.business.sms.SMSCode;
import com.magic.aimai.business.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.bcel.generic.PUSH;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.*;

/**
 * 用户 业务层
 * Created by Eric Xie on 2017/7/12 0012.
 */
@Service
public class UserService {

    @Resource
    private IUserMapper userMapper;

    @Resource
    private ICityMapper cityMapper;
    @Resource
    private IUserCityMapper userCityMapper;

    /**
     * 登录发送验证码
     * @param phone
     * @return
     */
    public String loginCode(String phone) throws Exception{
        if(null != LoginHelper.get(LoginHelper.KEY_LOGIN+phone)){
            LoginHelper.del(LoginHelper.KEY_LOGIN+phone);
        }
        String code = SMSCode.createRandomCode();
//        String format = MessageFormat.format(TextMessage.MSG_CODE_VALID, code, StatusConstant.VALID_CODE);
        String format = MessageFormat.format(TextMessage.MSG_CODE, code);
        boolean b = SMSCode.sendMessage(format, phone);
        if(!b){
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"发送失败");
        }
        LoginHelper.add(LoginHelper.KEY_LOGIN+phone,code,StatusConstant.VALID_CODE * 60);
        return code;
    }

    /**
     * 获取分销商下的所有游离用户
     * @param userId 分销商ID
     * @return
     */
    public Page<User> queryFreeUser(Integer userId,Integer pageNO,Integer pageSize) throws Exception{
        List<City> cities = userCityMapper.queryCityByUser(userId); // 查询这些城市下的企业的游离状态员工
        Set<Integer> allCityIds = new HashSet<Integer>();
        if(null != cities && cities.size() > 0){
            List<Integer> provinceIds = new ArrayList<Integer>();
            List<Integer> cityIds = new ArrayList<Integer>();
            List<Integer> countyIds = new ArrayList<Integer>();
            for (City city : cities) {
                if(1 == city.getLevelType()){
                    provinceIds.add(city.getId());
                }
                else if(2 == city.getLevelType()){
                    cityIds.add(city.getId());
                }
                else if(3 == city.getLevelType()){
                    countyIds.add(city.getId());
                }
            }
            if(provinceIds.size() > 0){
                List<City> cities1 = cityMapper.queryCitys(provinceIds); // 市级
                if(null != cities1 && cities1.size() > 0){
                    for (City city : cities1) {
                        cityIds.add(city.getId());
                    }
                }
                allCityIds.addAll(provinceIds);
            }

            if(cityIds.size() > 0){
                List<City> cities1 = cityMapper.queryCitys(cityIds); // 区县
                if(null != cities1 && cities1.size() > 0){
                    for (City city : cities1) {
                        countyIds.add(city.getId());
                    }
                }
                allCityIds.addAll(cityIds);
            }
            if(countyIds.size() > 0){
                allCityIds.addAll(countyIds);
            }
        }
        List<User> users = new ArrayList<User>();
        int count = 0;
        if(allCityIds.size() > 0){
            users = userMapper.queryCompanyByCity(allCityIds,null,null);
            count = users.size();
            if(users.size() > 0){
                users = userMapper.queryCompanyByCity(allCityIds,(pageNO - 1) * pageSize, pageSize);
            }
        }
        return new Page<User>(users,count,0);
    }

    public User getUserByEmail(String email,Integer userId){
        return userMapper.queryUserByEmail(email,userId);
    }

    /**
     * 获取在该公司下某课程未分配的员工列表
     * @param curriculumId
     * @param companyId
     * @return
     */
    public List<User> getUserByCurriculum(Integer curriculumId,Integer companyId){
        return userMapper.queryUserByCurriculum(curriculumId,companyId);
    }

 /**
     * 获取在该公司下某课程已经分配的员工列表
     * @param curriculumId
     * @param companyId
     * @return
     */
    public List<User> getUserByCurriculum2(Integer curriculumId,Integer companyId){
        return userMapper.queryUserByCurriculum2(curriculumId,companyId);
    }


    //*****************************
    //**********以上V2 API*********//
    //*****************************//

    public PageList<User> getUserByIsValid(PageArgs pageArgs,Integer isValid,Integer roleId,String phoneNumber){
        int count = userMapper.countUserByIsValid(isValid, roleId, phoneNumber);
        List<User> users = userMapper.queryUserByIsValid(isValid, roleId, phoneNumber, pageArgs.getPage(), pageArgs.getPageSize());
        return new PageList<User>(users,count);

    }

    /**
     * 公司查询 该课程下 观看人列表 和 考核人列表
     * @param flag 0 : 查看已观看的人数 1:已考核的人
     * @param companyId
     * @param orderId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<User> queryUserStudyStatus(Integer flag,Integer companyId,Integer orderId,Integer pageNO,Integer pageSize){
        List<User> users = null;
        if(0 == flag){
            // 查看已观看的人数
            users = userMapper.queryWatchVideo(companyId,orderId,(pageNO - 1) * pageSize, pageSize);
        }
        else if(1 == flag){
            users = userMapper.queryExamine(companyId,orderId,(pageNO - 1) * pageSize, pageSize);
        }
        return users;
    }

    /**
     *  公司用户  导入员工列表
     * @param resourcesUrl
     * @param companyId
     * @return
     * @throws Exception
     */
    public Map<String,Object> importExcelUser(String resourcesUrl, Integer companyId) throws Exception {

        int HttpResult; // 服务器返回的状态
        URL url = new URL(resourcesUrl); // 创建URL
        URLConnection urlConn = url.openConnection();
        urlConn.connect();
        HttpURLConnection httpConn = (HttpURLConnection) urlConn;
        HttpResult = httpConn.getResponseCode();
        if (HttpResult != HttpURLConnection.HTTP_OK)
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据读取失败");
        else {
            Map<Integer, List<String>> map = ExcelReader.readExcelContent(urlConn.getInputStream());
            List<User> importUsers = new ArrayList<User>(); // 读取出来的用户列表
            List<String> phones = new ArrayList<String>();
            // 解析数据
            for (Integer cellNum : map.keySet()) {
                List<String> values = map.get(cellNum);
                if (null == values.get(0)) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "手机号必须填写");
                }
                User temp = new User();
                temp.setPhone(values.get(0));
                temp.setDepartmentName(null == values.get(0) ? null : values.get(1));
                temp.setJobTitle(null == values.get(0) ? null : values.get(2));
                importUsers.add(temp);
                phones.add(temp.getPhone());
            }

            if (phones.size() == 0 || importUsers.size() == 0) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "表格数据为空");
            }
            Map<String, String> unExistUser = new HashMap<String, String>(); // 不存在的用户
            Map<String, String> existCompany = new HashMap<String, String>(); // 已经有公司的用户
            List<User> waitUpdate = new ArrayList<User>(); // 待更新的用户
            int successNum = 0;
            int failNum = 0;
            List<User> users = userMapper.batchQueryUserByPhone(phones);
            for (User importUser : importUsers) {
                if (null == users || users.size() == 0) {
                    unExistUser.put(importUser.getPhone(), "系统中不存在此用户");
                    failNum++;
                    continue;
                }
                boolean isExist = false;
                for (User user : users) {
                    if(importUser.getPhone().equals(user.getPhone())){
                        isExist = true;
                        if(null == user.getParentId()){
                            User wait = new User();
                            wait.setParentId(companyId);
                            wait.setJobTitle(importUser.getJobTitle());
                            wait.setDepartmentName(importUser.getDepartmentName());
                            wait.setPhone(importUser.getPhone());
                            waitUpdate.add(wait);
                            successNum++;
                        }else{
                            existCompany.put(importUser.getPhone(), "该用户已经绑定其他公司");
                            failNum++;
                        }
                    }
                }
                if(!isExist){
                    unExistUser.put(importUser.getPhone(), "系统中不存在此用户");
                    failNum++;
                }
            }
            // 批量更新
            if(waitUpdate.size() > 0) {
                userMapper.batchUpdate(waitUpdate);
            }
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("successNum",successNum);
            result.put("failNum",failNum);
            result.put("unExistUser",unExistUser);
            result.put("existCompany",existCompany);
            return result;
        }
    }


    /**
     * 查询公司的用户详细列表
     *
     * @param companyId
     * @return
     */
    public List<User> queryUserByParent(Integer companyId, String userName,List<Integer> userIds) {
        return userMapper.queryUserByParent(companyId, userName,userIds);
    }

    /**
     * 查询公司详情
     *
     * @param companyId
     * @return
     */
    public User queryCompanyDetail(Integer companyId) {
        return userMapper.queryCompanyDetail(companyId);
    }


    public List<WebStatistics> exportExcel(Date year, Integer stageId, Integer cityId, Integer levelType, String searchParams) {

        List<Integer> cityIds = new ArrayList<Integer>();
        if (null != cityId && !"100000".equals(cityId.toString())) {
            cityIds.add(cityId);
            {
                City city = cityMapper.queryCity(cityId);
                if (null == city) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"未知的地区");
                }
                List<City> cities = cityMapper.queryCityByParentIds(cityIds,city.getLevelType());
                for (City city1 : cities) {
                    cityIds.add(city1.getId());
                }
            }
            List<City> cityList = null;
            if (!CommonUtil.isEmpty(searchParams)) {
                cityList = cityMapper.queryCityByItems(searchParams);
            }


            if (null != cityList && cityList.size() > 0) {
                List<Integer> provinceIds = new ArrayList<Integer>();
                List<Integer> townIds = new ArrayList<Integer>();
                for (City city : cityList) {
                    if (city.getLevelType() == 1) {
                        provinceIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 2) {
                        townIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 3) {
                        cityIds.add(city.getId());
                    }
                }
                if (provinceIds.size() > 0) {
                    List<City> provinceCityList = cityMapper.queryCityByParentIds(provinceIds, 1);
                    if (null != provinceCityList && provinceCityList.size() > 0) {
                        for (City city : provinceCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
                if (townIds.size() > 0) {
                    List<City> townCityList = cityMapper.queryCityByParentIds(townIds, 2);
                    if (null != townCityList && townCityList.size() > 0) {
                        for (City city : townCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
            }
        }
        if (cityIds.size() == 0) {
            cityIds = null;
        }
        return userMapper.exportExcel(year, stageId, cityIds.size() > 0 ? cityIds : null,
                CommonUtil.isEmpty(searchParams) ? null : searchParams);
    }


    /**
     * web端 政府个人中心 统计接口
     *
     * @param year
     * @param stageId
     * @return
     */
    public WebStatistics webStatistics(Date year, Integer stageId, Integer cityId, Integer levelType,
                                       String searchParams,Set<Integer> allCityIds) {
        List<Integer> cityIds = new ArrayList<Integer>();
        if (null != cityId && !"100000".equals(cityId.toString())) {
            cityIds.add(cityId);
            {
                City city = cityMapper.queryCity(cityId);
                if (null == city) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"未知的地区");
                }
                List<City> cities = cityMapper.queryCityByParentIds(cityIds,city.getLevelType());
                for (City city1 : cities) {
                    cityIds.add(city1.getId());
                }
            }
            List<City> cityList = null;
            if (!CommonUtil.isEmpty(searchParams)) {
                cityList = cityMapper.queryCityByItems(searchParams);
            }


            if (null != cityList && cityList.size() > 0) {
                List<Integer> provinceIds = new ArrayList<Integer>();
                List<Integer> townIds = new ArrayList<Integer>();
                for (City city : cityList) {
                    if (city.getLevelType() == 1) {
                        provinceIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 2) {
                        townIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 3) {
                        cityIds.add(city.getId());
                    }
                }
                if (provinceIds.size() > 0) {
                    List<City> provinceCityList = cityMapper.queryCityByParentIds(provinceIds, 1);
                    if (null != provinceCityList && provinceCityList.size() > 0) {
                        for (City city : provinceCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
                if (townIds.size() > 0) {
                    List<City> townCityList = cityMapper.queryCityByParentIds(townIds, 2);
                    if (null != townCityList && townCityList.size() > 0) {
                        for (City city : townCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
            }
        }

        if (cityIds.size() == 0 && null != allCityIds && allCityIds.size() > 0) {
            cityIds.addAll(allCityIds);
        }
        return userMapper.webStatistics(year, stageId, cityIds.size() > 0 ? cityIds : null, CommonUtil.isEmpty(searchParams) ? null : searchParams);
    }

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    public User queryUserDetail(Integer userId) {
        return userMapper.queryUserDetail(userId);
    }

    /**
     * 政府动态获取 公司列表 API
     *
     * @param searchParams
     * @param cityId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<User> queryUserForGovernment(String searchParams, Integer cityId, Integer pageNO, Integer pageSize) {
        List<Integer> cityIds = new ArrayList<Integer>();
        if (null != cityId && !"100000".equals(cityId.toString())) {
            cityIds.add(cityId);
            {
                City city = cityMapper.queryCity(cityId);
                if (null == city) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"未知的地区");
                }
                List<City> cities = cityMapper.queryCityByParentIds(cityIds,city.getLevelType());
                for (City city1 : cities) {
                    cityIds.add(city1.getId());
                }
            }
            List<City> cityList = null;
            if (!CommonUtil.isEmpty(searchParams)) {
                cityList = cityMapper.queryCityByItems(searchParams);
            }


            if (null != cityList && cityList.size() > 0) {
                List<Integer> provinceIds = new ArrayList<Integer>();
                List<Integer> townIds = new ArrayList<Integer>();
                for (City city : cityList) {
                    if (city.getLevelType() == 1) {
                        provinceIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 2) {
                        townIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 3) {
                        cityIds.add(city.getId());
                    }
                }
                if (provinceIds.size() > 0) {
                    List<City> provinceCityList = cityMapper.queryCityByParentIds(provinceIds, 1);
                    if (null != provinceCityList && provinceCityList.size() > 0) {
                        for (City city : provinceCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
                if (townIds.size() > 0) {
                    List<City> townCityList = cityMapper.queryCityByParentIds(townIds, 2);
                    if (null != townCityList && townCityList.size() > 0) {
                        for (City city : townCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
            }
        }
        if (cityIds.size() == 0) {
            cityIds = null;
        }

        return userMapper.queryUserForGovernment(cityIds, searchParams, (pageNO - 1) * pageSize, pageSize);
    }

    /**
     * 政府动态获取 公司列表  WEB
     *
     * @param searchParams
     * @param cityId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public Page<User> queryUserForGovernment(String searchParams, Integer cityId, Integer levelType,
                                             Integer pageNO, Integer pageSize,Set<Integer> allCityIds) {

        List<Integer> cityIds = new ArrayList<Integer>();
        if (!"100000".equals(cityId)) {
            cityIds.add(cityId);
            if (null != cityId) {
                City city = cityMapper.queryCity(cityId);
                if (null == city) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"未知的地区");
                }
                List<City> cities = cityMapper.queryCityByParentIds(cityIds,city.getLevelType());
                for (City city1 : cities) {
                    cityIds.add(city1.getId());
                }
            }
            List<City> cityList = null;
            if (!CommonUtil.isEmpty(searchParams)) {
                cityList = cityMapper.queryCityByItems(searchParams);
            }


            if (null != cityList && cityList.size() > 0) {
                List<Integer> provinceIds = new ArrayList<Integer>();
                List<Integer> townIds = new ArrayList<Integer>();
                for (City city : cityList) {
                    if (city.getLevelType() == 1) {
                        provinceIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 2) {
                        townIds.add(city.getId());
                        cityIds.add(city.getId());
                    } else if (city.getLevelType() == 3) {
                        cityIds.add(city.getId());
                    }
                }
                if (provinceIds.size() > 0) {
                    List<City> provinceCityList = cityMapper.queryCityByParentIds(provinceIds, 1);
                    if (null != provinceCityList && provinceCityList.size() > 0) {
                        for (City city : provinceCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
                if (townIds.size() > 0) {
                    List<City> townCityList = cityMapper.queryCityByParentIds(townIds, 2);
                    if (null != townCityList && townCityList.size() > 0) {
                        for (City city : townCityList) {
                            cityIds.add(city.getId());
                        }
                    }
                }
            }
        }
        if (cityIds.size() == 0 && allCityIds.size() > 0) {
            cityIds.addAll(allCityIds);
        }
        Integer count = userMapper.countUserForGovernment(cityIds, searchParams);
        List<User> users = new ArrayList<User>();
        if (count > 0) {
            users = userMapper.queryUserForGovernment(cityIds, searchParams, (pageNO - 1) * pageSize, pageSize);
        }
        return new Page<User>(users, count, 0);
    }

    /**
     * 通过公司ID 动态查询用户
     *
     * @param companyId
     * @param isAllocation
     * @param orderId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<User> queryUserByCompany(Integer companyId,String searchParams, Integer isAllocation,
                                         Integer orderId, Integer pageNO, Integer pageSize,Integer curriculumId) {
        return userMapper.queryUserByCompanyId(companyId, searchParams, isAllocation, orderId, (pageNO - 1) * pageSize, pageSize,curriculumId);
    }

    /**
     * 通过公司ID 动态查询用户
     *
     */
    public Page<User> queryUserByCompanyOfWeb(Integer companyId,String searchParams, Integer isAllocation,
                                         Integer orderId, Integer pageNO, Integer pageSize,Integer curriculumId) {

        List<User> counts = userMapper.queryUserByCompanyId(companyId, searchParams, isAllocation, orderId, null, null,
                curriculumId);

        List<User> users = null;
        if (null != counts && counts.size() > 0) {
            users = userMapper.queryUserByCompanyId(companyId, searchParams, isAllocation, orderId,
                    (pageNO - 1) * pageSize, pageSize,curriculumId);
        }
        return new Page<User>(users,null == counts ? 0 : counts.size(),0);
    }


    public List<User> queryCompanyList() {
        return userMapper.queryCompanyList();
    }


    /**
     * 获取游离状态的用户列表
     *
     * @param phone
     * @param pid
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<User> queryNoCompanyUser(String phone, String pid, Integer pageNO, Integer pageSize,User currentUser) {

        Set<Integer> cityIds = new HashSet<Integer>();
        City city = cityMapper.queryCity(currentUser.getCityId());
        return userMapper.queryNoCompanyUser(phone, pid,
                null == pageNO && null == pageSize ? null : (pageNO - 1) * pageSize,
                null == pageSize ? null : pageSize,city.getId(),city.getLevelType());
    }

    /**
     * 登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return
     * @throws Exception
     */
    public User login(String phone, String password) throws Exception {
        User user = userMapper.queryUserByPhone(phone);
        if (null == user) {
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "用户不存在");
        }
        if (Common.YES.ordinal() != user.getIsValid()) {
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "帐号被封");
        }
        if (RoleEnum.COMPANY_USER.ordinal() == user.getRoleId() ||
                RoleEnum.BUSINESS_USER.ordinal() == user.getRoleId() ||
                RoleEnum.GOVERNMENT_USER.ordinal() == user.getRoleId()) {
            if (2 == user.getStatus()) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "帐号待审核");
            }
            if (0 == user.getStatus()) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "您的账号已被拒绝通过，请您重新注册提交资料");
            }
        }
        if (!password.equals(user.getPwd())) {
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "密码错误");
        }
        return user;
    }

    public User queryUserByPhone(String phone) {
        return userMapper.queryUserByPhone(phone);
    }


    /**
     * 查询用户的基础信息
     *
     * @param id
     * @return
     */
    public User queryBaseInfo(Integer id) {
        return userMapper.queryBaseInfoById(id);
    }


    /**
     * 更新用户 不为空的字段 通过ID
     *
     * @param user
     */
    public void updateUser(User user) {
        if (null != user.getPid() && !"".equals(user.getPid())) {
            User user1 = userMapper.getByPid(user.getPid() ,user.getRoleId());
            if (null != user1 && !user1.getId().equals(user.getId())) {
                if (RoleEnum.GOVERNMENT_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此机构代码已存在");
                }
                if (RoleEnum.COMPANY_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此营业执照编码已存在");
                }
                if (RoleEnum.BUSINESS_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此资质编码已存在");
                }
                if (RoleEnum.USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此身份证号已存在");
                }
            }
        }
        if (null != user.getRoleId() && user.getRoleId().equals(RoleEnum.USER.ordinal())) {
            if (null != user.getParentId()) {
                User company = userMapper.info(user.getParentId());
                if (null == company) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "企业不存在");
                }
                if (null != user.getTradeId()) {
                    if (!company.getTradeId().equals(user.getTradeId())) {
                        throw new InterfaceCommonException(StatusConstant.Fail_CODE, "不能修改与企业不同的行业，请与企业行业一致");
                    }
                }
                user.setTradeId(company.getTradeId());
            }
        }
        User user1 = userMapper.info(user.getId());
        if (null == user1) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "未知用户");
        }
        user.setAccumulate(user1.getAccumulate());
        userMapper.updateUser(user);
    }

    /**
     * 更新用户 不为空的字段 通过ID
     *
     * @param user
     */
    public void updateUser(User user,String cityIds) {
        if (null != user.getPid() && !"".equals(user.getPid())) {
            User user1 = userMapper.getByPid(user.getPid() ,user.getRoleId());
            if (null != user1 && !user1.getId().equals(user.getId())) {
                if (RoleEnum.GOVERNMENT_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此机构代码已存在");
                }
                if (RoleEnum.COMPANY_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此营业执照编码已存在");
                }
                if (RoleEnum.BUSINESS_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此资质编码已存在");
                }
                if (RoleEnum.USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此身份证号已存在");
                }
            }
        }
        if (null != user.getRoleId() && user.getRoleId().equals(RoleEnum.USER.ordinal())) {
            if (null != user.getParentId()) {
                User company = userMapper.info(user.getParentId());
                if (null == company) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "企业不存在");
                }
                if (null != user.getTradeId()) {
                    if (!company.getTradeId().equals(user.getTradeId())) {
                        throw new InterfaceCommonException(StatusConstant.Fail_CODE, "不能修改与企业不同的行业，请与企业行业一致");
                    }
                }
                user.setTradeId(company.getTradeId());
            }
        }
        User user1 = userMapper.info(user.getId());
        if (null == user1) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "未知用户");
        }
        if(RoleEnum.BUSINESS_USER.ordinal() == user1.getRoleId() ){
            userCityMapper.del(user.getId());
            // 分销商
            if (!CommonUtil.isEmpty(cityIds)) {
                List<UserCity> userCityList = new ArrayList<UserCity>();
                JSONArray jsonArray = JSONArray.fromObject(cityIds);
                for (Object o : jsonArray) {
                    UserCity u = new UserCity();
                    u.setCityId(Integer.valueOf(o.toString()));
                    u.setUserId(user.getId());
                    userCityList.add(u);
                }
                if(userCityList.size() > 0){
                    userCityMapper.batchAdd(userCityList);
                }
            }
        }
        user.setAccumulate(user1.getAccumulate());
        userMapper.updateUser(user);
    }

    public void update(User user){
        userMapper.updateUser(user);
    }

    /**
     * 通过ID 批量更新
     *
     * @param users
     */
    public void updateUser(List<User> users) {
        userMapper.batchUpdateUser(users);
    }


    public void clearDeviceInfo(Integer userId) {
        userMapper.clearDeviceInfo(userId);
    }


    public User queryBaseUserByPhone(String phone) {
        return userMapper.queryBaseUserByPhone(phone);
    }

    /**
     * 新增用户
     *
     * @param user
     * @throws Exception
     */
    public User addUser(User user,String cityIds) throws Exception {
        User sqlUser = userMapper.queryBaseUserByPhone(user.getPhone());
        if (null != sqlUser && StatusConstant.ACCOUNT_NON_APPROVED.equals(user.getStatus())
                && (RoleEnum.COMPANY_USER.ordinal() == sqlUser.getRoleId() ||
                RoleEnum.GOVERNMENT_USER.ordinal() == sqlUser.getRoleId())) {
            user.setId(sqlUser.getId());
            userMapper.updateUser(user);
            return user;
        }
        if (null != sqlUser) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "该手机号已被注册");
        }
        if (RoleEnum.COMPANY_USER.ordinal() == user.getRoleId()) {
            // 如果是 公司用户
            if (null != userMapper.queryUserByCompany(user.getShowName())) {
                throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "该公司已经注册");
            }
        }

        if (null != user.getPid() && !"".equals(user.getPid())) {
            User user1 = userMapper.getByPid(user.getPid() ,user.getRoleId());
            if (null != user1) {
                if (RoleEnum.GOVERNMENT_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此机构代码已存在");
                }
                if (RoleEnum.COMPANY_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此营业执照编码已存在");
                }
                if (RoleEnum.BUSINESS_USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此资质编码已存在");
                }
                if (RoleEnum.USER.ordinal() == user.getRoleId()) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此身份证号已存在");
                }
            }
        }

        if (null != user.getRoleId() && user.getRoleId().equals(RoleEnum.USER.ordinal())) {
            if (null != user.getParentId()) {
                User company = userMapper.info(user.getParentId());
                if (null == company) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "企业不存在");
                }
                user.setTradeId(company.getTradeId());
            }
        }
        userMapper.addUser(user);
        if(RoleEnum.BUSINESS_USER.ordinal() == user.getRoleId() && !CommonUtil.isEmpty(cityIds)){
            // 分销商
            List<UserCity> userCityList = new ArrayList<UserCity>();
            JSONArray jsonArray = JSONArray.fromObject(cityIds);
            for (Object o : jsonArray) {
                UserCity u = new UserCity();
                u.setCityId(Integer.valueOf(o.toString()));
                u.setUserId(user.getId());
                userCityList.add(u);
            }
            if(userCityList.size() > 0){
                userCityMapper.batchAdd(userCityList);
            }
        }
        return user;
    }

    /**
     * 批量新增用户
     *
     * @param users  用户实体
     * @param roleId 如果新增的是 公司用户 roleId 必选 (可选)
     * @return 当批量导入公司用户时，返回没有新增成功的 用户，其他用户则新增成功
     * @throws Exception
     */
    public List<User> addUser(List<User> users, Integer roleId) throws Exception {

        if (null == users || users.size() == 0) {
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据不能为空");
        }
        // 不能新增的用户
        List<User> failUser = new ArrayList<User>();

        if (null != roleId && RoleEnum.COMPANY_USER.ordinal() == roleId) {
            for (User user : users) {
                if (!roleId.equals(user.getRoleId())) {
                    throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据出错");
                }
            }
            // 验证 公司名
            List<User> sqlUsers = userMapper.queryUserByRoleId(roleId);
            for (User user : users) {
                for (User sqlUser : sqlUsers) {
                    if (sqlUser.getShowName().equals(user.getShowName())) {
                        failUser.add(user);
                    }
                }
            }
            if (failUser.size() > 0) {
                // 排除不能新增的用户
                Iterator<User> iterator = users.iterator();
                while (iterator.hasNext()) {
                    for (User user : failUser) {
                        if (user.getShowName().equals(iterator.next().getShowName())) {
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
        }
        userMapper.batchAddUser(users);
        return failUser;
    }


    public User queryUserByCompany(String companyName) {
        return userMapper.queryUserByCompany(companyName);
    }


    /**
     * 后台页面 分页获取用户
     *
     * @param pageArgs 分页属性
     * @param phone    手机号
     * @param roleId   角色id
     * @param cityId   城市id
     * @param tradeId  行业id
     * @param status   状态 企业、政府用户注册 后台审核状态 0：未通过 1：审核通过  2：审核中
     * @param showName 展示名称 个人：姓名 政府：机构名称 企业：企业名称
     * @return
     */
    public PageList<User> listForAdmin(PageArgs pageArgs, String phone, Integer roleId,
                                       Integer provinceId, Integer cityId, Integer districtId,
                                       Integer tradeId, Integer status, String showName) {
        PageList<User> pageList = new PageList<User>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phone", phone);
        map.put("provinceId", provinceId);
        map.put("cityId", cityId);
        map.put("districtId", districtId);
        map.put("tradeId", tradeId);
        map.put("status", status);
        map.put("roleId", roleId);
        map.put("showName", showName);
        int count = userMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs", pageArgs);
            pageList.setList(userMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 后台页面 分页获取用户(档案管理)
     *
     * @param pageArgs    分页属性
     * @param phone       手机号
     * @param roleId      角色id
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param companyName 公司名
     * @return
     */
    public PageList<User> listForAdminRecord(PageArgs pageArgs, String phone, Integer roleId, String pid,
                                             String companyName, Date startTime, Date endTime,Integer companyId) {
        PageList<User> pageList = new PageList<User>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phone", phone);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("roleId", roleId);
        map.put("pid", pid);
        map.put("companyId", companyId);
        map.put("companyName", companyName);
        int count = userMapper.listForAdminRecordCount(map);
        if (count > 0) {
            map.put("pageArgs", pageArgs);
            pageList.setList(userMapper.listForAdminRecord(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 后台页面 分页获取用户(档案管理)
     *
     * @param phone       手机号
     * @param roleId      角色id
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param companyName 公司名
     * @return
     */
    public Page<User> listForWebRecord(Integer pageNO,Integer pageSize, String phone, Integer roleId, String pid,
                                             String companyName, Date startTime, Date endTime,Integer companyId,
                                       Set<Integer> cityIdSet) {
        List<User> pageList = new ArrayList<User>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phone", phone);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("roleId", roleId);
        map.put("pid", pid);
        map.put("companyId", companyId);
        map.put("companyName", companyName);
        map.put("cityIdSet", cityIdSet);
        int count = userMapper.listForAdminRecordCount(map);
        if (count > 0) {
            map.put("limit", (pageNO - 1) * pageSize);
            map.put("limitSize", pageSize);
            pageList = userMapper.listForWebRecord(map);
        }
        return new Page<User>(pageList,count,0);
    }


    /**
     * 通过ID 查询用户 详情 包含公司名  地区
     *
     * @param id
     * @return
     */
    public User info(Integer id) {
        User user = userMapper.info(id);
        //封装省市区
        if (null != user.getCityId()) {
            City city = cityMapper.getThreeId(user.getCityId());
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(city.getProvinceId());
            jsonArray.add(city.getTownId());
            jsonArray.add(city.getId());
            user.setCityJsonAry(jsonArray);
        }
        if(RoleEnum.BUSINESS_USER.ordinal() == user.getRoleId()){
            user.setUserCityList(userCityMapper.queryCityByUser(user.getId()));
        }
        return user;
    }


    /**
     *  公司用户  导入员工列表
     * @param resourcesUrl
     * @param companyId
     * @return
     * @throws Exception
     */
    public Map<String,Object> importExcenUserWeb(String resourcesUrl, Integer companyId,Integer roleId) throws Exception {

        int HttpResult; // 服务器返回的状态
        URL url = new URL(resourcesUrl); // 创建URL
        URLConnection urlConn = url.openConnection();
        urlConn.connect();
        HttpURLConnection httpConn = (HttpURLConnection) urlConn;
        HttpResult = httpConn.getResponseCode();
        if (HttpResult != HttpURLConnection.HTTP_OK)
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据读取失败");
        else {
            Map<Integer, List<String>> map = ExcelReader.readExcelContent(urlConn.getInputStream());
            List<User> importUsers = new ArrayList<User>(); // 读取出来的用户列表
            List<String> phones = new ArrayList<String>();
            List<String> pids = new ArrayList<String>();
            List<User> waitAdd = new ArrayList<User>(); // 待新增的用户
            List<User> failUsers = new ArrayList<User>(); // 添加失败的用户
            // 解析数据
            for (Integer cellNum : map.keySet()) {
                List<String> values = map.get(cellNum);
                if (values.size() < 4) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "数据不完整");
                }
                if (null == values.get(0)) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "名称必须填写");
                }
                if (null == values.get(1)) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "手机号必须填写");
                }
                if(values.get(1).length() != 11){
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "手机号必须11位");
                }
                if (null == values.get(2)) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "机构代码/营业执照编码必须填写");
                }
                User temp = new User();
                temp.setShowName(values.get(0));
                temp.setPhone(values.get(1));
                temp.setPid(null == values.get(2) ? null : values.get(2));
                temp.setIntroduce(null == values.get(3) ? null : values.get(3));
                temp.setRoleId(roleId);
                temp.setPwd(MD5Util.md5("111111"));
                temp.setStatus(1);
                //普通用户
                if (importUsers.contains(temp)) {
                    failUsers.add(temp);
                } else {
                    importUsers.add(temp);
                    phones.add(temp.getPhone());
                    pids.add(temp.getPid());
                }

            }

            if (phones.size() == 0 || importUsers.size() == 0) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "表格数据为空");
            }

            int successNum = 0;
            int failNum = 0;
            List<User> users = userMapper.batchQueryUserByPhone(phones);
            List<User> users1 = userMapper.getByPids(pids,roleId);
            if (null == users || users.size() == 0) {
                waitAdd.addAll(importUsers);
                successNum += waitAdd.size();
            }
            else{
                for (User importUser : importUsers) {
                    if(users.contains(importUser) || users1.contains(importUser)){
                        // 如果存在
                        failNum++;
                        failUsers.add(importUser);
                        continue;
                    }
                    waitAdd.add(importUser);
                    successNum++;
                }
            }
            //批量添加
            if (waitAdd.size() > 0) {
                userMapper.batchAddUser(waitAdd);
            }
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("successNum",successNum);
            result.put("failNum",failNum);
            result.put("failUsers",failUsers);
            return result;
        }
    }


    /**
     * 后台页面 分页获取用户(档案管理)
     *
     * @param pageArgs    分页属性
     * @param phone       手机号
     * @param orderId     订单id
     * @return
     */
    public PageList<User> findUserByPhone2(PageArgs pageArgs, String phone, Integer orderId) {
        PageList<User> pageList = new PageList<User>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phone", phone);
        map.put("orderId", orderId);
        int count = userMapper.findUserByPhoneCount(map);
        if (count > 0) {
            map.put("pageArgs", pageArgs);
            pageList.setList(userMapper.findUserByPhone2(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 与公司脱离关系 与上级用户解除关联
     * @param id
     */
    public void removeParentId(Integer id) {
        userMapper.removeParentId(id);
    }

    /**
     * 导出用户 及 课程(档案管理)
     *
     * @param phone       手机号
     * @param roleId      角色id
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param companyName 公司名
     * @return
     */
    public List<User> excelUser(String phone, Integer roleId, String pid,
                                             String companyName, Date startTime, Date endTime,Integer companyId,List<Integer> userIdList) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phone", phone);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("roleId", roleId);
        map.put("pid", pid);
        map.put("companyId", companyId);
        map.put("companyName", companyName);
        map.put("userIds", userIdList);
        return userMapper.excelUser(map);
    }


}
