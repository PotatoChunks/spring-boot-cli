package com.pt.db.mapper;

import com.pt.db.model.SmsResourceCategory;
import com.pt.db.model.SmsResourceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsResourceCategoryMapper {
    long countByExample(SmsResourceCategoryExample example);

    int deleteByExample(SmsResourceCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsResourceCategory record);

    int insertSelective(SmsResourceCategory record);

    List<SmsResourceCategory> selectByExample(SmsResourceCategoryExample example);

    SmsResourceCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsResourceCategory record, @Param("example") SmsResourceCategoryExample example);

    int updateByExample(@Param("record") SmsResourceCategory record, @Param("example") SmsResourceCategoryExample example);

    int updateByPrimaryKeySelective(SmsResourceCategory record);

    int updateByPrimaryKey(SmsResourceCategory record);
}