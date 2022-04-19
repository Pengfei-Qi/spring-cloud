package com.fei.springcloud.service;

import com.fei.springcloud.pojo.CompanyTbl;

import java.util.List;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
public interface CompanyService {
    int insert(CompanyTbl companyTbl);

    int delete(Integer id);

    int update(CompanyTbl companyTbl);

    CompanyTbl selectOneById(Integer id);

    List<CompanyTbl> selectAll();
}
