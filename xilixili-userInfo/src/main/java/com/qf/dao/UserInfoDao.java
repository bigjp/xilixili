package com.qf.dao;

import com.qf.User;
import com.qf.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoDao {
    User selectUserByUid(Integer uid);

    User updateUserInfo(User user);

    List<Video> selectVideosByUid(Integer uid);

    Video updateVideoInfo(Video video);

    int deleteVideoByVid(Integer vid);

    List<Video> selectCollectVideos(Integer uid);


    void deleteCollectVideos(Integer uid, Integer vid);

    void insertCollections(Integer uid, Integer vid);


    List<Integer> selectUserFansId(Integer uid);

}
