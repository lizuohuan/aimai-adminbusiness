package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.CurriculumType;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.mapper.ICurriculumTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 课程分类
 * @author lzh
 * @create 2017/7/21 10:39
 */
@Service
public class CurriculumTypeService {

    @Resource
    private ICurriculumTypeMapper curriculumTypeMapper;

    /**
     * 后台页面 分页获取课程
     * @param pageArgs 分页工具
     * @param curriculumTypeName 课程分类
     * @return
     */
    public PageList<CurriculumType> listForAdmin(PageArgs pageArgs , String curriculumTypeName) {
        PageList<CurriculumType> pageList = new PageList<CurriculumType>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("curriculumTypeName",curriculumTypeName);
        int count = curriculumTypeMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(curriculumTypeMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }
}
