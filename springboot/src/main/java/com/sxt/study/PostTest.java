package com.sxt.study;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post")
@Api(value = "/",description = "这是get请求接口！！！")
public class PostTest {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "post request with cookies",httpMethod = "POST")
    public String login(@RequestParam(value = "username",required = true)String user,
                        @RequestParam(value = "password",required = true)String pwd,
                        HttpServletResponse response){
        if (user.equals("username")&&pwd.equals("password")) {
            Cookie cookie = new Cookie("loginCode","12138");
            response.addCookie(cookie);
            return "恭喜登陆成功！";
        }

        return "用户名或者密码错误";

    }
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据验证获取用户列表",httpMethod = "POST")
    public List<User> getUserList(HttpServletRequest request,
                                  @RequestBody User user){
        List<User> list = new ArrayList<>();
       Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("loginCode")&&cookie.getValue().equals("12138")
                    &&user.getUserName().equals("username")&&user.getPassWord().equals("password")) {
                list.add(new User("zhangsan",19));
                list.add(new User("wangwu",20));
                list.add(new User("zhaoliu",14));
                return list;
            }
        }

        return null;

    }
}
