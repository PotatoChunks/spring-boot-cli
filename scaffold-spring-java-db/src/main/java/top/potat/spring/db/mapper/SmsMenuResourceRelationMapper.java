package top.potat.spring.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potat.spring.db.model.SmsMenuResourceRelation;
import top.potat.spring.db.model.SmsMenuResourceRelationExample;

public interface SmsMenuResourceRelationMapper {
    long countByExample(SmsMenuResourceRelationExample example);

    int deleteByExample(SmsMenuResourceRelationExample example);

    int insert(SmsMenuResourceRelation record);

    int insertSelective(SmsMenuResourceRelation record);

    List<SmsMenuResourceRelation> selectByExample(SmsMenuResourceRelationExample example);

    int updateByExampleSelective(@Param("record") SmsMenuResourceRelation record, @Param("example") SmsMenuResourceRelationExample example);

    int updateByExample(@Param("record") SmsMenuResourceRelation record, @Param("example") SmsMenuResourceRelationExample example);
}