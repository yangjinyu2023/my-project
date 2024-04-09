package com.example.demo.throwable;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Slf4j
public class ExceptionUtilsDemo {
    public static void main(String[] args) {
        try {
            say();
        } catch (Exception e) {
            log.error("", e);
            System.out.println("----------");
            //RuntimeException:xx  （打印的是直接异常）
            System.out.println("ExceptionUtils.getMessage(e)：" + ExceptionUtils.getMessage(e));
            //ArithmeticException: / by zero   （打印的是根异常，但是若是抛出异常时，没将跟异常向外抛出，那么捕获到的依旧是RuntimeException:xx）
            System.out.println("ExceptionUtils.getRootCauseMessage(e)：" + ExceptionUtils.getRootCauseMessage(e));
            //打印的是异常堆栈信息
            System.out.println("ExceptionUtils.getStackTrace(e)：" + ExceptionUtils.getStackTrace(e));
            //java.lang.ArithmeticException: / by zero   (打印的是根异常，但是若是抛出异常时，没将跟异常向外抛出，那么返回null)
            System.out.println("ExceptionUtils.getRootCause(e)：" + ExceptionUtils.getRootCause(e));
            //异常链中异常的数量：2
            System.out.println("ExceptionUtils.getThrowableCount(e)：" + ExceptionUtils.getThrowableCount(e));
            //java.lang.ArithmeticException: / by zero  数组，第一位的的是根异常信息
            System.out.println("ExceptionUtils.getRootCauseStackTrace(e)[0]：" + ExceptionUtils.getRootCauseStackTrace(e)[0]);
            //java.lang.RuntimeException: xx  数组，第一位的是直接异常信息
            System.out.println("ExceptionUtils.getStackFrames(e)[0]：" + ExceptionUtils.getStackFrames(e)[0]);
            //异常链的数组：[java.lang.RuntimeException: xx, java.lang.ArithmeticException: / by zero]
            System.out.println("ExceptionUtils.getThrowableList(e)：" + ExceptionUtils.getThrowableList(e));
            //自定义的异常链信息（打印直接异常的详细信息）：ExceptionUtil.getLogErrorMessage(e)：xx||RuntimeException||com.tellme.test.TestExceptionUtils.say(TestExceptionUtils.java:38)
            System.out.println("ExceptionUtil.getLogErrorMessage(e)：" + getLogErrorMessage(e));
        }
    }

    private static void say() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            //将e传入，即保持了异常链的信息。否则的话，异常链便断了。
            throw new RuntimeException("xx", e);
        }
    }

    /**
     * 获取到异常描述
     *
     * @param e 异常信息
     * @return "异常信息||异常类名||异常位置"
     */
    public static String getLogErrorMessage(Exception e) {
        return e.getMessage() + "||" + e.getClass().getSimpleName() + "||" + e.getStackTrace()[0];
    }
}