package com.rawmic.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {
    @GetMapping("/health-check")    //getMapping mena
    public String healthStatus(){
        return "OK";
    }
}
