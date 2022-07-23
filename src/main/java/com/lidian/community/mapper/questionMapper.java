package com.lidian.community.mapper;

import com.lidian.community.model.question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName questionMapper
 * @Description TODO
 * @Date 2022/7/23 12:14
 */
@Repository
@Mapper
public interface questionMapper {
    void insertq(question question);
}
