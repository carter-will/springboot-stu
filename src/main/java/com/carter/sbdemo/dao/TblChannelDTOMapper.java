package com.carter.sbdemo.dao;

import com.carter.sbdemo.model.TblChannelDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TblChannelDTOMapper {
    int deleteByPrimaryKey(String id);

    int insert(TblChannelDTO record);

    int insertSelective(TblChannelDTO record);

    TblChannelDTO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TblChannelDTO record);

    int updateByPrimaryKey(TblChannelDTO record);

    TblChannelDTO selectByComid(String comid);

    List<TblChannelDTO> selectChannel();

    int deleteByComid(String comid);
}