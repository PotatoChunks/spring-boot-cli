package top.potat.spring.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potat.spring.db.model.UmsMemberUser;
import top.potat.spring.db.model.UmsMemberUserExample;

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