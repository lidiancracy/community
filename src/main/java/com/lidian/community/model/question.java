package com.lidian.community.model;

import lombok.Data;

/**
 * @ClassName question
 * @Description TODO
 * @Date 2022/7/23 12:35
 */
@Data
public class question {
    private Integer id;
    private String title;
    private String desp;
    private String tag;
    private Long createtime;
    private Long modifytime;
    private Integer creator;
    private Integer viewcount;
    private Integer commentcount;
    private Integer likecount;
}
