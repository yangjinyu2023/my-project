package com.example.demo.designPattern.proxy.springAop;

import com.example.demo.utils.BeanUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @PostMapping("/testAop")
    public void test() {
        ((AopDemo1) BeanUtil.getBean("aopDemo1")).hello("123");
    }
}