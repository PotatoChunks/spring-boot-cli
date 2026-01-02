package top.potat.spring.db.service.sms;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.common.enums.YesEnum;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.common.utils.encryption.PasswordEncoder;
import top.potat.spring.db.mapper.SmsAdminMapper;
import top.potat.spring.db.mapper.SmsMemberRoleRelationMapper;
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.db.model.SmsAdminExample;
import top.potat.spring.db.models.sms.AdminModel;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SerAdminService {
    private final SmsAdminMapper smsAdminMapper;
    private final SmsMemberRoleRelationMapper smsMemberRoleRelationMapper;


    /**
     * 获取管理员列表
     */
    public List<SmsAdmin> getAdminList(AdminModel.ListParamModel query) {
        SmsAdminExample smsAdminExample = new SmsAdminExample();
        SmsAdminExample.Criteria criteria = smsAdminExample.createCriteria().andDeleteTypeEqualTo(YesEnum.NO.getCodeType());

        if(StringUtils.isNotEmpty(query.getLoginName()))
            criteria.andLoginNameLike("%" + query.getLoginName() + "%");

        if(StringUtils.isNotEmpty(query.getUserName()))
            criteria.andUserNameLike("%" + query.getUserName() + "%");

        if(StringUtils.isNotEmpty(query.getPhone()))
            criteria.andPhoneLike("%" + query.getPhone() + "%");

        if(StringUtils.isNotEmpty(query.getTitle()))
            criteria.andTitleLike("%" + query.getTitle() + "%");


        smsAdminExample.setOrderByClause("create_time desc, id desc");
        if(query.getPageNum() != null && query.getPageSize() != null)
            PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return smsAdminMapper.selectByExample(smsAdminExample);
    }

    /**
     * 封装列表Model
     */
    public List<AdminModel.AdminListModel> setAdminListModel(List<SmsAdmin> smsAdminList) {
        if(CollectionUtil.isEmpty(smsAdminList))
            return Collections.emptyList();
        return smsAdminList.stream().map(smsAdmin -> {
            AdminModel.AdminListModel adminListModel = new AdminModel.AdminListModel(smsAdmin);
            adminListModel.setDisableTypeName(YesEnum.getCodeTypeByCodeName(smsAdmin.getDisableType()));
            if(StringUtils.isEmpty(adminListModel.getTitle()))
                adminListModel.setTitle("无");
            return adminListModel;
        }).collect(Collectors.toList());
    }


    /**
     * 根据loginName查询管理员信息
     */
    public List<SmsAdmin> getAdminListByLoginName(String loginName) {
        if(StringUtils.isEmpty(loginName))
            return Collections.emptyList();
        SmsAdminExample smsAdminExample = new SmsAdminExample();
        smsAdminExample.createCriteria()
                .andDeleteTypeEqualTo(YesEnum.NO.getCodeType())
                .andLoginNameEqualTo(loginName);
        smsAdminExample.setOrderByClause("create_time desc, id desc");
        return smsAdminMapper.selectByExample(smsAdminExample);
    }



    /**
     * 软删除
     */
    public void deleteAdmin(Long id) {
        SmsAdmin smsAdmin = smsAdminMapper.selectByPrimaryKey(id);
        if (smsAdmin == null) {
            ErrorAsserts.dataNotFount("管理员不存在");
        }
        smsAdmin.setDeleteType(YesEnum.YES.getCodeType());
        smsAdmin.setDeleteTime(new Date());
        smsAdminMapper.updateByPrimaryKeySelective(smsAdmin);
    }


    /**
     * 新增管理员
     */
    public void insertAdmin(SmsAdmin record) {
        //密码加密
        if(StringUtils.isNotEmpty(record.getPassword())){
            record.setPassword( PasswordEncoder.encrypt(record.getPassword()) );
        }
        smsAdminMapper.insertSelective(record);
    }


    /**
     * 根据id查询
     */
    public SmsAdmin getById(Long id) {
        return smsAdminMapper.selectByPrimaryKey(id);
    }


    /**
     * 修改管理员信息
     */
    public void updateAdmin(SmsAdmin record) {
        if(record == null || record.getId() == null)
            ErrorAsserts.permissionException("参数异常");
        //修改密码
        if(StringUtils.isEmpty(record.getPassword())){
            record.setPassword(null);
        }else {
            record.setPassword( PasswordEncoder.encrypt(record.getPassword()) );
        }
        smsAdminMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 修改管理员
     */
    public void updateAdminDetail(AdminModel.UpdateParamModel record) {
        updateAdmin(record.build());
    }


}
