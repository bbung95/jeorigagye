package com.jeorigagye.service;

import com.jeorigagye.domain.Account;
import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Expenditure;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.account.AccountDto;
import com.jeorigagye.dto.expenditure.ExpenditureForm;
import com.jeorigagye.enums.AccountType;
import com.jeorigagye.repository.CategoryRepository;
import com.jeorigagye.repository.ExpenditureRepository;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;
    private final MemberRepsitory memberRepsitory;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<Long> addExpenditure(ExpenditureForm expenditureForm){

        Category findCategory = categoryRepository.findById(expenditureForm.getCatId()).get();
        Member findMember = memberRepsitory.findById(expenditureForm.getMemberId()).get();

        Expenditure expenditure = Expenditure.builder()
                .price(expenditureForm.getPrice())
                .name(expenditureForm.getName())
                .member(findMember)
                .category(findCategory)
                .build();

        Expenditure saveExepnditure = expenditureRepository.save(expenditure);

        return new ResponseEntity<>(saveExepnditure.getId(), HttpStatus.OK);
    }

    public ResponseEntity<List<Expenditure>> findAll(Search search){

        PageRequest pageRequest = PageRequest.of(search.getCulPage(), 10);

        Page<Expenditure> findExpenditures = expenditureRepository.findAll(pageRequest);

        List<Expenditure> expenditureList = new ArrayList<>();
//        findExpenditures.getContent()
//                .stream()
//                .map(Expenditure::t)
//                .collect(Collectors.toList());

        return new ResponseEntity<>(expenditureList, HttpStatus.OK);
    }

    public ResponseEntity deleteExpenditure(Long expenditureId){

        Expenditure findExpenditure = expenditureRepository.findById(expenditureId).get();
        expenditureRepository.delete(findExpenditure);

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<Integer> findExpenditureSumPrice(Long memberId){

        int sumPrice = expenditureRepository.findByMemberIdWithExpenditureSumPrice(memberId);

        return new ResponseEntity(sumPrice, HttpStatus.OK);
    }

}
