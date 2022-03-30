package com.jeorigagye.dto;

import com.jeorigagye.domain.Member;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "MemberDtoBuilder")
public class MemberDto {

    private Long id;
    private String membername;
    private String name;

    public static MemberDtoBuilder builder(Member member){
        return MemberDtoBuilder()
                .id(member.getId())
                .membername(member.getMembername())
                .name(member.getName());
    }
}
