package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void getLogin() {
        log.info("login 폼 요청");
    }

    @GetMapping("/registry")
    public void getRegistry() {
        log.info("registry 폼 요청");
    }

    @GetMapping("/auth")
    public String gAuth(@RequestParam String param) {
        return new String();
    }

}
