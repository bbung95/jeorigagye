package com.jeorigagye.controller;

import com.jeorigagye.dto.member.MemberForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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