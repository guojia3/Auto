package com.sxt.model;

import lombok.Data;

@Data
public class GetUserInfo {
    private Integer id;
    private Integer userId;
    private String expected;
}
