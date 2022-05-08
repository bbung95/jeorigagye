package com.jeorigagye.controller;

import com.jeorigagye.config.security.auth.PrincipalDetail;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.member.MemberForm;
import com.jeorigagye.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity memberJoin(@RequestBody MemberForm memberForm){

        return memberService.memberJoin(memberForm);
    }

    @PostMapping("logout")
    public ResponseEntity memberLogout(HttpSession session){

        session.invalidate();

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity memberList(Search search){

        return  memberService.findAll(search);
    }

    @GetMapping("check")
    public boolean memberLoginCheck(@AuthenticationPrincipal PrincipalDetail principalDetail){

        if(principalDetail != null){
            return true;
        }

        return false;
    }


}
