package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.Page;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.entity.PaperRecord;
import com.magic.aimai.business.mapper.IPaperRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考试记录
 * @author lzh
 * @create 2017/8/17 15:17
 */
@Service
public class PaperRecordService {

    @Resource
    private IPaperRecordMapper paperRecordMapper;

    /**
     * 后台页面 分页获取考试记录
     * @param pageArgs 分页属性
     * @param userId 用户id
     * @return
     */
    public PageList<PaperRecord> listForAdmin(PageArgs pageArgs , Integer userId) {
        PageList<PaperRecord> pageList = new PageList<PaperRecord>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        int count = paperRecordMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(paperRecordMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 后台页面 分页获取考试记录
     * @param userId 用户id
     * @return
     */
    public Page<PaperRecord> listForWeb(Integer pageNO,Integer pageSize , Integer userId) {
        List<PaperRecord> pageList = new ArrayList<PaperRecord>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        int count = paperRecordMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("limit",(pageNO - 1) * pageSize);
            map.put("pageSize",pageSize);
            pageList = paperRecordMapper.listForWeb(map);
        }
        return new Page<PaperRecord>(pageList,count,0);
    }

    /**
     * 修改考试状态
     * @param record
     */
    public void updateIsPass(PaperRecord record){
        paperRecordMapper.updateIsPass(record);
    }

    /**
     * 没有考试记录  但是需要进行通过或不通过操作  直接进行新增记录
     * @param record
     */
    public void save(PaperRecord record){
        paperRecordMapper.addPaperRecord(record);
    }



}
