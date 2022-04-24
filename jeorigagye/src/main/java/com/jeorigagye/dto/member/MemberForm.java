package com.jeorigagye.dto.member;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    private String membername;
    private String password;
    private String name;
    private int salary;
}
