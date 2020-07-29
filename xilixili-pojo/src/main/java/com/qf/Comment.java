package com.qf;

import lombok.Data;

@Data
public class Comment {

    private Integer cid;
    private Integer lid; //楼id
    private Integer uid;//评论人id
    private Integer uided;//被评论人id
    private String words;//评论内容
    private String cdate;//发表评论时间
}
