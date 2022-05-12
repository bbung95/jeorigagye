package com.jeorigagye.controller;

import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.expenditure.ExpenditureForm;
import com.jeorigagye.service.ExpenditureService;
import com.jeorigagye.util.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenditure/")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    @PostMapping("regist")
    public ResponseEntity expenditureRegist(HttpServletRequest request, @RequestBody ExpenditureForm expenditureForm){

        Long memberId = AuthToken.tokenParse(request);
        expenditureForm.setMemberId(memberId);

        return expenditureService.addExpenditure(expenditureForm);
    }

    @GetMapping
    public ResponseEntity expenditureList(HttpServletRequest request, @RequestBody Search search){

        Long memberId = AuthToken.tokenParse(request);
        search.setMemberId(memberId);

        return expenditureService.findAll(search);
    }

    @DeleteMapping("{expenditureId}")
    public ResponseEntity expenditureDelete(@PathVariable Long expenditureId){

        return expenditureService.deleteExpenditure(expenditureId);
    }

    @GetMapping("count")
    public ResponseEntity expenditureTotalCount(HttpServletRequest request){

        Long memberId = AuthToken.tokenParse(request);

        return expenditureService.findExpenditureSumPrice(memberId);
    }
}
