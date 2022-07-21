package com.lidian.community.DTO;

import lombok.Data;

/**
 * @ClassName githubuser
 * @Description 我们只想获取 授权用户的name id bio(描述)
 * @Date 2022/7/21 15:39
 */
@Data
public class githubuser {
    private String name;
    private String id;
    private String bio;
}
