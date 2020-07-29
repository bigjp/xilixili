package com.qf.service;

import com.qf.User;
import com.qf.Video;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserInfoService {

    User selectUserById(Integer uid);

    User updateUserInfo(User user);

    List<Video> selectVideosByUid(Integer uid);

    Video updateVideoInfo(Video video);

    int deleteVideoByVid(Integer vid);

    List<Video> selectCollectVideos(Integer uid);


    void deleteCollectVideos(Integer uid, Integer vid);

    void insertCollections(Integer uid,Integer vid);

    List<User> selectUserFans(Integer uid);
}
