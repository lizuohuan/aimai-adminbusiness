package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.Page;
import com.magic.aimai.business.entity.SystemInfo;
import com.magic.aimai.business.enums.Common;
import com.magic.aimai.business.mapper.ISystemInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/13 0013.
 */
@Service
public class SystemInfoService {


    @Resource
    private ISystemInfoMapper systemInfoMapper;


    /**
     * 移动端是否显示 小红点  0不显示  1显示
     * @param userId
     * @return
     */
    public int countCheckInfo(Integer userId){
        List<SystemInfo> systemInfos = systemInfoMapper.countCheckInfo(userId);
        if(null == systemInfos || systemInfos.size() == 0){
            return  0;
        }
        for (SystemInfo systemInfo : systemInfos) {
            if(Common.NO.ordinal() == systemInfo.getIsChecked()){
                return 1;
            }
        }
        return 0;
    }


    public List<SystemInfo> queryInfoOfUser(Integer userId,Integer limit,Integer limitSize){
        return systemInfoMapper.querySystemInfo(limit,limitSize,userId);
    }


    public Page<SystemInfo> queryInfoOfWeb(Integer userId, Integer limit, Integer limitSize){
        List<SystemInfo> systemInfos = null;
        Integer count = systemInfoMapper.countSystemInfo(limit, limitSize, userId);
        if(count > 0){
            systemInfos = systemInfoMapper.querySystemInfo(limit, limitSize, userId);
        }
        return new Page<SystemInfo>(systemInfos,count,0);
    }


    /**
     * 新增用户 查看系统消息 记录
     * 如果看过，则不再记录
     * @param userId 用户ID
     * @param systemInfoId 系统ID
     */
    public void addSystemInfoCheck(Integer userId,Integer systemInfoId){
        Integer count = systemInfoMapper.querySystemInfoIsCheck(userId, systemInfoId);
        if(count == 0){
            systemInfoMapper.addSystemInfoUser(userId,systemInfoId);
        }
    }




}
