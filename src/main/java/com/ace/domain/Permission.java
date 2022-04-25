package com.ace.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Permission implements Serializable {
    private Long id;
    private Long userId;
    private String name;
    private String authority;
    private Date created;
    private Date updated;

}
