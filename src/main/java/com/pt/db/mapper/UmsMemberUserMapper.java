package com.pt.db.mapper;

import com.pt.db.model.UmsMemberUser;
import com.pt.db.model.UmsMemberUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberUserMapper {
    long countByExample(UmsMemberUserExample example);

    int deleteByExample(UmsMemberUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberUser record);

    int insertSelective(UmsMemberUser record);

    List<UmsMemberUser> selectByExample(UmsMemberUserExample example);

    UmsMemberUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberUser record, @Param("example") UmsMemberUserExample example);

    int updateByExample(@Param("record") UmsMemberUser record, @Param("example") UmsMemberUserExample example);

    int updateByPrimaryKeySelective(UmsMemberUser record);

    int updateByPrimaryKey(UmsMemberUser record);
}