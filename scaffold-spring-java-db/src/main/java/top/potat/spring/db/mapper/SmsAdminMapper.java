package top.potat.spring.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.db.model.SmsAdminExample;

public interface SmsAdminMapper {
    long countByExample(SmsAdminExample example);

    int deleteByExample(SmsAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsAdmin record);

    int insertSelective(SmsAdmin record);

    List<SmsAdmin> selectByExample(SmsAdminExample example);

    SmsAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsAdmin record, @Param("example") SmsAdminExample example);

    int updateByExample(@Param("record") SmsAdmin record, @Param("example") SmsAdminExample example);

    int updateByPrimaryKeySelective(SmsAdmin record);

    int updateByPrimaryKey(SmsAdmin record);
}