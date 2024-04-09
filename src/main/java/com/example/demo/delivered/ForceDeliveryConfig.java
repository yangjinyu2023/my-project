package com.example.demo.delivered;

import java.io.Serializable;

import lombok.Data;

@Data
public class ForceDeliveryConfig implements Serializable {
    private static final long serialVersionUID = -348654695098354312L;
    /**
     * 系统来源，默认为0
     */
    private int source = 0;
    private String waybillCode;
    private String userCode;
    private boolean skip;
}