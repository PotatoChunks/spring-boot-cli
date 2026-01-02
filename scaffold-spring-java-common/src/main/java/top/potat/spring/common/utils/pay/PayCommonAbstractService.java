package top.potat.spring.common.utils.pay;


import top.potat.spring.common.domain.pay.PayCommonAbstractModel;

/**
 * 同一支付抽象类
 */
public interface PayCommonAbstractService {

    /**
     * 下订单
     */
    PayCommonAbstractModel.PayNotifyRes order(PayCommonAbstractModel.PayParam aliPayParam);


    /**
     * 查询订单信息
     */
    PayCommonAbstractModel.QueryOrderRes queryOrder(PayCommonAbstractModel.QueryOrderParam queryOrderParam);


    /**
     * 关闭订单
     */
    PayCommonAbstractModel.CloseOrderRes closeOrder(PayCommonAbstractModel.CloseOrderParam closeOrderParam);


    /**
     * 退款申请
     */
    PayCommonAbstractModel.RefundRes refund(PayCommonAbstractModel.RefundParam refundParam);


    /**
     * 解析支付回调参数
     */
    PayCommonAbstractModel.PayNotifyParameter parseOrderNotifyResult(PayCommonAbstractModel.PayNotifyParameterModel payNotifyParameterModel);


    /**
     * 处理支付回调结果
     */
    void handleOrderNotifyResult(PayCommonAbstractModel.PayNotifyParameter payNotifyParameter,PayCommonAbstractModel.PayNotifyParameterModel payNotifyParameterModel);


    /**
     * 解析退款回调参数
     */
    PayCommonAbstractModel.RefundRes parseRefundNotifyResult(PayCommonAbstractModel.RefundNotifyParameterModel refundNotifyParameterModel);


    /**
     * 处理退款回调结果
     */
    void handleRefundNotifyResult(PayCommonAbstractModel.RefundRes refundRes,PayCommonAbstractModel.RefundNotifyParameterModel refundNotifyParameterModel);

}
