package com.sxt.controller;

import com.sxt.model.User;
import com.sxt.util.ManagerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j
@Controller
@Api(value = "v1",description = "用户管理接口")
@RequestMapping("v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆接口",httpMethod = "POST")
    @ResponseBody
    public Boolean login(HttpServletResponse response,
                         @RequestBody User user){
        int i = template.selectOne("login",user);
        if(i > 0){
            log.info("登陆验证成功！");
            response.addCookie(new Cookie("userCode","12138"));
            return true;
        }
        log.error("登陆验证失败");
        return false;
    }
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "增加用户接口",httpMethod = "POST")
    @ResponseBody
    public boolean addUser(HttpServletRequest request,
                           @RequestBody User user){
        boolean vCookie = ManagerUtil.verifyCookie(request);
        int result;
        if (vCookie){
            result  = template.insert("addUser",user);
            if (result > 0){
                log.info("增加用户成功");
                return true;
            }

        }
        log.error("用户增加失败！");
       return false;
    }
    @ApiOperation(value = "获取用户信息（列表）接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public List<User> getUserInfo(HttpServletRequest request,
                                  @RequestBody User user){
        if (ManagerUtil.verifyCookie(request)){
            List<User> users = template.selectList("getUserInfo",user);
            log.info("获取用户信息成功！！");
            return users;
        }
        return null;
    }
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "更新/删除用户接口",httpMethod = "POST")
    @ResponseBody
    public boolean updateUserInfo(HttpServletRequest request,
                                  @RequestBody User user){
        if (ManagerUtil.verifyCookie(request)){
            int result = template.update("updateUserInfo",user);
            if (result > 0){
                log.info("更新成功");
                return true;
            }
        }
        log.error("更新失败！！！！");
        return false;
    }
}
