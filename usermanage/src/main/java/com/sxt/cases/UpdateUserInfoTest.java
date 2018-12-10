package com.sxt.cases;

import com.sxt.config.TestConfig;
import com.sxt.model.UpdateUserInfo;
import com.sxt.model.User;
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

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginSuccess",description = "更新用户")
    public void getUserList() throws IOException {
        SqlSession session  = DataBaseUtil.getSqlSession();
        UpdateUserInfo updateUserInfo = session.selectOne("updateUserInfo",1);
        System.out.println(updateUserInfo);
        System.out.println(TestConfig.updateUserInfoUrl);

        String result = getResult(updateUserInfo);

        Assert.assertEquals(result,updateUserInfo.getExpected());
    }

    private String getResult(UpdateUserInfo updateUserInfo) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        post.setHeader("content-type","application/json");
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfo.getUserId());
        param.put("username",updateUserInfo.getUsername());
        param.put("sex",updateUserInfo.getSex());
        param.put("age",updateUserInfo.getAge());
        param.put("isDelete",updateUserInfo.getIsDelete());
        param.put("permission",updateUserInfo.getPermission());
        post.setEntity(new StringEntity(param.toString(),"utf-8"));
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        return EntityUtils.toString(response.getEntity(),"utf-8");
    }
}
