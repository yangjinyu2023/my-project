package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.delivered.DeliveredTask;
import com.example.demo.delivered.chain.DeliveredTaskCheckChainHandler;
import com.example.demo.delivered.chain.ForceDeliveryCheckChainHandler;
import com.example.demo.delivered.checker.CheckResult;

@RestController
public class TestController {
    @Autowired
    private ForceDeliveryCheckChainHandler handler1;

    @Autowired
    private DeliveredTaskCheckChainHandler handler2;

    @PostMapping("/test")
    public Boolean test() {
        //ForceDeliveryConfig config = new ForceDeliveryConfig();
        //config.setWaybillCode("JD1111");
        //config.setSource(19);
        //CheckResult result1 = handler1.check(config);
        DeliveredTask deliveredTask = new DeliveredTask();
        deliveredTask.setWaybillCode("JD2222");
        CheckResult result2 = handler2.check(deliveredTask);
        return true;
    }
}