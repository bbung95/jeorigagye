package com.jeorigagye.controller;

import com.jeorigagye.config.security.auth.PrincipalDetail;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.member.MemberForm;
import com.jeorigagye.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity memberJoin(@RequestBody MemberForm memberForm){

        return memberService.memberJoin(memberForm);
    }

    @GetMapping("check")
    public boolean memberLoginCheck(@AuthenticationPrincipal Principal principal){

        System.out.println(principal);

        if(principal != null){
            return true;
        }

        return false;
    }


}
