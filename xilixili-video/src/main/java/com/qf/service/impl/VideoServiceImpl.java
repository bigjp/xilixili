package com.qf.service.impl;

import com.qf.Group;
import com.qf.History;
import com.qf.User;
import com.qf.Video;
import com.qf.dao.VideoDao;
import com.qf.response.BaseResp;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;



    @Override
    public BaseResp addgreat(Integer vid) {
        Integer code = videoDao.addgreat(vid);
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(code);
        baseResp.setMessage("点击点赞成功");
        return baseResp;
    }

    @Override
    public BaseResp delgreat(Integer vid) {
        Integer code = videoDao.delgreat(vid);
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(code);
        baseResp.setMessage("取消点赞成功");
        return baseResp;
    }

    @Override
    public BaseResp collections(Integer uid, Integer vid) {
        Integer code=videoDao.collections(uid,vid);
        videoDao.updatecollections(vid);
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(code);
        baseResp.setMessage("点击收藏成功");
        return baseResp;
    }

    @Override
    public BaseResp delcollections(Integer uid, Integer vid) {
        Integer code=videoDao.delcollections(uid,vid);
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(code);
        baseResp.setMessage("删除成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllgreat(Integer vid) {
        List<Video> videos=videoDao.findAllgreat(vid);
        BaseResp baseResp=new BaseResp();
        baseResp.setList(videos);
        baseResp.setCode(1);
        baseResp.setMessage("总点赞数量查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findMyCollectons(Integer uid) {
        List<Video> videos=videoDao.findMyCollectons(uid);
        BaseResp baseResp=new BaseResp();
        baseResp.setList(videos);
        baseResp.setCode(1);
        baseResp.setMessage("我的收藏视频查询成功");
        return baseResp;
    }

    @Override
    public BaseResp follow(Integer uid, Integer fid, String date) {
        //查询该用户是否已经关注过
        User user = videoDao.findFollowOne(uid, fid);
        BaseResp baseResp=new BaseResp();
        if(user==null){
            Integer code  = videoDao.follow(uid, fid, date);
            Integer code1 = videoDao.addfollow(uid,fid);
            //添加到默认分组
            videoDao.followgroup(fid, 0,uid);
            //添加到被关注人的粉丝列表
            videoDao.addfans(fid,uid);
            baseResp.setCode(code);
            baseResp.setMessage("关注成功");
        }else{
            baseResp.setCode(0);
            baseResp.setMessage("用户已关注成功");
        }
        return baseResp;
    }

    @Override
    public BaseResp delfollow(Integer uid, Integer fid) {
        Integer code=videoDao.delfollow(uid,fid);
        videoDao.delgroup(uid,fid);
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(code);
        baseResp.setMessage("取消关注成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllFollow(Integer uid) {
        List<User> users=videoDao.findAllFollow(uid);
        BaseResp baseResp=new BaseResp();
        baseResp.setList(users);
        baseResp.setCode(1);
        baseResp.setMessage("查询所有关注成功");
        return baseResp;
    }

    @Override
    public BaseResp findfollow(Integer gid, Integer uid) {
        //根据用户分组关注查询
        List<User> users=videoDao.findfollow(gid,uid);
        BaseResp baseResp=new BaseResp();
        baseResp.setList(users);
        baseResp.setCode(1);
        baseResp.setMessage("查询我的关注成功");
        return baseResp;
    }

    @Override
    public BaseResp findFollowOne(Integer uid, Integer fid) {
        User user = videoDao.findFollowOne(uid, fid);
        BaseResp baseResp=new BaseResp();
        if(user!=null){
            baseResp.setCode(1);
            baseResp.setMessage("该用户已关注");
        }else {
            baseResp.setCode(0);
            baseResp.setMessage("未关注");
        }
        return baseResp;
    }

    @Override
    public BaseResp group(Integer gid, Integer uid,Integer fid) {
        Integer code = videoDao.group(gid, uid,fid);
        Group group=videoDao.findgroupuser(gid,fid);
        if(group==null){
            Integer code1=videoDao.addgroup(gid,fid);
        }
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(code);
        baseResp.setMessage("分组成功");
        return baseResp;
    }

    @Override
    public BaseResp creatgroup(String gname,Integer uid) {
        List<Group> groups=videoDao.findGroupByGname(gname,uid);
        BaseResp baseResp = new BaseResp();
        if(groups!=null && groups.size()==1){
            baseResp.setCode(0);
            baseResp.setMessage("分组已存在");
        }else{
            Integer code=videoDao.creatgroup(gname,uid);
            baseResp.setCode(code);
            baseResp.setMessage("创建分组成功");
        }
        return baseResp;
    }

    @Override
    public BaseResp findGroupById(Integer uid) {
        List<Group> groups=videoDao.findGroupById(uid);
        BaseResp baseResp=new BaseResp();
        baseResp.setList(groups);
        baseResp.setCode(1);
        baseResp.setMessage("查询用户分组");
        return baseResp;
    }

    @Override
    public BaseResp delGroupByGid(Integer gid) {
        Integer code=videoDao.delGroupByGid(gid);
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(code);
        baseResp.setMessage("删除分组成功");
        return null;
    }

    @Override
    public BaseResp findFans(Integer uid) {
        List<User> fans = videoDao.findFans(uid);
        BaseResp baseResp=new BaseResp();
        baseResp.setList(fans);
        baseResp.setCode(1);
        baseResp.setMessage("查询粉丝列表成功");
        return baseResp;
    }

    @Override
    public BaseResp addhistory(Integer uid, Integer vid) {
        Date date = new Date();
        BaseResp baseResp = new BaseResp();
        List<History> historys = videoDao.findhistory(uid);
        if(historys.size()!=0){
            for(History history:historys){
                Integer vid1 = history.getVid();
                //如果之前已经看过此视频，再次观看更新观看列表观看时间
                if(vid1==vid){
                    videoDao.updhistory(uid,vid,date);
                    baseResp.setCode(1);
                    baseResp.setMessage("更新历史观看时间成功");
                    return null;
                }
            }
            //没有观看则添加新的记录
            Integer code=videoDao.addhistory(vid,date,uid);
            History history1=videoDao.findhistoryuser(uid,vid);
            Integer hid=history1.getHid();
            Integer code1=videoDao.addhistoryuser(uid,hid);
            baseResp.setCode(code);
            baseResp.setMessage("加入历史观看列表成功");
        }else{
            Integer code=videoDao.addhistory(vid,date,uid);
            History history1=videoDao.findhistoryuser(uid,vid);
            Integer hid=history1.getHid();
            Integer code1=videoDao.addhistoryuser(uid,hid);
            baseResp.setCode(code);
            baseResp.setMessage("加入历史观看列表成功");
        }
       return baseResp;
    }

    @Override
    public BaseResp findhistory(Integer uid) {
        List<History> historys = videoDao.findhistory(uid);
        BaseResp baseResp=new BaseResp();
        baseResp.setCode(1);
        baseResp.setList(historys);
        baseResp.setMessage("历史观看列表查询成功");
        return baseResp;
    }
}
