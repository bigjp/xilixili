package com.qf.controller;

import com.qf.service.UserInfoService;
import com.qf.utils.UploadUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UploadUtils uploadUtils;
    //根据uid查user
    @RequestMapping("/selectUserByUid")
    public User selectUserById(Integer uid){
        return userInfoService.selectUserById(uid);
    }

    //更新用户信息
    @RequestMapping("/updateUserInfo")
    public User updateUserInfo(@RequestBody User user){
        return userInfoService.updateUserInfo(user);
    }
    //上传文件
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile){
        return uploadUtils.upload(multipartFile);
    }
    //查询用户上传视频
    @RequestMapping("/selectVideosByUid")
    public List<Video> selectVideosByUid(Integer uid){
        return userInfoService.selectVideosByUid(uid);
    }

    //更新用户上传视频信息
    @RequestMapping("/updateVideoInfo")
    public Video updateVideoInfo(Video video){
        return userInfoService.updateVideoInfo(video);
    }

    //删除用户上传视频
    @RequestMapping("/deleteVideoByVid")
    public String deleteVideoByVid(Integer vid){
         userInfoService.deleteVideoByVid(vid);
         return "删除成功";
    }
    //查询用户收藏视频
    @RequestMapping("/selectCollectVideos")
    public List<Video> selectCollectVideos(Integer uid){
        return userInfoService.selectCollectVideos(uid);
    }
    //删除用户收藏视频
    @RequestMapping("/deleteCollectVideos")
    public String deleteCollectVideos(@Param("uid")Integer uid,@Param("vid") Integer vid){
         userInfoService.deleteCollectVideos(uid,vid);
        return "删除成功";
    }
    //用户收藏视频
    @RequestMapping("/insertCollections")
    public String insertCollections(@Param("uid")Integer uid,@Param("vid") Integer vid){
        userInfoService.insertCollections(uid,vid);
        return "收藏成功";
    }
    //用户粉丝列表
    @RequestMapping("/selectUserFans")
    public List<User> selectUserFans(Integer uid){
       return userInfoService.selectUserFans(uid);
    }
    //用户取消关注
    @RequestMapping("/cancelConcern")
    public String CancelConcern (Integer fansId){
        userInfoService.cancelConcern(fansId);
        return "取消关注成功";
    }

}
