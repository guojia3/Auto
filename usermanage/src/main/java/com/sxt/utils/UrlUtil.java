package com.sxt.utils;

import com.sxt.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class UrlUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static  String getUrl(InterfaceName name){
        String host = bundle.getString("test.url");
        String uri = "";
        if(name == InterfaceName.ADDUSER){
            uri = bundle.getString("addUser.uri");
        }
        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }
        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }
        if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }
        if(name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        return host + uri;
    }
}
