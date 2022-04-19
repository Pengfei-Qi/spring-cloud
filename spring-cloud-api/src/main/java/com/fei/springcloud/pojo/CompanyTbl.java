package com.fei.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CompanyTbl implements Serializable {
    private static final long serialVersionUID = 327190059566300566L;
    private int id;
    private String name;
    private Integer count;
    private String address;
    private String dbSource;
}
