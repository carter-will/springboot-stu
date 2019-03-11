package com.carter.sbdemo.controller;


import com.carter.sbdemo.model.TblChannelConfigDTO;
import com.carter.sbdemo.service.ChannelCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channelcfg")
public class ChannelCfgController {

    @Autowired
    @Qualifier("channelCfgServiceImpl")
    private ChannelCfgService channelCfgService;


    @ResponseBody
    @PostMapping("/add")
    public int addCfg(TblChannelConfigDTO tblChannelConfigDTO){

        return channelCfgService.addChannelCfg(tblChannelConfigDTO);
    }

    @ResponseBody
    @PostMapping("/del")
    public int addCfg(String comid, String productNo, String chainCde) throws Exception {

        return channelCfgService.deleteChannelCfg(comid,productNo,chainCde);
    }

}
