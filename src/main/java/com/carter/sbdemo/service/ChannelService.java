package com.carter.sbdemo.service;

import com.carter.sbdemo.model.TblChannelDTO;
import com.github.pagehelper.PageInfo;

public interface ChannelService {

    TblChannelDTO selectByComid(String comid);

    int addTblChannel(TblChannelDTO tblChannelDTO);

    PageInfo<TblChannelDTO> findAllUser(int pageNum, int pageSize);

    int deleteChannel(String comid);
}
