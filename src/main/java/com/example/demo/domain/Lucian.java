package com.example.demo.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class Lucian {
    @Autowired
    private Gun gun;
}