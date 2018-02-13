package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.enums.PaperEnum;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.IExaminationPaperMapper;
import com.magic.aimai.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 试卷 试题
 * @author lzh
 * @create 2017/8/3 16:58
 */
@Service
public class ExaminationPaperService {

    @Resource
    private IExaminationPaperMapper examinationPaperMapper;

    /**
     * 后台页面 分页获取试卷的考题
     * @param pageArgs 分页属性
     * @return
     */
    public PageList<ExaminationPaper> listForAdmin(PageArgs pageArgs , String title , Integer type , Date startTime , Date endTime,
                                                   Integer category, Integer tradeId, Integer curriculumId, Integer companyId,
                                                   Integer provinceId, Integer cityId, Integer districtId, Integer paperId) throws Exception {
        PageList<ExaminationPaper> pageList = new PageList<ExaminationPaper>();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("title",title);
        params.put("type",type);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        params.put("category",category);
        params.put("tradeId",tradeId);
        params.put("curriculumId",curriculumId);
        params.put("companyId",companyId);
        params.put("provinceId",provinceId);
        params.put("cityId",cityId);
        params.put("districtId",districtId);
        params.put("paperId",paperId);
        int count = examinationPaperMapper.listForAdminCount(params);
        if (count > 0) {
            params.put("pageArgs",pageArgs);
            pageList.setList(examinationPaperMapper.listForAdmin(params));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    public void delete(Integer id) {
        examinationPaperMapper.delete(id);
    }

}
