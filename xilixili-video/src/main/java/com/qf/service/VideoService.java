package com.qf.service;

import com.qf.pojo.BaseResp;

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
}
