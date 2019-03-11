package com.carter.sbdemo.dao;

import com.carter.sbdemo.model.TblChannelConfigDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TblChannelConfigDTOMapper {
    int deleteByPrimaryKey(String slsKey);

    int insert(TblChannelConfigDTO record);

    int insertSelective(TblChannelConfigDTO record);

    TblChannelConfigDTO selectByPrimaryKey(String slsKey);

    int updateByPrimaryKeySelective(TblChannelConfigDTO record);

    int updateByPrimaryKey(TblChannelConfigDTO record);

    int deleteByUnionKey(@Param("comid") String comid,@Param("productNo") String productNo,@Param("chainCde") String chainCde);
}