package com.example.demo.springcloud;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "serverName", path = "contextPath",
        configuration = MyFeignConfiguration.class,
        fallbackFactory = MyHystrixClientFallbackFactory.class)
public interface MyFeignClient extends DataManageApi {
}