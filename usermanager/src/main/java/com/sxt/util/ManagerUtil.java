package com.sxt.util;

import lombok.extern.log4j.Log4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
@Log4j
public class ManagerUtil {

    public static boolean verifyCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            log.error("cookie 校验失败");
            return  false;
        }
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("userCode") && cookie.getValue().equals("12138")){
                log.info("cookie校验成功");
                return true;
            }

        }
        log.error("cookie 校验失败");
        return false;
    }
}
