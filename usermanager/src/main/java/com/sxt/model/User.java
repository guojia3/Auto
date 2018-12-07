package com.sxt.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private Integer age;
    private Integer sex;
    private String password;
    private Integer permission;
    private Integer isDelete;
}
