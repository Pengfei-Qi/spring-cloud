package com.fei.springcloud.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/*
 * @Author qpf
 * @Date 2022/4/30 10:51
 * @Description: 分页封装
 *
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class PageDTO<T> {
    private Integer pageSize;//页容量
    private Integer pageNum;//页码
    private Integer totalSize;//总容量
    private List<T> pageContent;//内容列表
}
