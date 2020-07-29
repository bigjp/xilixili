package com.qf.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer uid;
    private String email;
    private String password;
    private String nickkname;
    private Character sex;
    private Date birthday;
    private String sign;
    private Long phone;
    private Integer vip_level;
    private Date vip_deaddate;
    private Integer great;
    private String image;
}
