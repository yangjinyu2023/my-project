package com.example.demo.designPattern.chain;

import java.util.Arrays;
import java.util.List;

public class ChainPatternDemo {
    static class ChainHandlerA extends ChainHandler {
        @Override
        protected void handleProcess() {
            System.out.println("handle by chain a");
        }
    }

    static class ChainHandlerB extends ChainHandler {
        @Override
        protected void handleProcess() {
            System.out.println("handle by chain b");
        }
    }

    static class ChainHandlerC extends ChainHandler {
        @Override
        protected void handleProcess() {
            System.out.println("handle by chain c");
        }
    }

    // 责任链模式
    // 职责链上的处理者负责处理请求，客户只需要将请求发送到职责链上即可，无须关心请求的处理细节和请求的传递
    // 所以职责链将请求的发送者和请求的处理者解耦了
    public static void main(String[] args) {
        List<ChainHandler> handlers =
                Arrays.asList(new ChainHandlerA(), new ChainHandlerB(), new ChainHandlerC());
        Chain chain = new Chain(handlers);
        chain.proceed();
    }
}