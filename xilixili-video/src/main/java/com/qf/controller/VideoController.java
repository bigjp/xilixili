package com.qf.controller;


import com.qf.response.BaseResp;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class VideoController {

    private Integer count=0;

    private Integer collections=0;

    private Integer one=0;

    private Integer video=0;

    @Autowired
    private VideoService videoService;


    //查询视频点赞量
    @RequestMapping(value = "/findAllgreat",method = RequestMethod.GET)
    public BaseResp findAllgreat(Integer vid){
        BaseResp baseResp=videoService.findAllgreat(vid);
        return baseResp;
    }

    //点击点赞，点赞数量加一，再次点击，取消点赞，点赞数量减一
    @RequestMapping(value = "/great",method = RequestMethod.GET)
    public BaseResp great(Integer vid){
        if(count==0){
            BaseResp baseResp = videoService.addgreat(vid);
            count++;
            System.out.println(count);
            return baseResp;
        }else if(count==1){
            System.out.println(count);
            BaseResp baseResp =videoService.delgreat(vid);
            count--;
            return baseResp;
        }
        return null;
    }

    //查询我的收藏
    @RequestMapping(value = "/mycollections",method = RequestMethod.GET)
    public BaseResp mycollections(Integer uid){
        BaseResp baseResp=videoService.findMyCollectons(uid);
        return baseResp;
    }

    //点击收藏，添加视频到我的收藏,添加过收藏的不能再次添加,并改变视频的收藏量collections
    @RequestMapping(value = "/collections",method = RequestMethod.GET)
    public BaseResp collections(Integer uid,Integer vid){
        BaseResp baseResp=new BaseResp();
        if(collections==0){
            if(uid!=one || vid!=video){
                baseResp= videoService.collections(uid, vid);
                collections++;
                one=uid;
                video=vid;
            }else{
                baseResp.setCode(0);
                baseResp.setMessage("已收藏");
            }
        }else{
            if(uid!=one || vid!=video){
                baseResp= videoService.collections(uid, vid);
                one=uid;
                video=vid;
                collections--;
            }else{
                baseResp.setCode(0);
                baseResp.setMessage("已收藏");
            }
        }
       return baseResp;
    }

    //删除我的收藏
    @RequestMapping(value = "/delcollections",method = RequestMethod.GET)
    public BaseResp delcollections(Integer uid,Integer vid){
        BaseResp baseResp=videoService.delcollections(uid,vid);
        return baseResp;
    }

    //点击关注用户，添加用户到关注列表,并将自己添加到被关注用户的粉丝列表
    @RequestMapping(value = "/addfollow",method = RequestMethod.GET)
    public BaseResp follow(Integer uid,Integer fid){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss").format(new Date());
        BaseResp baseResp=videoService.follow(uid,fid,date);
        return baseResp;
    }

    //查询该用户是否是已关注，如果是已关注，返回1，前端显示该用户已关注，返回0，前端显示未关注
    @RequestMapping(value = "/findfollowone",method = RequestMethod.GET)
    public BaseResp findfollowone(Integer uid,Integer fid){
        BaseResp baseResp = videoService.findFollowOne(uid, fid);
        return baseResp;
    }

    //点击取消关注，讲该用户踢出关注列表
    @RequestMapping(value = "delfollow",method = RequestMethod.GET)
    public BaseResp delfollow(Integer uid,Integer fid){
        BaseResp baseResp=videoService.delfollow(uid,fid);
        return  baseResp;
    }

    //查询所有关注的用户
    @RequestMapping(value = "/findAllFollow",method =RequestMethod.GET )
    public BaseResp findAllFollow(Integer uid){
        BaseResp baseResp=videoService.findAllFollow(uid);
        return baseResp;
    }

    //查询我的分组关注列表
    @RequestMapping(value = "findfollow",method = RequestMethod.GET)
    public BaseResp findfollow(Integer gid,Integer uid){
        BaseResp baseResp=videoService.findfollow(gid,uid);
        return baseResp;
    }

    //用户点击新建分组，添加一个分组
    @RequestMapping(value="/creatgroup",method = RequestMethod.GET)
    public BaseResp creatgroup(String gname,Integer uid){
        BaseResp baseResp=videoService.creatgroup(gname,uid);
        return baseResp;
    }

    //未分组的关注用户为默认分组0，选择将用户分组传入一个分组id，将关注用户重新分组
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    public BaseResp group(Integer gid,Integer uid,Integer fid){
        BaseResp baseResp=videoService.group(gid,uid,fid);
        return baseResp;
    }

    //查询用户所创建的所有分组，并返回前端，前端动态展示分组
    @RequestMapping(value = "/findGroupById",method = RequestMethod.GET)
    public BaseResp findGroupById(Integer uid){
        BaseResp baseResp=videoService.findGroupById(uid);
        return baseResp;
    }

    //删除用户创建的分组
    @RequestMapping(value = "/delGroupByGid",method = RequestMethod.GET)
    public BaseResp delGroupByGid(Integer gid){
        BaseResp baseResp=videoService.delGroupByGid(gid);
        return baseResp;
    }

    //查看粉丝列表
    @RequestMapping(value = "/findFans",method = RequestMethod.GET)
    public BaseResp findFans(Integer uid){
        BaseResp baseResp=videoService.findFans(uid);
        return baseResp;
    }

    //添加观看历史
    @RequestMapping(value = "/addhistory",method = RequestMethod.GET)
    public BaseResp addhistory(Integer uid,Integer vid){
        BaseResp baseResp=videoService.addhistory(uid,vid);
        return baseResp;
    }

    //查询观看历史,根据时间倒序排序
    @RequestMapping(value = "/findhistory",method = RequestMethod.GET)
    public BaseResp findhistory(Integer uid){
        BaseResp baseResp=videoService.findhistory(uid);
        return baseResp;
    }

}
