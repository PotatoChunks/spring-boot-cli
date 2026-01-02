package top.potat.spring.common.domain.pay;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 同一支付抽象类封装Model
 */
public class PayCommonAbstractModel {

    /**
     * 支付方式枚举
     */
    @Getter
    public enum PayTypeEnum {
        ALI_PAY("ALI_PAY", "支付宝"),
        WX_PAY("WX_PAY", "微信"),
        UNION_PAY("UNION_PAY", "银联"),
        UNKNOWN("UNKNOWN", "未知")
        ;
        private final String codeType;
        private final String message;
        PayTypeEnum(String codeType, String message) {
            this.codeType = codeType;
            this.message = message;
        }

        /**
         * 通过codeType获取枚举
         */
        public static PayTypeEnum payTypeEnumByCodeType(String codeType) {
            for (PayTypeEnum value : values()) {
                if (Objects.equals(value.codeType, codeType)) {
                    return value;
                }
            }
            return UNKNOWN;
        }
    }


    /**
     * 下单统一参数
     */
    @Data
    public static class PayParam {
        //订单编号
        @ApiModelProperty(value = "订单编号")
        private String orderNum;
        //商品信息
        @ApiModelProperty(value = "商品信息")
        private String description;
        //回调地址
        @ApiModelProperty(value = "回调地址")
        private String notifyUrl;
        //总金额(元)
        @ApiModelProperty(value = "总金额(元)")
        private BigDecimal totalAmount;
        //微信openId
        @ApiModelProperty(value = "微信openId")
        private String userOpenId;
        //用户IP
        @ApiModelProperty(value = "用户IP")
        private String userIp;

        /**
         * 自定义参数1
         * @see PayNotifyResParameter
         */
        @ApiModelProperty(value = "自定义参数1")
        private String parameter1;
        /**
         * 自定义参数2
         * @see PayNotifyResParameter
         */
        @ApiModelProperty(value = "自定义参数2")
        private String parameter2;
    }

    /**
     * 支付回调携带自定义参数
     */
    @Data
    public static class PayNotifyResParameter {
        //订单类型
        private String orderTypeStr;

        public PayNotifyResParameter(){}

        public String toJson(){
            return JSONObject.toJSONString(this);
        }
    }

    /**
     * 统一下单返回参数
     */
    @Data
    public static class PayNotifyRes {
        @ApiModelProperty(value = "订单编号")
        private String orderNum;
        @ApiModelProperty("官方支付订单号(没有不填)")
        private String transactionId;
        @ApiModelProperty("其他三方订单编号(没有不填)")
        private String outTransId;
        @ApiModelProperty(value = "支付返回参数1")
        private Object parameter1;
        @ApiModelProperty(value = "支付返回参数2")
        private Object parameter2;
        public PayNotifyRes(){}
        public PayNotifyRes(String orderNum, Object parameter1){
            this.orderNum = orderNum;
            this.parameter1 = parameter1;
        }
        public PayNotifyRes(String orderNum, Object parameter1, Object parameter2){
            this.orderNum = orderNum;
            this.parameter1 = parameter1;
            this.parameter2 = parameter2;
        }
    }


    /**
     * 查询订单参数
     */
    @Data
    public static class QueryOrderParam {
        @ApiModelProperty(value = "订单编号")
        private String orderNum;
        @ApiModelProperty("官方支付订单号(没有不填)")
        private String transactionId;
        @ApiModelProperty("其他三方订单编号(没有不填)")
        private String outTransId;
    }

    /**
     * 查询订单返回参数
     */
    @Data
    public static class QueryOrderRes {
        @ApiModelProperty(value = "订单编号")
        private String orderNum;
        @ApiModelProperty("官方支付订单号(没有不填)")
        private String transactionId;
        @ApiModelProperty("其他三方订单编号(没有不填)")
        private String outTransId;
        @ApiModelProperty(value = "订单支付状态")
        private OrderStatusEnum status;
        public QueryOrderRes(){}
        public QueryOrderRes(String orderNum, String transactionId, String outTransId, OrderStatusEnum status){
            this.orderNum = orderNum;
            this.transactionId = transactionId;
            this.outTransId = outTransId;
            this.status = status;
        }
    }

    /**
     * 订单状态枚举
     */
    @Getter
    public enum OrderStatusEnum {
        WAIT_PAY("WAIT_PAY", "待支付"),
        PAY_SUCCESS("PAY_SUCCESS", "支付成功"),
        //关闭
        CANCEL("CANCEL", "关闭"),
        //转入退款
        REFUND("REFUND", "转入退款"),
        //已撤销（仅付款码支付会返回）
        REVOKE("REVOKE", "已撤销"),
        //用户支付中（仅付款码支付会返回）
        USERPAYING("USERPAYING", "用户支付中"),
        //支付失败（仅付款码支付会返回）
        PAYERROR("PAYERROR", "支付失败"),
        //未知
        UNKNOWN("UNKNOWN", "未知"),
        ;
        private final String codeType;
        private final String message;
        OrderStatusEnum(String codeType, String message) {
            this.codeType = codeType;
            this.message = message;
        }

        /**
         * 通过codeType获取枚举
         */
        public static OrderStatusEnum orderStatEnumByCodeType(String codeType) {
            for (OrderStatusEnum value : values()) {
                if (Objects.equals(value.codeType, codeType)) {
                    return value;
                }
            }
            return UNKNOWN;
        }

    }


    /**
     * 关闭订单请求参数
     */
    @Data
    public static class CloseOrderParam {
        @ApiModelProperty(value = "订单编号")
        private String orderNum;
        @ApiModelProperty("官方支付订单号(没有不填)")
        private String outTransId;
        @ApiModelProperty("官方支付订单号(没有不填)")
        private String transactionId;
    }

    /**
     * 关闭订单返回参数
     */
    @Data
    public static class CloseOrderRes {
        @ApiModelProperty(value = "订单编号")
        private String orderNum;
        @ApiModelProperty("官方支付订单号(没有不填)")
        private String transactionId;
        @ApiModelProperty("其他三方订单编号(没有不填)")
        private String outTransId;
        @ApiModelProperty(value = "关闭订单结果")
        private boolean result = true;
    }


    /**
     * 支付回调请求参数
     */
    @Data
    public static class PayNotifyParameterModel{
        @ApiModelProperty(value = "回调请求")
        private HttpServletRequest request;
        @ApiModelProperty(value = "回调响应")
        private HttpServletResponse response;
    }

    /**
     * 支付回调解析参数
     */
    @Data
    public static class PayNotifyParameter {
        @ApiModelProperty("订单编号")
        private String orderNum;
        @ApiModelProperty("官方支付订单号")
        private String transactionId;
        @ApiModelProperty("其他三方订单编号(没有不填)")
        private String outTransId;
        @ApiModelProperty(value = "订单支付状态")
        private OrderStatusEnum status;
        @ApiModelProperty("交易金额(元 没有不填)")
        private BigDecimal amount;

        @ApiModelProperty("自定义参数1")
        private String parameter1;
        @ApiModelProperty("自定义参数2")
        private String parameter2;
    }


    /**
     * 退款请求参数
     */
    @Data
    public static class RefundParam {
        @ApiModelProperty(value = "支付时的订单编号")
        private String orderNum;
        @ApiModelProperty(value = "退款订单编号")
        private String refundOrderNum;
        @ApiModelProperty(value = "退款金额(元)")
        private BigDecimal refundAmount;
        @ApiModelProperty(value = "订单原本总金额")
        private BigDecimal totalAmount;
        @ApiModelProperty(value = "原因")
        private String reason;
        @ApiModelProperty(value = "回调地址")
        private String notifyUrl;

        /**
         * 自定义参数1
         * @see RefundNotifyResParameter
         */
        @ApiModelProperty(value = "自定义参数1")
        private String parameter1;
        /**
         * 自定义参数2
         * @see RefundNotifyResParameter
         */
        @ApiModelProperty(value = "自定义参数2")
        private String parameter2;

    }

    /**
     * 退款回调携带自定义参数
     */
    @Data
    public static class RefundNotifyResParameter {

        public RefundNotifyResParameter(){}
        public String toJson(){
            return JSONObject.toJSONString(this);
        }
    }

    /**
     * 退款状态枚举
     */
    @Getter
    public enum RefundStatusEnum {
        WAIT_REFUND("WAIT_REFUND", "退款处理中"),
        REFUND_SUCCESS("REFUND_SUCCESS", "退款成功"),
        //退款关闭
        REFUND_CLOSE("REFUND_CLOSE", "退款关闭"),
        //退款异常
        REFUND_EXCEPTION("REFUND_EXCEPTION", "退款异常"),
        //未知
        REFUND_UNKNOWN("REFUND_UNKNOWN", "未知"),
        ;
        private final String codeType;
        private final String message;
        RefundStatusEnum(String codeType, String message) {
            this.codeType = codeType;
            this.message = message;
        }

        /**
         * 通过codeType获取枚举
         */
        public static RefundStatusEnum refundStatEnumByCodeType(String codeType) {
            for (RefundStatusEnum value : values()) {
                if (Objects.equals(value.codeType, codeType)) {
                    return value;
                }
            }
            return REFUND_UNKNOWN;
        }

    }


    /**
     * 退款回调请求参数
     */
    @Data
    public static class RefundNotifyParameterModel{
        @ApiModelProperty(value = "回调请求")
        private HttpServletRequest request;
        @ApiModelProperty(value = "回调响应")
        private HttpServletResponse response;
    }


    /**
     * 退款/退款回调 返回参数
     */
    @Data
    public static class RefundRes {
        @ApiModelProperty(value = "退款订单编号")
        private String refundOrderNum;
        @ApiModelProperty(value = "官方退款订单编号")
        private String thirdOrderNum;
        @ApiModelProperty(value = "其他三方退款订单编号(没有不填)")
        private String outTransId;
        @ApiModelProperty(value = "下单时的订单编号")
        private String orderNum;
        @ApiModelProperty(value = "官方支付下单时的订单编号")
        private String transactionId;
        @ApiModelProperty(value = "其他三方下单时的订单编号(没有不填)")
        private String outOrderId;
        @ApiModelProperty(value = "退款状态")
        private RefundStatusEnum status;
        @ApiModelProperty("退款金额(元)")
        private BigDecimal refundAmount;

        @ApiModelProperty("自定义参数1")
        private String parameter1;
        @ApiModelProperty("自定义参数2")
        private String parameter2;

    }

}
