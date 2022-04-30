package com.fei.springcloud.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private int age;
    private String desc;
}
