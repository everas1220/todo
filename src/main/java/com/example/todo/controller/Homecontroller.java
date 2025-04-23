package com.example.todo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class Homecontroller {

    @GetMapping("/")
    public String getHome() {
        log.info("home 요청");
        return "home";
    }
}
