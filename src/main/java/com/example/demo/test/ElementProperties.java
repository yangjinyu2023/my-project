package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ElementProperties {
    public void execute(){
        log.info("ElementProperties execute");
    }
}