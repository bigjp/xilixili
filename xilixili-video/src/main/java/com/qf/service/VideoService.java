package com.qf.service;


import com.qf.UserCollections;
import com.qf.Video;
import com.qf.response.BaseResp;

import java.util.List;

public interface VideoService {

    BaseResp addgreat(Integer vid);

    BaseResp delgreat(Integer vid);

    BaseResp collections(Integer uid, Integer vid);

    BaseResp findAllgreat(Integer vid);

    BaseResp findMyCollectons(Integer uid);

    BaseResp follow(Integer uid, Integer fid, String date);

    BaseResp delfollow(Integer uid, Integer fid);

    BaseResp findfollow(Integer gid, Integer uid);

    BaseResp findFollowOne(Integer uid,Integer fid);

    BaseResp group(Integer gid, Integer uid,Integer fid);

    BaseResp creatgroup(String gname,Integer uid);

    BaseResp findGroupById(Integer uid);

    BaseResp delGroupByGid(Integer gid);

    BaseResp findAllFollow(Integer uid);

    BaseResp findFans(Integer uid);

    BaseResp delcollections(Integer uid, Integer vid);

    BaseResp addhistory(Integer uid, Integer vid);

    BaseResp findhistory(Integer uid);

    BaseResp findvideo(Integer vid);

    BaseResp findUpUser(Integer vid);

    UserCollections findUserCollection(Integer uid, Integer vid);

    BaseResp findAllCollection(Integer vid);

    BaseResp findVideoByType(String type,Integer currentpage,Integer size);

    BaseResp paihangbang(String type,Integer begin,Integer size);

    BaseResp findAllByType(String type);

    BaseResp paihangbangAll(String type);
}
