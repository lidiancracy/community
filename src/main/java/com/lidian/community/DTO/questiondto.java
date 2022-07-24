package com.lidian.community.DTO;

import com.lidian.community.model.user;
import lombok.Data;

/**
 * @ClassName questiondto
 * @Description 多了一个user对象，question和user表联动
 * @Date 2022/7/24 13:00
 */
@Data
public class questiondto {
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
    private user user;
}
