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

    @GetMapping("{memberId}")
    public ResponseEntity memberDetail(@PathVariable Long memberId){

        return memberService.findById(memberId);
    }

    @GetMapping("check")
    public boolean memberLoginCheck(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        System.out.println("36Line token");

        if(token != null){
            return true;
        }

        System.out.println("42Line token");
        
        return false;
    }

}