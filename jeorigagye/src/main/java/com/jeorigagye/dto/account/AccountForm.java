package com.jeorigagye.dto.account;

import com.jeorigagye.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountForm {

    private String name;
    private int price;
    private int type;
    private Long memberId;
    private Long catId;
}
