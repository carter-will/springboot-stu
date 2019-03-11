package com.carter.sbdemo.service.impl;

import com.carter.sbdemo.dao.TblChannelDTOMapper;
import com.carter.sbdemo.model.TblChannelDTO;
import com.carter.sbdemo.service.ChannelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "channelServiceImpl")
public class ChannelServiceImpl implements ChannelService {

    private static final Map<String, String> DATABASES = new HashMap<>();

    static{
        DATABASES.put("EC18021146","1");
        DATABASES.put("EC18021147","2");
        DATABASES.put("EC18021148","3");
    }



    @Autowired
    private TblChannelDTOMapper tblChannelDTOMapper;

//    @Cacheable(value = "tblChannel", key = "#comid")               //若要使用缓存，打开注释
    @Override
    public TblChannelDTO selectByComid(String comid) {
        return tblChannelDTOMapper.selectByComid(comid);
    }

//    @CachePut(value = "tblChannel", key = "#tblChannelDTO.comid")                    //若要使用缓存，打开注释
    @Override
    public int addTblChannel(TblChannelDTO tblChannelDTO) {
        int result = tblChannelDTOMapper.insertSelective(tblChannelDTO);
        return result;
    }


    @Override
    public PageInfo<TblChannelDTO> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<TblChannelDTO> userDomains = tblChannelDTOMapper.selectChannel();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

//    @CacheEvict(value = "tblChannel", key = "#comid")                  //若要使用缓存，打开注释
    @Override
    public int deleteChannel(String comid) {
        int result = tblChannelDTOMapper.deleteByComid(comid);
        return result;
    }


}
