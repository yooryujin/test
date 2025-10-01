package com.example.ryujin.attend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/check_in")
    public String checkIn() {
        return "check_in";
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

}
