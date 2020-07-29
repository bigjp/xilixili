package com.qf.pojo;

import lombok.Data;

import java.util.List;

@Data
public class BaseResp {

    private List list;
    private Integer code;
    private String message;

}
