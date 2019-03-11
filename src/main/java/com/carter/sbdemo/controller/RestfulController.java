package com.carter.sbdemo.controller;


import com.carter.sbdemo.model.TblChannelDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/channelcfg")
public class RestfulController {


    @ResponseBody
    @GetMapping("/getObject")
    public TblChannelDTO addCfg(){
       TblChannelDTO channel = new TblChannelDTO();
       channel.setId("1");
       channel.setComid("EC11111111");
       channel.setName("ABCD渠道");
       return channel;
    }

    @ResponseBody
    @GetMapping("/getStr")
    public String queryCfg(){
       String channel = "EC18000000";
       return channel;
    }




}
