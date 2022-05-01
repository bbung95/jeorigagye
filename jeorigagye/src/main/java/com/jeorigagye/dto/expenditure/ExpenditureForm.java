package com.jeorigagye.dto.expenditure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExpenditureForm {

    private String name;
    private int price;
    private Long catId;
    private Long memberId;
}
