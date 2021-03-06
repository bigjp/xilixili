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
    private Integer phonr;
    private String email;
    private Integer vipLevel;
    private Integer vipDeadDate;
    private Integer great;
    private String userimage;
    private String password;

}
