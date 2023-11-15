package com.pt.db.mapper;

import com.pt.db.model.SmsRoleResourceRelation;
import com.pt.db.model.SmsRoleResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsRoleResourceRelationMapper {
    long countByExample(SmsRoleResourceRelationExample example);

    int deleteByExample(SmsRoleResourceRelationExample example);

    int insert(SmsRoleResourceRelation record);

    int insertSelective(SmsRoleResourceRelation record);

    List<SmsRoleResourceRelation> selectByExample(SmsRoleResourceRelationExample example);

    int updateByExampleSelective(@Param("record") SmsRoleResourceRelation record, @Param("example") SmsRoleResourceRelationExample example);

    int updateByExample(@Param("record") SmsRoleResourceRelation record, @Param("example") SmsRoleResourceRelationExample example);
}