package top.potat.spring.admin.service.sms;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.admin.dao.SmsRoleResourceDao;
import top.potat.spring.db.model.SmsRoleMenuRelation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmsRoleResourceService {

    private final SmsRoleResourceDao smsRoleResourceDao;

    /**
     * 批量关联角色和菜单的 数据
     */
    public void insertRoleMenuIdList(Long roleId,List<Long> menuIdList){
        if(roleId == null || CollectionUtil.isEmpty(menuIdList))
            return;
        List<SmsRoleMenuRelation> smsRoleMenuRelationList = menuIdList.stream()
                .map(menuId -> {
                    SmsRoleMenuRelation smsRoleMenuRelation = new SmsRoleMenuRelation();
                    smsRoleMenuRelation.setRoleId(roleId);
                    smsRoleMenuRelation.setMenuId(menuId);
                    return smsRoleMenuRelation;
                })
                .collect(Collectors.toList());

        smsRoleResourceDao.insertRoleMenuIdList(smsRoleMenuRelationList);
    }


}
