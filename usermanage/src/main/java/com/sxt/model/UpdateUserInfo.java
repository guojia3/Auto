package com.sxt.model;

import lombok.Data;

@Data
public class UpdateUserInfo {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer sex;
    private Integer age;
    private Integer permission;
    private Integer isDelete;
    private String expected;
}
