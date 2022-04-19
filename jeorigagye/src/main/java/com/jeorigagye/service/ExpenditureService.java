package com.jeorigagye.service;

import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Expenditure;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.expenditure.ExpenditureForm;
import com.jeorigagye.repository.CategoryRepository;
import com.jeorigagye.repository.ExpenditureRepository;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;
    private final MemberRepsitory memberRepsitory;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<Long> addExpenditure(Long memberId, ExpenditureForm expenditureForm){

        Category findCategory = categoryRepository.findById(expenditureForm.getCatId()).get();
        Member findMember = memberRepsitory.findById(memberId).get();

        Expenditure expenditure = Expenditure.builder()
                .price(expenditureForm.getPrice())
                .name(expenditureForm.getName())
                .member(findMember)
                .category(findCategory)
                .build();

        Expenditure saveExepnditure = expenditureRepository.save(expenditure);

        return new ResponseEntity<>(saveExepnditure.getId(), HttpStatus.OK);
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
