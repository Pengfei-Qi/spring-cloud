package com.fei.springcloud.mapper;

import com.fei.springcloud.pojo.CompanyTbl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@Mapper
@Component
public interface CompanyTblMapper {

    int insert(CompanyTbl companyTbl);

    int delete(Integer id);

    int update(CompanyTbl companyTbl);

    CompanyTbl selectOneById(Integer id);

    List<CompanyTbl> selectAll();
}
