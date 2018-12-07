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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                ", isDelete=" + isDelete +
                '}';
    }
}
