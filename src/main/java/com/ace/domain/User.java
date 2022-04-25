package com.ace.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Collection;


@Data
@Setter
@Getter
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private Date created;
    private Date updated;

    private List<GrantedAuthority> authorities;

}
