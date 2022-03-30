package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.MemberDto;
import com.jeorigagye.dto.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepsitory memberRepsitory;

    @Transactional
    public Long memberJoin(MemberForm form){

        Member member = Member.builder(form).build();

        memberRepsitory.save(member);

        return member.getId();
    }

    public MemberDto findById(Long memberId){

        Member findMember = memberRepsitory.findById(memberId).get();

        MemberDto memberDto = MemberDto.builder(findMember).build();

        return memberDto;
    }
}