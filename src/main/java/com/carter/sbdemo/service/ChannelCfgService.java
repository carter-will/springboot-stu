package com.carter.sbdemo.service;

import com.carter.sbdemo.model.TblChannelConfigDTO;

public interface ChannelCfgService {

    int addChannelCfg(TblChannelConfigDTO tblChannelConfigDTO);

    int deleteChannelCfg(String comid,String productNo,String chainCde) throws Exception;

}
