package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("home")
    public String getHome() {

        return "/home";
    }



    @GetMapping("/cabinet")
    public String getCabinet() {
        return "/cabinet";
    }
}
