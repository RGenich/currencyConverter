package com.test.currencyConverter.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String getMainPage () {
        return "index";
    }

}
