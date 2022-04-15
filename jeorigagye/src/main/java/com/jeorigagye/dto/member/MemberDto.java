package com.jeorigagye.dto.member;

import com.jeorigagye.domain.Member;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String membername;
    private String name;

    @Builder
    public MemberDto (Member member){
        this.id = member.getId();
        this.membername = member.getMembername();
        this.name = member.getName();
    }

}