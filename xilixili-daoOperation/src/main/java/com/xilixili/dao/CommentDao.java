package com.xilixili.dao;

import com.qf.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentDao {

    @Insert("insert into comment (lid,uid,uided,words,cdate) value (#{lid},#{uid},#{uided},#{words},#{cdate}")
    int addComment(Comment comment);
}
