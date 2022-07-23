package com.lidian.community.provider;

import com.alibaba.fastjson.JSON;
import com.lidian.community.DTO.accesstoken;
import com.lidian.community.DTO.githubuser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName Githubprovider
 * @Description TODO
 * @Date 2022/7/21 14:18
 */
@Component
public class Githubprovider {
    /**
     * 传过去的参数是 accesstoken 携带code，我们希望获取token
     * post请求只能通过json以字符串的形式传输
     * @param accesstoken
     * @return
     * @throws IOException
     */
    public String getaccesstoken(accesstoken accesstoken)   {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accesstoken));
        Request request = new Request.Builder()
//                url是我们需要调用的地址
                .url("https://github.com/login/oauth/access_token")
//                post 请求
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
//            System.out.println(string); //access_token=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&scope=&token_type=bearer
            String s = string.split("=")[1].split("&")[0];
//            System.out.println(s);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 传token 发送Get请求，获取user信息   github/user/token就是用户所有信息了
     * 用户所有信息格式是json，我们将其转换成user对象 返回
     */
    public githubuser getuserinfo(String token){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+token).header("Authorization","token "+token)
                    .build();
            try  {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                return JSON.parseObject(string,githubuser.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }


}
