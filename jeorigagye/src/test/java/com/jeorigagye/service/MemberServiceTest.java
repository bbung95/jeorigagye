package com.jeorigagye.service;

import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.member.MemberForm;
import com.jeorigagye.repository.MemberRepsitory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

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
    
    @Test
    @DisplayName("회원검색_TEST")
    public void memberPagingTest() throws Exception {
        //given
        memberRepsitory.save(getMember("bbung91"));
        memberRepsitory.save(getMember("bbung92"));
        memberRepsitory.save(getMember("bbung93"));
        memberRepsitory.save(getMember("bbung94"));
        memberRepsitory.save(getMember("bbung95"));
        memberRepsitory.save(getMember("bbung96"));
        memberRepsitory.save(getMember("bbung97"));
        memberRepsitory.save(getMember("bbung98"));
        memberRepsitory.save(getMember("bbung99"));
        memberRepsitory.save(getMember("bbung90"));

        PageRequest pageRequest = PageRequest.of(0, 5);
        //when
        Page<Member> page = memberRepsitory.findByMembernameContaining("bbung", pageRequest);
        List<Member> content = page.getContent();

        //then
        for (Member m: content) {
            System.out.println("m.getMembername() = " + m.getMembername());
        }
        System.out.println("page.getTotalElements() = " + page.getTotalElements());
        
        assertThat(content.size()).isEqualTo(5);
        assertThat(page.getTotalElements()).isEqualTo(10);
        assertThat(page.getNumber()).isEqualTo(0); // page 번호
        assertThat(page.getTotalPages()).isEqualTo(2); // 총 페이지
        assertThat(page.isFirst()).isTrue(); // 첫페이지
        assertThat(page.hasNext()).isTrue(); // 다음페이지
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