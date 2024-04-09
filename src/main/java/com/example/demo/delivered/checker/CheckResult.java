package com.example.demo.delivered.checker;

import java.io.Serializable;

/**
 * 校验结果
 *
 * @author yangjinyu
 * @time 2023/4/17 14:33
 */
public class CheckResult implements Serializable {
    private static final long serialVersionUID = -6478984730743917567L;
    boolean success = true;
    String message;

    public CheckResult() {
    }

    public CheckResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public static CheckResult success() {
        return new CheckResult();
    }

    public static CheckResult fail(String message){
        return new CheckResult(false, message);
    }
}