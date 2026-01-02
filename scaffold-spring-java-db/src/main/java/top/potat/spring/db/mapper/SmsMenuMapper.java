package top.potat.spring.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potat.spring.db.model.SmsMenu;
import top.potat.spring.db.model.SmsMenuExample;

public interface SmsMenuMapper {
    long countByExample(SmsMenuExample example);

    int deleteByExample(SmsMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsMenu record);

    int insertSelective(SmsMenu record);

    List<SmsMenu> selectByExample(SmsMenuExample example);

    SmsMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsMenu record, @Param("example") SmsMenuExample example);

    int updateByExample(@Param("record") SmsMenu record, @Param("example") SmsMenuExample example);

    int updateByPrimaryKeySelective(SmsMenu record);

    int updateByPrimaryKey(SmsMenu record);
}