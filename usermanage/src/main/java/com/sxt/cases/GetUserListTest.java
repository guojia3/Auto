package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.GetUserList;
import com.sxt.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {
    @Test(dependsOnGroups = "loginSuccess",description = "获取所有性别为男的用户列表")
    public void getUserList() throws IOException {
        SqlSession session  = DataBaseUtil.getSqlSession();
        GetUserList userList = session.selectOne("getUserList",1);
        System.out.println(userList);
        System.out.println(TestConfig.getUserListUrl);

    }
}
