package com.jeorigagye.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.Logger.*;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){

        log.info("home");

        return "welcome";
    }

}