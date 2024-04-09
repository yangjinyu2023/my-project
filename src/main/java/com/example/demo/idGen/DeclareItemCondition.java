package com.example.demo.idGen;

import lombok.Data;

import java.io.Serializable;

/**
 * 申报条目
 *
 * @author yangjinyu
 * @time 2021/3/30 14:44
 */
@Data
public class DeclareItemCondition implements Serializable {

    private static final long serialVersionUID = 1780660083046801111L;

    private String personID;

    private String unitID;

    private Long issue;

    private Long startBusiYearMonth;

    private Long endBusiYearMonth;

    private String insuTypes;

    private String feeTypes;

    private String auditSeq;

    private String collectSeq;
}