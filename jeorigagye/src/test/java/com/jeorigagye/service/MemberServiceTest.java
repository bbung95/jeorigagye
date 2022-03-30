package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepsitory memberRepsitory;

    @Autowired
    private EntityManager em;

    @Test
    @Rollback(value = false)
    public void memberSaveTest() throws Exception {
        //given
        Member member = getMember();

        //when
        memberRepsitory.save(member);

        //then
        Assertions.assertEquals(member.getId() , 1L);
    }
    
    @Test
    public void memberFindTest() throws Exception {
        //given
        Member member = getMember();
        memberRepsitory.save(member);
        em.clear();

        //when
        Member findMember = memberRepsitory.findById(member.getId()).get();

        //then
        Assertions.assertEquals(member.getId() , findMember.getId());
        Assertions.assertEquals(member.getMembername() , findMember.getMembername());
        Assertions.assertEquals(member.getName() , findMember.getName());
    }

    public Member getMember(){
        MemberForm memberForm = new MemberForm();
        memberForm.setMembername("bbung95");
        memberForm.setPassword("1234");
        memberForm.setName("뻥뻥이");

        Member member = Member.builder(memberForm).build();

        return member;
    }
}