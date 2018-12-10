package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.GetUserInfo;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginSuccess",description = "获取用户信息")
    public void getUserInfo() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        GetUserInfo userInfo = session.selectOne("getUserInfo",1);
        System.out.println(userInfo);
        System.out.println(TestConfig.getUserInfoUrl);

        JSONArray result = getResult(userInfo);

        User user = session.selectOne("userInfo",userInfo.getUserId());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        JSONArray array = new JSONArray(userList);
        JSONArray array1 = new JSONArray(result.getString(0));

        Assert.assertEquals(array1.toString(),array.toString());


    }

    private JSONArray getResult(GetUserInfo userInfo) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        post.setHeader("content-type","application/json");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        JSONObject param = new JSONObject();
        param.put("id",userInfo.getUserId());
        post.setEntity(new StringEntity(param.toString(),"utf-8"));

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        List result = Arrays.asList(EntityUtils.toString(response.getEntity(),"utf-8"));
        return new JSONArray(result);
    }
}
