package com.qf.response;

import lombok.Data;

import java.util.List;

@Data
public class BaseResp {
    private List list;
    private String message;
    private Integer code;
}
