package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.GetUserList;
import com.sxt.model.User;
import com.sxt.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetUserListTest {
    @Test(dependsOnGroups = "loginSuccess",description = "获取所有性别为男的用户列表")
    public void getUserList() throws IOException {
        SqlSession session  = DataBaseUtil.getSqlSession();
        GetUserList userList = session.selectOne("getUserList",1);
        System.out.println(userList);
        System.out.println(TestConfig.getUserListUrl);

        JSONArray result = getResult(userList);

        List<User> users = session.selectList("userList",userList);
        JSONArray array = new JSONArray(users);

        Assert.assertEquals(result.length(),array.length());
        for(int i = 0;i < array.length();i ++){
            JSONObject userExpect = (JSONObject) result.get(i);
            JSONObject userActual = (JSONObject) array.get(i);
            Assert.assertEquals(userActual.toString(),userExpect.toString());
        }

    }

    private JSONArray getResult(GetUserList userList) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        post.setHeader("content-type","application/json");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        JSONObject param = new JSONObject();
        param.put("username",userList.getUsername());
        param.put("sex",userList.getSex());
        post.setEntity(new StringEntity(param.toString(),"utf-8"));

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        return new JSONArray(EntityUtils.toString(response.getEntity(),"utf-8"));
    }
}
