package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.OperationLog;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.mapper.IOperationLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志
 * @author lzh
 * @create 2018/1/23 9:27
 */
@Service
public class OperationLogService {

    @Resource
    private IOperationLogMapper operationLogMapper;


    public OperationLog info(Integer id) {
        return operationLogMapper.info(id);
    }


    @Transactional
    public void save(OperationLog operationLog) {
        operationLogMapper.save(operationLog);
    }


    /**
     * 后台页面 分页操作日志
     * @param pageArgs 分页属性
     * @param userName 操作人
     * @param roleId 角色id
     * @param startTime  创建开始时间
     * @param endTime 创建结束时间
     * @return
     */
    public PageList<OperationLog> list(PageArgs pageArgs , String userName ,
                                           Integer roleId ,  Date startTime , Date endTime) {
        PageList<OperationLog> pageList = new PageList<OperationLog>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("roleId",roleId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        int count = operationLogMapper.listCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(operationLogMapper.list(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 添加操作日志2
     * @param userId
     * @param roleId
     * @param content
     */
    @Transactional
    public void save2(Integer userId,Integer roleId,String content) {
        OperationLog operationLog = new OperationLog();
        operationLog.setContent(content);
        operationLog.setRoleId(roleId);
        operationLog.setUserId(userId);
        operationLogMapper.save(operationLog);
    }
}
