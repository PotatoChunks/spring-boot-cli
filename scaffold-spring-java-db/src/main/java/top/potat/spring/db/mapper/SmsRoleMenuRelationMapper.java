package top.potat.spring.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potat.spring.db.model.SmsRoleMenuRelation;
import top.potat.spring.db.model.SmsRoleMenuRelationExample;

public interface SmsRoleMenuRelationMapper {
    long countByExample(SmsRoleMenuRelationExample example);

    int deleteByExample(SmsRoleMenuRelationExample example);

    int insert(SmsRoleMenuRelation record);

    int insertSelective(SmsRoleMenuRelation record);

    List<SmsRoleMenuRelation> selectByExample(SmsRoleMenuRelationExample example);

    int updateByExampleSelective(@Param("record") SmsRoleMenuRelation record, @Param("example") SmsRoleMenuRelationExample example);

    int updateByExample(@Param("record") SmsRoleMenuRelation record, @Param("example") SmsRoleMenuRelationExample example);
}