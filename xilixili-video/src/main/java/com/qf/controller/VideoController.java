package com.qf.controller;


import com.qf.User;
import com.qf.UserCollections;
import com.qf.Video;
import com.qf.response.BaseResp;
import com.qf.service.VideoService;
import com.qf.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class VideoController {

    private Integer count=0;

    private Integer collections=0;

    private Integer one=0;

    private Integer video=0;

    @Autowired
    private VideoService videoService;

    @Autowired
    private RedisUtils redisUtils;


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
        //查询此视频有没有已经被收藏
        UserCollections video =videoService.findUserCollection(uid,vid);
        if(video==null){
            baseResp= videoService.collections(uid, vid);
        }else{
            baseResp.setMessage("已收藏");
            baseResp.setCode(0);
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

    //根据id查询视频
    @RequestMapping(value = "/findvideo",method = RequestMethod.GET)
    public BaseResp findvideo(Integer vid){
        BaseResp baseResp=videoService.findvideo(vid);
        return baseResp;
    }

    //查询up
    @RequestMapping(value = "findUpUser",method = RequestMethod.GET)
    public BaseResp findUpUser(Integer vid){
        BaseResp baseResp=videoService.findUpUser(vid);
        return  baseResp;
    }

    //查询收藏量
    @RequestMapping(value = "/findAllCollection",method = RequestMethod.GET)
    public BaseResp findAllCollection(Integer vid){
        BaseResp baseResp=videoService.findAllCollection(vid);
        return baseResp;
    }

    //分类查询
    @RequestMapping(value = "/findVideoByType",method = RequestMethod.GET)
    public BaseResp findVideoByType(String type,Integer currentpage,Integer size){
        Integer begin=(currentpage-1)*size;
        BaseResp baseResp=videoService.findVideoByType(type,begin,size);
        return baseResp;
    }

    //排行榜
    @RequestMapping(value = "/paihangbang",method = RequestMethod.GET)
    public BaseResp paihangbang(String type,Integer currentpage,Integer size){
        Integer begin=(currentpage-1)*size;
        BaseResp baseResp=videoService.paihangbang(type,begin,size);
        return baseResp;
    }

    //根据类别查询
    @RequestMapping(value = "/findAllByType",method = RequestMethod.GET)
    public BaseResp findAllByType(String type){
        BaseResp baseResp=videoService.findAllByType(type);
        return  baseResp;
    }

    @RequestMapping(value = "/paihangbangAll",method = RequestMethod.GET)
    public BaseResp paihangbangAll(String type){
        BaseResp baseResp=videoService.paihangbangAll(type);
        return baseResp;
    }

    @RequestMapping(value="/isuser",method = RequestMethod.GET)
    public BaseResp isuser(){
//        User user = (User)redisUtils.get("user");
        BaseResp baseResp = new BaseResp();
        User user=new User();
        user.setUserimage("/static/img/4.jpg");
        user.setUid(1);
        List<User> users=new ArrayList<>();
        users.add(user);
        if(user==null){
            baseResp.setCode(0);
        }else{
            baseResp.setList(users);
            baseResp.setCode(1);
        }
      return baseResp;
    }

}
