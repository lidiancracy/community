package com.lidian.community.model;

import lombok.Data;

/**
 * @ClassName user
 * @Description DAO
 * @Date 2022/7/22 18:03
 */
@Data
public class user {
    private Integer id;
    private  String name;
    private String accountid;
    private String token;
    private Long createtime;
    private Long modifiedtime;
}
