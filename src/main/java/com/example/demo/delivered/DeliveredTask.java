package com.example.demo.delivered;

import java.io.Serializable;

import lombok.Data;

/**
 * 妥投任务
 *
 * @author yangjinyu
 * @time 2023/4/4 14:39
 */
@Data
public class DeliveredTask implements Serializable {
    private static final long serialVersionUID = -7934076299431480447L;
    int source;
    String waybillCode;
    int siteId;
    boolean constraint;
}