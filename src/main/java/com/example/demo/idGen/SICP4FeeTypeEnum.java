package com.example.demo.idGen;

import org.springframework.lang.NonNull;

/**
 * 地方应缴类型AAE793
 * 
 * @author yangjinyu
 */
@SuppressWarnings("NonAsciiCharacters")
public enum SICP4FeeTypeEnum {

    正常核定("10"),
	账户维护("12"),
	个人补收("20"),
	差额补基数("24"),
	差额补比例("25"),
	差额退基数("26"),
	差额退比例("27"),
	一次性缴费("28"),
	退役军人补收("74"),
	退役军人补收差额("75"),
	账户转入("91"),
	事转企_下海不参与缴费计算("92");

    private String codeValue;

    SICP4FeeTypeEnum(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeValue() {
        return this.codeValue;
    }

    public static String getValueCode(@NonNull String code) {
        for (SICP4FeeTypeEnum element : values()) {
            if (element.getCodeValue().equals(code))
                return element.toString();
        }
        return "";
    }
}