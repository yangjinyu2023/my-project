package com.example.demo.springcloud;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyHystrixClientFallbackFactory implements FallbackFactory<MyFeignClient> {
    @Override
    public MyFeignClient create(Throwable throwable) {
        return new MyFeignClient() {
            @Override
            public String getString(String param) {
                log.error("异常处理={0}", throwable);
                return "Execute raw fallback: access service fail, reason " + throwable;
            }
        };
    }
}