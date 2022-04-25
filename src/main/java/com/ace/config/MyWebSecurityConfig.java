package com.ace.config;


import com.ace.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    //明文加密器,只需要在内存中有这个管理对象,如果不添加，从前端登录时会抛出异常
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    //构造一个内存框架对象，获取数据库中的数据
    @Bean
    public UserDetailsService myUserDetailsService(){
        return new UserService();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //获取数据库中的数据
        auth.userDetailsService(myUserDetailsService());

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()// 开启权限控制,通过ANT规范，自定义逻辑

                //当访问/admin/write时必须有write权限
                .antMatchers("/admin/write").hasAuthority("write")
                .antMatchers("/admin/update").hasAuthority("update")
                .antMatchers("/admin/delete").hasAuthority("delete")
                .antMatchers("/user/read").hasAuthority("read")
                .anyRequest()//任意请求
                .authenticated();//必须经过认证 只要登陆就能访问
        http.formLogin();//开启表单认证
        http.httpBasic();//开启http 基本认证
    }


}
