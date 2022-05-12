package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.member.MemberDto;
import com.jeorigagye.dto.member.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepsitory memberRepsitory;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Long> memberJoin(MemberForm form){

        System.out.println(form);
        membernameDuplicatedCheck(form.getMembername());

        Member member = Member.builder()
                .membername(form.getMembername())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .build();
        memberRepsitory.save(member);

        return new ResponseEntity<>(member.getId(), HttpStatus.OK);
    }

    public ResponseEntity<MemberDto> findById(Long memberId){

        Member findMember = memberRepsitory.findById(memberId).get();

        MemberDto memberDto = MemberDto.builder()
                .member(findMember)
                .build();

        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    public ResponseEntity<List<MemberDto>> findAll(Search search){

        PageRequest page = PageRequest.of(search.getCulPage(), 10);

        Page<Member> findMembers = memberRepsitory.findByMembernameContaining(search.getSearchKeyword(), page);

        List<MemberDto> memberList = findMembers.getContent()
                .stream()
                .map(Member::toMemberDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Long> memberUpdate(MemberDto memberDto){

        Member findMember = memberRepsitory.findById(memberDto.getId()).get();
        findMember.changeMemberInfo(memberDto);

        return new ResponseEntity<>(memberDto.getId(), HttpStatus.OK);
    }

    private void membernameDuplicatedCheck(String membername){

        Optional<Member> findMember = memberRepsitory.findByMembername(membername);

        if(findMember.isPresent()){
            throw new IllegalStateException("아이디가 중복됩니다.");
        }
    }
}