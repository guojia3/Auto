package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.AddUser;
import com.sxt.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class AddUserTest {

    @Test(dependsOnGroups = "loginSuccess",description = "添加用户测试")
    public void addUser() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        AddUser addUser = session.selectOne("addUser",1);
        System.out.println(addUser);
        System.out.println(TestConfig.addUserUrl);

        String result = getAddUserResult(addUser);
        Assert.assertEquals(result,addUser.getExpected());
    }

    private String getAddUserResult(AddUser addUser) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        post.setHeader("content-type","application/json");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        JSONObject param = new JSONObject();
        param.put("id",addUser.getUserId());
        param.put("username",addUser.getUsername());
        param.put("password",addUser.getPassword());
        param.put("age",addUser.getAge());
        param.put("sex",addUser.getSex());
        param.put("permission",addUser.getPermission());
        param.put("isDelete",addUser.getIsDelete());
        post.setEntity(new StringEntity(param.toString()));

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        return EntityUtils.toString(response.getEntity());
    }
}
