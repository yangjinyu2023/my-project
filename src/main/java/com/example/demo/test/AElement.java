package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AElement extends BaseElement implements IElement{
    @Override
    public void execute() {
        log.info("A execute");
        properties.execute();
    }
}