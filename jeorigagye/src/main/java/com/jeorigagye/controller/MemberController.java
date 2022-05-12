package com.jeorigagye.controller;

import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.member.MemberDto;
import com.jeorigagye.dto.member.MemberForm;
import com.jeorigagye.service.MemberService;
import com.jeorigagye.util.AuthToken;
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

    @GetMapping("list")
    public ResponseEntity memberList(@RequestBody Search search){

        return  memberService.findAll(search);
    }

    @GetMapping
    public ResponseEntity member(HttpServletRequest request){

        Long memberId = AuthToken.tokenParse(request);

        return memberService.findById(memberId);
    }

    @GetMapping("{memberId}")
    public ResponseEntity memberDetail(@PathVariable Long memberId){

        return memberService.findById(memberId);
    }

    @PutMapping
    public ResponseEntity memberDetail(HttpServletRequest request, @RequestBody MemberDto memberDto){

        Long memberId = AuthToken.tokenParse(request);
        memberDto.setId(memberId);

        return memberService.memberUpdate(memberDto);
    }

    // issue : token null check 수정 필요
    @GetMapping("check")
    public boolean memberLoginCheck(HttpServletRequest request){

        return AuthToken.tokenCheck(request);
    }

}