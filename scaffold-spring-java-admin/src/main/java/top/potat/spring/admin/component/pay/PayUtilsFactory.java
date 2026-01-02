package top.potat.spring.admin.component.pay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.potat.spring.common.domain.pay.PayCommonAbstractModel;
import top.potat.spring.common.utils.pay.PayCommonAbstractService;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayUtilsFactory {


    public PayCommonAbstractService getPayService(String payType) {
        log.debug("获取支付服务:{}", payType);
        log.debug("---------------");
        PayCommonAbstractModel.PayTypeEnum payTypeEnum = PayCommonAbstractModel.PayTypeEnum.payTypeEnumByCodeType(payType);
        log.debug("获取支付服务:{}", payTypeEnum);
        log.debug("---------------");
        switch (payTypeEnum) {
            case WX_PAY:
                //return new WxPayService();
                return null;
            case ALI_PAY:
                //return new AliPayService();
                return null;
            case UNION_PAY:
                //return new UnionPayService();
                return null;
            default:
                log.error("支付类型错误->{}->枚举类型->{}", payType, payTypeEnum);
                return null;
        }
    }

}
