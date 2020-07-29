package com.qf.service.impl;

import com.qf.User;
import com.qf.Video;
import com.qf.dao.UserInfoDao;
import com.qf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public User selectUserById(Integer uid) {
        return userInfoDao.selectUserByUid(uid);
    }

    @Override
    public User updateUserInfo(User user) {
        return userInfoDao.updateUserInfo(user);
    }

    @Override
    public List<Video> selectVideosByUid(Integer uid) {
        return userInfoDao.selectVideosByUid(uid);
    }

    @Override
    public Video updateVideoInfo(Video video) {
        return userInfoDao.updateVideoInfo(video);
    }

    @Override
    public int deleteVideoByVid(Integer vid) {
        return userInfoDao.deleteVideoByVid(vid);
    }

    @Override
    public List<Video> selectCollectVideos(Integer uid) {
        return userInfoDao.selectCollectVideos(uid);
    }

    @Override
    public void deleteCollectVideos(Integer uid, Integer vid) {
        userInfoDao.deleteCollectVideos(uid,vid);
    }

    @Override
    public void insertCollections(Integer uid, Integer vid) {
        userInfoDao.insertCollections(uid,vid);
    }

    @Override
    public List<User> selectUserFans(Integer uid) {
        List<User> userList=new ArrayList<>();
       List<Integer> fansId= userInfoDao.selectUserFansId(uid);
        for (int i=0;i<fansId.size();i++) {
            if (fansId.get(i) != null) {
                User user = userInfoDao.selectUserByUid(fansId.get(i));
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public void cancelConcern(Integer fansId) {
        userInfoDao.cancelConcern(fansId);
    }


}
