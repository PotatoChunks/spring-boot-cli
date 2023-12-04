package com.pt.db.mapper;

import com.pt.db.model.UmsMemberUserInfo;
import com.pt.db.model.UmsMemberUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberUserInfoMapper {
    long countByExample(UmsMemberUserInfoExample example);

    int deleteByExample(UmsMemberUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberUserInfo record);

    int insertSelective(UmsMemberUserInfo record);

    List<UmsMemberUserInfo> selectByExample(UmsMemberUserInfoExample example);

    UmsMemberUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberUserInfo record, @Param("example") UmsMemberUserInfoExample example);

    int updateByExample(@Param("record") UmsMemberUserInfo record, @Param("example") UmsMemberUserInfoExample example);

    int updateByPrimaryKeySelective(UmsMemberUserInfo record);

    int updateByPrimaryKey(UmsMemberUserInfo record);
}