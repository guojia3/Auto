package com.sxt.controller;

import com.sxt.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@Api(value = "demo",description = "the first demo")
@RequestMapping(value = "demo")
public class Demo {
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数量",httpMethod = "GET")
    public int getUserCount(){

        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ApiOperation(value = "增加用户",httpMethod = "POST" )
    public int addUser(@RequestBody User user){
         int result = template.insert("addUser",user);
        return result;
    }

}
