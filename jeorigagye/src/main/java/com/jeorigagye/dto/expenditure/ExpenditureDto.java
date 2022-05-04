package com.jeorigagye.dto.expenditure;

import com.jeorigagye.domain.Account;
import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Expenditure;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.account.AccountDto;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenditureDto {

    private Long id;
    private String name;
    private int price;
    private LocalDateTime createDate;
    private LocalDateTime expenditureDate;

    private Member member;
    private Category category;

    @Builder
    public ExpenditureDto(Expenditure expenditure){
        this.id = expenditure.getId();
        this.name = expenditure.getName();
        this.price = expenditure.getPrice();
        this.member = expenditure.getMember();
        this.category = expenditure.getCategory();
        this.createDate = expenditure.getCreatedDate();
        this.expenditureDate = expenditure.getExpenditureDate();
    }
}
