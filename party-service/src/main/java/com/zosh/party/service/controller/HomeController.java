package com.zosh.party.service.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    public String HomeControllerHandler(){
        return "user microservice for party booking";
    }
}
