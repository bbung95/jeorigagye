package com.jeorigagye.dto.account;

import com.jeorigagye.domain.Account;
import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDto {

    private Long id;
    private String name;
    private int price;
    private LocalDateTime createDate;

    private Member member;
    private Category category;

    @Builder
    public AccountDto (Account account){
        this.id = account.getId();
        this.name = account.getName();
        this.price = account.getPrice();
        this.member = account.getMember();
        this.category = account.getCategory();
    }
}
