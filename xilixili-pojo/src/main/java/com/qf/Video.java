package com.qf;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Video {
    private Integer vid;
    private String title;
    private String description;
    private String type;
    private String date;
    private Integer clicks;
    private Integer collections;
    private Integer great;
    private String videopath;
    private String image;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        Long aLong = Long.valueOf(date);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date(aLong));
        this.date = format;
    }
}
