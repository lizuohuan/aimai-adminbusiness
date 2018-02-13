package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.Company;

/**
 * 爱麦公司信息
 * @author lzh
 * @create 2017/8/3 19:56
 */
public interface ICompanyMapper {


    void update(Company company);

    Company info();
}
