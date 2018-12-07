package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.AddUser;
import com.sxt.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginSuccess",description = "添加用户测试")
    public void addUser() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        AddUser addUser = session.selectOne("addUser",1);
        System.out.println(addUser);
        System.out.println(TestConfig.addUserUrl);
    }
}
