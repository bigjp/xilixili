package com.qf.client;

import com.qf.pojo.BaseResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xilixili-es")
public interface ClientFeign {

    @RequestMapping("/findvideo")
    public BaseResp findvideo(@RequestParam("type") String type, @RequestParam("currentpage") Integer currentpage, @RequestParam("size") Integer size);
}
