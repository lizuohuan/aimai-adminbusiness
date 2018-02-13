package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2018/1/17 0017.
 */
public interface ILoginLogMapper {

    int add(LoginLog loginLog);

    List<LoginLog> queryLoginLog(Map<String,Object> map);

    int countLoginLog(Map<String,Object> map);

    /**
     * 获取最新的一条登录日志
     * @param userId
     * @return
     */
    LoginLog queryNewLog(@Param("userId") Integer userId);

    /**
     * 获取最新的N条登录日志
     * @param userId
     * @return
     */
    List<LoginLog> queryLog(@Param("userId") Integer userId,@Param("size") Integer size);

}
