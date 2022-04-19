package com.fei.springcloud.service.impl;

import com.fei.springcloud.mapper.CompanyTblMapper;
import com.fei.springcloud.pojo.CompanyTbl;
import com.fei.springcloud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyTblMapper companyTblMapper;

    @Override
    public int insert(CompanyTbl companyTbl) {
        int count = companyTblMapper.insert(companyTbl);
        return count;
    }

    @Override
    public int delete(Integer id) {
        int count = companyTblMapper.delete(id);
        return count;
    }

    @Override
    public int update(CompanyTbl companyTbl) {
        int count = companyTblMapper.update(companyTbl);
        return count;
    }

    @Override
    public CompanyTbl selectOneById(Integer id) {
        return companyTblMapper.selectOneById(id);
    }

    @Override
    public List<CompanyTbl> selectAll() {
        return companyTblMapper.selectAll();
    }
}
