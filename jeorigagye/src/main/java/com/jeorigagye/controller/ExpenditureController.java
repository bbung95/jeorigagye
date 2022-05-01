package com.jeorigagye.controller;

import com.jeorigagye.config.security.auth.PrincipalDetail;
import com.jeorigagye.domain.Expenditure;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.account.AccountForm;
import com.jeorigagye.dto.expenditure.ExpenditureForm;
import com.jeorigagye.service.AccountService;
import com.jeorigagye.service.ExpenditureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenditure/")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    @PostMapping("regist")
    public ResponseEntity expenditureRegist(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody ExpenditureForm expenditureForm){

        Member member = (Member)principalDetail.getMember();
        expenditureForm.setMemberId(member.getId());

        return expenditureService.addExpenditure(expenditureForm);
    }

    @GetMapping
    public ResponseEntity expenditureList(@RequestBody Search search){

        return expenditureService.findAll(search);
    }

    @DeleteMapping("{expenditureId}")
    public ResponseEntity expenditureList(@PathVariable Long expenditureId){

        return expenditureService.deleteExpenditure(expenditureId);
    }

    @GetMapping("count")
    public ResponseEntity expenditureTotalCount(@AuthenticationPrincipal PrincipalDetail principalDetail){

        Member member = (Member)principalDetail.getMember();

        return expenditureService.findExpenditureSumPrice(member.getId());
    }
}
