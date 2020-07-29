package com.qf.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Video {

    private Integer vid;
    private String title;
    private String description;
    private String type;
    private Date date;
    private Integer clicks;
    private String collentions;
    private Integer great;
    private String videopath;

}
