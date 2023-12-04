package com.pt.db.mapper;

import com.pt.db.model.SmsRole;
import com.pt.db.model.SmsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsRoleMapper {
    long countByExample(SmsRoleExample example);

    int deleteByExample(SmsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsRole record);

    int insertSelective(SmsRole record);

    List<SmsRole> selectByExample(SmsRoleExample example);

    SmsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsRole record, @Param("example") SmsRoleExample example);

    int updateByExample(@Param("record") SmsRole record, @Param("example") SmsRoleExample example);

    int updateByPrimaryKeySelective(SmsRole record);

    int updateByPrimaryKey(SmsRole record);
}