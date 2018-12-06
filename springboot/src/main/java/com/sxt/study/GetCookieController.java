package com.sxt.study;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是一个测试文档！！！！")
public class GetCookieController {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "返回cookie接口",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","false");
        response.addCookie(cookie);
        return "成功获取cookie。。。";

    }

    @RequestMapping(value = "/get/with/cookies")
    @ApiOperation(value = "带cookie请求接口",httpMethod = "GET")
    @ResponseBody
    public String getWithCookies(HttpServletRequest request){
       Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "请检查cookie信息";
        }
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("login") && cookie.getValue().equals("false")){
                return "访问成功";
            }
        }

        return "cookie 验证不通过！";
    }
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "get参数方法实现1",httpMethod = "GET")
    public Map<String,Object> getWithParam(@RequestParam Integer start,
                                           @RequestParam Integer end){
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("one",1);
        retMap.put("two",2);
        retMap.put("three",3);
        retMap.put("four",4);

        return retMap;

    }

    @RequestMapping(value = "/get/path/value/{start}/{end}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "get参数接口实现2",httpMethod = "GET")
    public Map<String,Object> getWithPathValue(@PathVariable Integer start,
                                           @PathVariable Integer end){
        Map<String,Object> map = new HashMap<>();
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        map.put("four",4);

        return map;

    }
}
