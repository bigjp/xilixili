package com.qf;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer uid;
    private String nickname;
    private String sex;
    private Date birthday;
    private String sign;
    private Integer phone;
    private String email;
    private Integer vipLevel;
    private Date vipDeadDate;
    private Integer great;
    private String image;
    private String password;

}
