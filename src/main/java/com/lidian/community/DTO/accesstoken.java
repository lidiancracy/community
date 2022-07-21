package com.lidian.community.DTO;

import lombok.Data;

/**
 * @ClassName accesstoken
 * @Description 传过去code，希望获取token，有5个传过去的参数
 * 参数太多，我们一般会将它封装成对象
 * @Date 2022/7/21 14:16
 */
@Data
public class accesstoken {
    private String client_id;
    private String client_secret;
    private String code;
    private  String redirect_uri;
    private String state;
}
