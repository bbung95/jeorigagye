package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.MemberDto;
import com.jeorigagye.dto.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepsitory memberRepsitory;
    private final PasswordEncoder passwordEncoder;

    public Long memberJoin(MemberForm form){

        membernameDuplicatedCheck(form.getMembername());

        Member member = Member.builder()
                .membername(form.getMembername())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .build();
        memberRepsitory.save(member);

        return member.getId();
    }

    public MemberDto findById(Long memberId){

        Member findMember = memberRepsitory.findById(memberId).get();

        MemberDto memberDto = MemberDto.builder(findMember).build();

        return memberDto;
    }

    private void membernameDuplicatedCheck(String membername){

        Member findMember = memberRepsitory.findByMembername(membername);

        if(findMember != null){
            throw new IllegalStateException("아이디가 중복됩니다.");
        }
    }
}