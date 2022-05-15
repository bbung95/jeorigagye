package com.jeorigagye.controller;

import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.account.AccountForm;
import com.jeorigagye.service.AccountService;
import com.jeorigagye.util.AuthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static lombok.extern.slf4j.Slf4j.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("regist")
    public ResponseEntity accountRegist(HttpServletRequest request, @RequestBody AccountForm accountForm){

        Long memberId = AuthToken.tokenParse(request);
        accountForm.setMemberId(memberId);

        return accountService.addAccount(accountForm);
    }

    @GetMapping
    public ResponseEntity accountList(HttpServletRequest request, Search search){

        log.info("search = {}", search);

        Long memberId = AuthToken.tokenParse(request);
        search.setMemberId(memberId);

        return accountService.findAll(search);
    }

    @DeleteMapping("{accountId}")
    public ResponseEntity accountDelete(@PathVariable Long accountId){

        return accountService.deleteAccount(accountId);
    }

    @GetMapping("count")
    public ResponseEntity accountTotalCount(HttpServletRequest request){

        Long memberId = AuthToken.tokenParse(request);

        return accountService.accountSumPrice(memberId);
    }

}
