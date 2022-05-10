package com.jeorigagye.controller;

import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.member.MemberForm;
import com.jeorigagye.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity memberJoin(@RequestBody MemberForm memberForm){

        return memberService.memberJoin(memberForm);
    }

    @GetMapping
    public ResponseEntity memberList(@RequestBody Search search){

        return  memberService.findAll(search);
    }

    @GetMapping("check")
    public boolean memberLoginCheck(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        System.out.println("token = " + token);

        if(token != null){
            return true;
        }

        System.out.println("token = " + token);
        
        return false;
    }

}