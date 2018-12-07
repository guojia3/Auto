package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.GetUserInfo;
import com.sxt.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginSuccess",description = "获取用户信息")
    public void getUserInfo() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        GetUserInfo userInfo = session.selectOne("getUserInfo",1);
        System.out.println(userInfo);
        System.out.println(TestConfig.getUserInfoUrl);

    }
}
