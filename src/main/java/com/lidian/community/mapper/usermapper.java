package com.lidian.community.mapper;

import com.lidian.community.model.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName usermapper
 * @Description TODO
 * @Date 2022/7/22 18:01
 */
@Repository
@Mapper
public interface usermapper {
    void insertuser(user user);

    user getuserbytoken(@Param("token") String token);
}
