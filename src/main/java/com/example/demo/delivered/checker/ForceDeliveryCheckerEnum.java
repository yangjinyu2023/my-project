package com.example.demo.delivered.checker;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 强妥校验项枚举
 * <p>
 * code决定执行顺序，code越小优先级越高
 * <p>
 * permanent代表是否为常驻校验
 * </p>
 *
 * @author yangjinyu
 * @time 2023/4/20 15:25
 */
@Getter
public enum ForceDeliveryCheckerEnum {
    ALLOW_LIST_CHECK(10000, false, "强妥权限白名单校验"),
    WAYBILL_CHECK(10100, true, "运单信息校验"),
    COURIER_RECEIVE_CHECK_CLOSE_CHECK(10200, false, "配送员收货校验关闭校验"),
    DATA_REPAIR_CHECK(10300, false, "数据修复校验"),
    FORCE_DELIVERY_AUTH_CHECK(10400, false, "强妥专属权限校验"),
    EXTERNAL_ORDER_CHECK(10500, false, "纯外单校验"),
    TELECOM_ORDER_CHECK(10600, false, "运营商订单校验"),
    MEDICINE_ORDER_CHECK(10601, false, "医药类型订单校验"),
    CHANGE_ADDRESS_ORDER_CHECK(10602, false, "改址单校验"),
    ENTERPRISE_DELIVERY_ORDER_CHECK(10700, false, "企业送订单校验"),
    CUSTOMER_FORBID_CHECK(10800, false, "商家禁止强制妥投校验"),
    WAYBILL_VAS_CHECK(10900, false, "运单增值服务校验"),
    ORDER_REJECTED_CHECK(11000, false, "被拒收订单校验"),
    GIFT_ORDER_CHECK(11001, false, "主赠订单校验"),
    EXCEPTION_ORDER_CHECK(11002, true, "异常订单校验"),
    WEIGHT_VOLUME_CHECK(11100, false, "重量体积校验"),
    RECONCILIATION_CHECK(11200, true, "寄付到付对账校验");

    private final int code;
    private final boolean permanent;
    private final String desc;
    private static final Map<Integer, ForceDeliveryCheckerEnum> cache;

    static {
        cache = Arrays.stream(ForceDeliveryCheckerEnum.values())
                .collect(Collectors.toMap(ForceDeliveryCheckerEnum::getCode, e -> e));
    }


    ForceDeliveryCheckerEnum(int code, boolean permanent, String desc) {
        this.code = code;
        this.permanent = permanent;
        this.desc = desc;
    }

    public static boolean isPermanent(int code) {
        ForceDeliveryCheckerEnum checkerEnum = cache.get(code);
        if(checkerEnum != null){
            return checkerEnum.isPermanent();
        }
        return false;
    }
}