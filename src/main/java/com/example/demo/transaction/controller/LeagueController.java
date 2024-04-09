package com.example.demo.transaction.controller;

import com.example.demo.transaction.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    @PostMapping("/LeagueService/addLegend")
    public void addLegend(@RequestParam String name) {
        leagueService.addLegend(name);
    }

    public static void main(String[] args) {
        Boolean b1 = Boolean.TRUE;
        System.out.println(String.valueOf(b1));
        Boolean b2 = null;
        System.out.println(String.valueOf(b2));
    }
}