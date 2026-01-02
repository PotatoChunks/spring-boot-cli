package top.potat.spring.admin.service.sms;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.potat.spring.admin.dao.SmsRoleResourceDao;
import top.potat.spring.db.model.SmsMenuResourceRelation;
import top.potat.spring.db.models.sms.SerMenuModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmsMenuService {

    private final SmsRoleResourceDao roleResourceDao;
    /**
     * 菜单绑定多个资源Api
     */
    public void updateMenuResource(SerMenuModel.MenuResourceRelationModel reqModel){
        List<Long> resourceIdList = reqModel.getResourceIdList();
        if(CollectionUtil.isEmpty(resourceIdList))
            return;
        List<SmsMenuResourceRelation> relationList = resourceIdList.stream().map(resourceId -> {
            SmsMenuResourceRelation smsMenuResourceRelation = new SmsMenuResourceRelation();
            smsMenuResourceRelation.setResourceId(resourceId);
            smsMenuResourceRelation.setMenuId(reqModel.getMenuId());
            return smsMenuResourceRelation;
        }).collect(Collectors.toList());
        roleResourceDao.insertMenuResourceList(relationList);
    }



}
