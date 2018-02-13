package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.mapper.ICityMapper;
import com.magic.aimai.business.mapper.ILoginLogMapper;
import com.magic.aimai.business.util.HttpUtils;
import com.magic.aimai.business.util.IpConfig;
import com.magic.aimai.business.util.StatusConstant;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2018/1/19 0019.
 */
@Service
public class LoginLogService {

    @Resource
    private ILoginLogMapper loginLogMapper;
    @Resource
    private ICityMapper cityMapper;

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 登录日志处理 返回 0 不是异常  1 异常
     */
    public int loginLog(HttpServletRequest request, User user,Integer source){
        int i = 0;
        try {
            Integer cityId = null;
            // 通过IP获取城市
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", "APPCODE " + IpConfig.getValue("AppCode"));
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("ip", request.getRemoteAddr());
            HttpResponse response = HttpUtils.doGet(IpConfig.getValue("host"), IpConfig.getValue("path"), "GET", headers, querys);
            String s = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSONObject.fromObject(s);
            if(jsonObject.getInt("code") == 0){
                JSONObject data = jsonObject.getJSONObject("data");
                String cityName = data.getString("city"); // 市级
                City city = cityMapper.queryCityByName(cityName);
                cityId = null == city ? null : city.getId();
            }
            if(null != cityId){
                // 验证当前登录是否属于异常登录
                // 获取最近5次的登录情况
                List<LoginLog> newLogs = loginLogMapper.queryLog(user.getId(), 6);
                if(null != newLogs && newLogs.size() > 0){
                    LoginLog n = null;
                    int index = 0;
                    boolean exception = false;
                    for (LoginLog newLog : newLogs) {
                        if(index == 0){
                            n = newLog;
                            index++;
                            continue;
                        }
                        if(!cityId.equals(newLog.getCityId())){
                            exception = true;
                        }
                        index++;
                    }
                    if(exception && !cityId.equals(n.getCityId())){
                        // 此次登录异常
                        i = 1;
                    }
                }
                // 创建登录日志
                LoginLog log = new LoginLog();
                log.setCityId(cityId);
                log.setIp(request.getRemoteAddr());
                log.setSource(source);
                log.setUserId(user.getId());
                loginLogMapper.add(log);
            }
        } catch (Exception e) {
            logger.error("登录日志处理异常",e);
        }
        return i;
    }

    /**
     * 后台页面 登录日志
     * @param pageArgs 分页属性
     * @param userName
     * @param source
     * @param startTime  创建开始时间
     * @param endTime 创建结束时间
     * @return
     */
    public PageList<LoginLog> list(PageArgs pageArgs , String userName ,
                                       Integer source , Date startTime , Date endTime) {
        PageList<LoginLog> pageList = new PageList<LoginLog>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("source",source);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        int count = loginLogMapper.countLoginLog(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(loginLogMapper.queryLoginLog(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }


}
