package com.pt.db.mapper;

import com.pt.db.model.SmsResource;
import com.pt.db.model.SmsResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsResourceMapper {
    long countByExample(SmsResourceExample example);

    int deleteByExample(SmsResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsResource record);

    int insertSelective(SmsResource record);

    List<SmsResource> selectByExample(SmsResourceExample example);

    SmsResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsResource record, @Param("example") SmsResourceExample example);

    int updateByExample(@Param("record") SmsResource record, @Param("example") SmsResourceExample example);

    int updateByPrimaryKeySelective(SmsResource record);

    int updateByPrimaryKey(SmsResource record);
}