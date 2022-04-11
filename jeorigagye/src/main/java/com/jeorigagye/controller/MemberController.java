package com.jeorigagye.controller;

import com.jeorigagye.dto.MemberForm;
import com.jeorigagye.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("join")
    public Long memberJoin(@RequestBody MemberForm memberForm){

        System.out.println("memberForm = " + memberForm);

        Long memberId = memberService.memberJoin(memberForm);

        return memberId;
    }

}
