package com.ace.mapper;

import com.ace.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {
    //利用user_id搜索的对应permission list
    //注意 下面的sql中表名和 where 之间需要加一个空格，否则会粘粘在一起，导致sql语句错误
    @Select("select * from tb_permission" +
            " where user_id=#{userId}")
    public List<Permission> selectPermissionsByUserid(
            @Param("userId") Long userId);

}
