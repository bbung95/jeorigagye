package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepsitory memberRepsitory;

    @Test
    public void memberSaveTest() throws Exception {
        //given
        MemberForm memberForm = new MemberForm();
        memberForm.setMembername("bbung95");
        memberForm.setPassword("1234");
        memberForm.setName("뻥뻥이");

        Member member = Member.builder(memberForm).build();

        //when
        memberRepsitory.save(member);

        //then
        Assertions.assertEquals(member.getId() , 1L);
    }
}