package com.jeorigagye.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AccountSumPriceDto {

    private int incomeSumPrice;
    private int expenditureSumPrice;
}
