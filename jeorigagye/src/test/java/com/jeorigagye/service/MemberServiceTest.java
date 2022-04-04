package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepsitory memberRepsitory;
    @Autowired
    private MemberService memberService;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("회원가입_TEST")
    public void memberSaveTest() throws Exception {
        //given
        Member member = getMember("bbung95");

        //when
        memberRepsitory.save(member);

        //then
        Assertions.assertEquals(member.getId() , 1L);
    }
    
    @Test
    @DisplayName("회원상세_TEST")
    public void memberFindTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        memberRepsitory.save(member);
        em.clear();

        //when
        Member findMember = memberRepsitory.findById(member.getId()).get();

        //then
        Assertions.assertEquals(member.getId() , findMember.getId());
        Assertions.assertEquals(member.getMembername() , findMember.getMembername());
        Assertions.assertEquals(member.getName() , findMember.getName());
    }
    
    @Test
    @DisplayName("회원아이디_중복_TEST")
    public void membernameDuplicateTest() throws Exception {
        //given
        Member member1 = getMember("bbung95");

        //when
        memberRepsitory.save(member1);

        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> {
            MemberForm form = new MemberForm();
            form.setMembername("bbung95");
            memberService.memberJoin(form);
        });

        //then
        Assertions.assertEquals(e.getMessage(), "아이디가 중복됩니다.");
    }
    
    public Member getMember(String membername){

        Member member = Member.builder()
                .membername(membername)
                .password("1234")
                .name("뻥뻥이")
                .build();

        return member;
    }
}