package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.member.MemberDto;
import com.jeorigagye.dto.member.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

        MemberDto memberDto = MemberDto.builder()
                .member(findMember)
                .build();

        return memberDto;
    }

    public List<MemberDto> findAll(Search search){

        PageRequest page = PageRequest.of(search.getCulPage(), 10);

        List<MemberDto> memberList = new ArrayList<>();
        Page<Member> findMembers = memberRepsitory.findByMembernameContaining(search.getSearchKeyword(), page);

        for (Member member: findMembers) {
            MemberDto memberDto = MemberDto.builder()
                    .member(member)
                    .build();
            memberList.add(memberDto);
        }

        return memberList;
    }

    private void membernameDuplicatedCheck(String membername){

        Member findMember = memberRepsitory.findByMembername(membername);

        if(findMember != null){
            throw new IllegalStateException("아이디가 중복됩니다.");
        }
    }
}