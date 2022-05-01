package com.jeorigagye.controller;

import com.jeorigagye.config.security.auth.PrincipalDetail;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.account.AccountForm;
import com.jeorigagye.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account/")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("regist")
    public ResponseEntity accountRegist(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody AccountForm accountForm){

        Member member = (Member)principalDetail.getMember();
        accountForm.setMemberId(member.getId());

        return accountService.addAccount(accountForm);
    }

    @GetMapping
    public ResponseEntity accountList(@RequestBody Search search){

        return accountService.findAll(search);
    }

    @GetMapping("count")
    public ResponseEntity accountTotalCount(@AuthenticationPrincipal PrincipalDetail principalDetail){

        Member member = (Member)principalDetail.getMember();

        return accountService.accountSumPrice(member.getId());
    }

}
