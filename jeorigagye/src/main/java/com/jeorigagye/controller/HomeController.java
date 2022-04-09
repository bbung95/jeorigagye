package com.jeorigagye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){

        System.out.println("home");
        
        model.addAttribute("home", "welcome");

        return "home.html";
    }
}
