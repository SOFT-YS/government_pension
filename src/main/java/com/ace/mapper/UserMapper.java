package com.ace.mapper;

import com.ace.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    //利用username读取user对象
    @Select("select * from tb_user " +
            "where username=#{username}")
    public User selectUserByUsername(
            @Param("username") String username);


}
