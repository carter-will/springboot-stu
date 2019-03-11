package com.carter.sbdemo.controller;


import com.carter.sbdemo.model.TblChannelDTO;
import com.carter.sbdemo.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    @Qualifier("channelServiceImpl")
    private ChannelService channelService;


    @RequestMapping(value = "/getChannelMsg",method = RequestMethod.GET)
    public String selectChannelMsgByComid(@RequestParam(value = "comid", required = true) String comid){
        TblChannelDTO tbl = channelService.selectByComid(comid);

        System.out.print(tbl.getName());

        return tbl.getName();
    }

    @ResponseBody
    @PostMapping("/add")
    public int addUser(TblChannelDTO tblChannelDTO){
        return channelService.addTblChannel(tblChannelDTO);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return channelService.findAllUser(pageNum,pageSize);
    }


    @ResponseBody
    @GetMapping("/del")
    public int addUser(String comid){
        return channelService.deleteChannel(comid);
    }

}
