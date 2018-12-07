package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.UpdateUserInfo;
import com.sxt.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginSuccess",description = "更新用户")
    public void getUserList() throws IOException {
        SqlSession session  = DataBaseUtil.getSqlSession();
        UpdateUserInfo updateUserInfo = session.selectOne("updateUserInfo",1);
        System.out.println(updateUserInfo);
        System.out.println(TestConfig.updateUserInfoUrl);

    }
}
