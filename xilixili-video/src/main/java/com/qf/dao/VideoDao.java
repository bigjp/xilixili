package com.qf.dao;

import com.qf.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface VideoDao {

    Integer addgreat(@Param("vid")Integer vid);

    Integer delgreat(@Param("vid")Integer vid);

    Integer collections(@Param("uid")Integer uid,@Param("vid") Integer vid);

    List<Video> findAllgreat(@Param("vid")Integer vid);

    List<Video> findMyCollectons(@Param("uid") Integer uid);

    Integer follow(@Param("uid")Integer uid, @Param("fid")Integer fid, @Param("date")String date);

    Integer followgroup(@Param("fid")Integer fid,@Param("gid")Integer gid,@Param("uid")Integer uid);

    Integer delfollow(@Param("uid") Integer uid,@Param("fid") Integer fid);

    Integer delgroup(@Param("uid")Integer uid,@Param("fid") Integer fid);

    List<User> findAllFollow(@Param("uid") Integer uid);

    List<User> findfollow(@Param("gid") Integer gid,@Param("uid") Integer uid);

    User findFollowOne(@Param("uid") Integer uid,@Param("fid") Integer fid);

    Integer group(@Param("gid") Integer gid, @Param("uid") Integer uid,@Param("fid")Integer fid);

    Integer creatgroup(@Param("gname")String gname,@Param("uid")Integer uid);

    List<Group> findGroupById(@Param("uid") Integer uid);

    Integer delGroupByGid(@Param("gid") Integer gid);

    List<User> findFans(@Param("uid") Integer uid);

    Integer addfans(@Param("fid") Integer fid, @Param("uid") Integer uid);

    Integer updatecollections( @Param("vid") Integer vid);

    Integer delcollections(@Param("uid") Integer uid, @Param("vid") Integer vid);

    Integer addhistory(@Param("vid") Integer vid, @Param("date") Date date, @Param("uid") Integer uid);

    List<History> findhistory(@Param("uid") Integer uid);

    Integer updhistory(@Param("uid")Integer uid,@Param("vid")Integer vid,@Param("date") Date date);


}
