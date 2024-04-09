package com.example.demo.springcloud;

import com.example.demo.utils.BeanUtil;
import feign.Feign;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

public class FeignDemo {
    public static void main(String[] args) {
        //1
        DataManageApi dataManageApi = Feign.builder()
                // 集成ribbon
                .client(new LoadBalancerFeignClient(null,null,null))
                .decoder(new MyDecoder())
                .target(DataManageApi.class, "https://api.github.com");
        dataManageApi.getString("111");
        //2
        BeanUtil.getBean(MyFeignClient.class).getString("111");
    }
}