package com.jeorigagye.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberForm {

    private String membername;

    private String password;

    private String name;

    private int salary;
}
