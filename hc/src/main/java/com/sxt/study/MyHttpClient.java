package com.sxt.study;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class MyHttpClient {
    @Test
    public void testDemo() throws IOException {
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


    }
}
