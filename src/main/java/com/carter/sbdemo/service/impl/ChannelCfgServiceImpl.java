package com.carter.sbdemo.service.impl;


import com.carter.sbdemo.dao.TblChannelConfigDTOMapper;
import com.carter.sbdemo.model.TblChannelConfigDTO;
import com.carter.sbdemo.service.ChannelCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "channelCfgServiceImpl")
public class ChannelCfgServiceImpl implements ChannelCfgService{

    @Autowired
    private TblChannelConfigDTOMapper tblChannelConfigDTOMapper;


    @Override
    public int addChannelCfg(TblChannelConfigDTO tblChannelConfigDTO) {
        int code = tblChannelConfigDTOMapper.insertSelective(tblChannelConfigDTO);
        return code;
    }

    @Override
    @Transactional
    public int deleteChannelCfg(String comid, String productNo, String chainCde) throws Exception {
        int code = tblChannelConfigDTOMapper.deleteByUnionKey(comid,productNo,chainCde);
        return code;
    }

    /**
     * @Transactional  注解的使用    参考：
     * http://blog.didispace.com/springboottransactional/
     * http://www.cnblogs.com/caoyc/p/5632963.html
     */
}
