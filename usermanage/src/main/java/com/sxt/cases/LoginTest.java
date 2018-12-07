package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.InterfaceName;
import com.sxt.model.Login;
import com.sxt.utils.DataBaseUtil;
import com.sxt.utils.UrlUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
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
    }

    @Test
    public void loginFail() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        Login login =  session.selectOne("login",2);
        System.out.println(login.toString());

    }
}
