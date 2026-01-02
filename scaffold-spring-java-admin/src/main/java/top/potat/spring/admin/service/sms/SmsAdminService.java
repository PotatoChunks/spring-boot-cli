package top.potat.spring.admin.service.sms;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.admin.dao.SmsRoleResourceDao;
import top.potat.spring.common.exception.ErrorAsserts;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.common.utils.encryption.PasswordEncoder;
import top.potat.spring.db.mapper.SmsAdminMapper;
import top.potat.spring.db.mapper.SmsMemberRoleRelationMapper;
import top.potat.spring.db.model.SmsAdmin;
import top.potat.spring.db.model.SmsMemberRoleRelation;
import top.potat.spring.db.model.SmsMemberRoleRelationExample;
import top.potat.spring.db.model.SmsRole;
import top.potat.spring.db.models.sms.AdminModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmsAdminService {
    private final SmsAdminMapper smsAdminMapper;
    private final SmsMemberRoleRelationMapper smsMemberRoleRelationMapper;

    private final SmsRoleResourceDao smsRoleResourceDao;

    /**
     * 管理员关联角色
     */
    public void addAdminRole(AdminModel.AdminInfoModel adminRoleModel) {
        if(adminRoleModel.getRoleIdList() == null) return;
        //删除掉旧的关联
        SmsMemberRoleRelationExample smsMemberRoleRelationExample = new SmsMemberRoleRelationExample();
        smsMemberRoleRelationExample.createCriteria()
                .andMemberIdEqualTo(adminRoleModel.getId()).andRelationTypeEqualTo(2);
        smsMemberRoleRelationMapper.deleteByExample(smsMemberRoleRelationExample);
        List<SmsRole> roleList = adminRoleModel.getRoleIdList();
        if(CollectionUtil.isEmpty(roleList))
            return;
        List<SmsMemberRoleRelation> roleRelationList = roleList.stream().map(role -> {
            SmsMemberRoleRelation smsMemberRoleRelation = new SmsMemberRoleRelation();
            smsMemberRoleRelation.setMemberId(adminRoleModel.getId());
            smsMemberRoleRelation.setRoleId(role.getId());
            smsMemberRoleRelation.setRelationType(2);
            return smsMemberRoleRelation;
        }).collect(Collectors.toList());

        smsRoleResourceDao.insertAdminRoleList(roleRelationList);
    }
    public void addAdminRole(AdminModel.UpdateParamModel adminRoleModel) {
        if(adminRoleModel.getRoleIdList() == null) return;
        //删除掉旧的关联
        SmsMemberRoleRelationExample smsMemberRoleRelationExample = new SmsMemberRoleRelationExample();
        smsMemberRoleRelationExample.createCriteria()
                .andMemberIdEqualTo(adminRoleModel.getId()).andRelationTypeEqualTo(2);
        smsMemberRoleRelationMapper.deleteByExample(smsMemberRoleRelationExample);
        List<SmsRole> roleList = adminRoleModel.getRoleIdList();
        if(CollectionUtil.isEmpty(roleList))
            return;
        List<SmsMemberRoleRelation> roleRelationList = roleList.stream().map(role -> {
            SmsMemberRoleRelation smsMemberRoleRelation = new SmsMemberRoleRelation();
            smsMemberRoleRelation.setMemberId(adminRoleModel.getId());
            smsMemberRoleRelation.setRoleId(role.getId());
            smsMemberRoleRelation.setRelationType(2);
            return smsMemberRoleRelation;
        }).collect(Collectors.toList());

        smsRoleResourceDao.insertAdminRoleList(roleRelationList);
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

}
