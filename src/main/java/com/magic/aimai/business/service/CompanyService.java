package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.Company;
import com.magic.aimai.business.mapper.ICompanyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 爱麦公司信息
 * @author lzh
 * @create 2017/8/3 20:01
 */
@Service
public class CompanyService {

    @Resource
    private ICompanyMapper companyMapper;


    public Company info(){
        return companyMapper.info();
    }

    public void update(Company company){
        companyMapper.update(company);
    }
}
