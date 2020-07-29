package com.qf.controller;

import com.qf.client.ClientFeign;
import com.qf.pojo.BaseResp;
import com.qf.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    ClientFeign clientFeign;

    @RequestMapping("/findvideos")
    public BaseResp findvideos(String type,Integer currentpage,Integer size){
        return clientFeign.findvideo(type,currentpage,size);
    }

}
