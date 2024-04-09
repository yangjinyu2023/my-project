package com.example.demo.delivered.checker;

import lombok.Getter;

/**
 * 妥投任务校验项枚举
 * <p>
 * code决定执行顺序，code越小优先级越高
 * </p>
 *
 * @author yangjinyu
 * @time 2023/4/20 15:25
 */
@Getter
public enum DeliveredTaskCheckerEnum {
    TRANSFER_HISTORY_CHECK(10000, "结转历史校验"),
    COLLECT_BEFORE_PAY_CHECK(10100, "先揽后付校验"),
    AN_LI_ORDER_CHECK(10200, "安利订单校验"),
    RETURN_CHANGE_ORDER_CHECK(10300, "拒收换新单校验"),
    PRE_SALE_ORDER_CHECK(10400, "预售单校验");

    private final int code;
    private final String desc;

    DeliveredTaskCheckerEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}