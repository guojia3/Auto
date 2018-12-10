package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.InterfaceName;
import com.sxt.model.Login;
import com.sxt.utils.DataBaseUtil;
import com.sxt.utils.UrlUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginSuccess",description = "preparation before test")
    public void beforeLoginTest(){
        TestConfig.loginUrl = UrlUtil.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = UrlUtil.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl = UrlUtil.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = UrlUtil.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = UrlUtil.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();


    }

    @Test(groups = "loginSuccess",description = "登陆成功接口测试")
    public  void  loginSuccess() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        Login login = sqlSession.selectOne("login",1);
        System.out.println(login.toString());
        System.out.println(TestConfig.loginUrl);

        String result = getTestResult(login);
        Assert.assertEquals(result,login.getExpected());
    }

    private String getTestResult(Login login) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);

        post.setHeader("content-type","application/json");

        JSONObject param = new JSONObject();
        param.put("username",login.getUsername());
        param.put("password",login.getPassword());

        post.setEntity(new StringEntity(param.toString(),"utf-8"));

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        TestConfig.cookieStore = TestConfig.defaultHttpClient.getCookieStore();
        return EntityUtils.toString(response.getEntity());
    }

    @Test
    public void loginFail() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        Login login =  session.selectOne("login",2);
        System.out.println(login.toString());

        String result = getTestResult(login);
        Assert.assertEquals(result,login.getExpected());

    }
}
