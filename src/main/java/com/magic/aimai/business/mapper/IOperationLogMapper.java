package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.OperationLog;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 * @author lzh
 * @create 2018/1/22 15:57
 */
public interface IOperationLogMapper {


    void save(OperationLog operationLog);

    void update(OperationLog operationLog);

    OperationLog info(Integer id);

    /**
     * 操作日志列表
     * @param map
     * @return
     */
    List<OperationLog> list(Map<String,Object> map);

    /**
     * 操作日志 总条数
     * @param map
     * @return
     */
    int listCount(Map<String,Object> map);
}
