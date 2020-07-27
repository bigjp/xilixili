package com.qf;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Data
public class Video {
    private Integer vid;
    private String title;
    private String discription;
    private String type;
    private Date date;
    private Integer clicks;
    private String collections;
    private Integer great;
    private String videoPath;

}
