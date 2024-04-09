package com.example.demo.throwable;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/2 15:22
 */
public class ErrorAndExceptionDemo {

    private void throwError() {
        throw new StackOverflowError();
    }

    private void throwRuntimeException() {
        throw new NullPointerException();
    }

    private void throwCheckedException() throws NoSuchFieldException {
        // checked exception 如果不捕获，编译不通过
        throw new NoSuchFieldException();
    }

    public static void main(String[] args) {
        ErrorAndExceptionDemo errorAndException = new ErrorAndExceptionDemo();
        errorAndException.throwError();
        errorAndException.throwRuntimeException();
        try {
            errorAndException.throwCheckedException();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}