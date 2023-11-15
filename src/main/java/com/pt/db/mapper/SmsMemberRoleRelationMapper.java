package com.pt.db.mapper;

import com.pt.db.model.SmsMemberRoleRelation;
import com.pt.db.model.SmsMemberRoleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsMemberRoleRelationMapper {
    long countByExample(SmsMemberRoleRelationExample example);

    int deleteByExample(SmsMemberRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsMemberRoleRelation record);

    int insertSelective(SmsMemberRoleRelation record);

    List<SmsMemberRoleRelation> selectByExample(SmsMemberRoleRelationExample example);

    SmsMemberRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsMemberRoleRelation record, @Param("example") SmsMemberRoleRelationExample example);

    int updateByExample(@Param("record") SmsMemberRoleRelation record, @Param("example") SmsMemberRoleRelationExample example);

    int updateByPrimaryKeySelective(SmsMemberRoleRelation record);

    int updateByPrimaryKey(SmsMemberRoleRelation record);
}