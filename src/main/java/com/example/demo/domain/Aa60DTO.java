package com.example.demo.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 业务互斥关系参数表
 * </p>
 *
 * @author wang-zihe
 * @since 2021-03-17
 */
@Data
public class Aa60DTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long aaz365;

    private Long aaz649;

    private String bze090;

    private BigDecimal aze090;
}
