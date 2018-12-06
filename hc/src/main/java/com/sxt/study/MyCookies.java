package com.sxt.study;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookies {

    private String host;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void bTest(){
        bundle = ResourceBundle.getBundle("application");
        host = bundle.getString("test.host");
    }

    @Test
    public void test() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String url = host + uri;
        HttpGet get = new HttpGet(url);
        DefaultHttpClient client = new DefaultHttpClient();
        result = EntityUtils.toString(client.execute(get).getEntity(),"utf-8");
        System.out.println(result);

        store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie:cookieList) {
            System.out.println("name>>>" + cookie.getName() + ";value>>>>>" + cookie.getValue());
        }
    }
    @Test(dependsOnMethods = {"test"})
    public void testWithCookies() throws IOException {
        String url = this.host + this.bundle.getString("test.get.cookies");
        DefaultHttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(new HttpGet(url));

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
        }


    }

    @Test(dependsOnMethods = {"test"})
    public void postWithCookies() throws IOException {
      String url = host + bundle.getString("test.post.headers");
      HttpPost post = new HttpPost(url);
      post.setHeader("content-type","application/json");

      JSONObject jsonObject = new JSONObject();
      jsonObject.put("name","nick").put("age","18");

      StringEntity entity = new StringEntity(jsonObject.toString());
      post.setEntity(entity);

      DefaultHttpClient client = new DefaultHttpClient();
      client.setCookieStore(this.store);

      HttpResponse response = client.execute(post);

      String result = EntityUtils.toString(response.getEntity(),"utf-8");
      JSONObject resultObject = new JSONObject(result);

      Assert.assertEquals("success",resultObject.getString("message"));
      Assert.assertEquals("0",resultObject.getString("code"));

    }
}
