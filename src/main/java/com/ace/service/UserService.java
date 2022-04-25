package com.ace.service;


import com.ace.domain.Permission;
import com.ace.mapper.PermissionMapper;
import com.ace.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//username参数,是在登陆时,用户传递的表单数据username
        //主要读取数据库3个值 username password authorities
        //这里自定义的User和security中的User重名，且那个包名很长所以这里写自定义User的包路径
        com.ace.domain.User user = userMapper.selectUserByUsername(username);
        Long userId=user.getId();
        List<Permission> permissions = permissionMapper.selectPermissionsByUserid(userId);
        //为了返回一个UserDetails 使用User
        List<GrantedAuthority> authorities=new ArrayList<>();
        for (Permission p:permissions) {
            //循环一次,就拿到了表格权限,使用authority字段
            String authorityName = p.getAuthority();
            GrantedAuthority authority=
                    new SimpleGrantedAuthority(authorityName);
            authorities.add(authority);
        }

        //这里的User 是这个包下的 org.springframework.security.core.userdetails.User;
        User userDetails=new User(
                user.getUsername(),
                user.getPassword(),
                authorities);
        user.setAuthorities(authorities);
        return userDetails;
    }


}
