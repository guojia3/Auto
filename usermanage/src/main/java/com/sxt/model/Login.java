package com.sxt.model;

import lombok.Data;

@Data
public class Login {
    private Integer id;
    private String username;
    private String password;
    private String expected;


}
