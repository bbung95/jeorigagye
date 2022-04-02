package com.jeorigagye.service;

import com.jeorigagye.domain.Account;
import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Member;
import com.jeorigagye.enums.AccountType;
import com.jeorigagye.repository.AccountRepository;
import com.jeorigagye.repository.MemberRepsitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MemberRepsitory memberRepsitory;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setCategory(){
        Category category = Category.builder()
                .name("식사")
                .image("image1")
                .build();

        em.persist(category);
        em.flush();
    }

    @Test
    @DisplayName("입금 내역 등록")
    public void accountIncomeSaveTest() throws Exception {
        //given
        Category category = em.find(Category.class, 1L);
        Member member = getMember("bbung95");
        memberRepsitory.save(member);

        Account account = Account.builder()
                .name("첫 입금")
                .price(100000)
                .type(AccountType.INCOME)
                .category(category)
                .member(member)
                .build();

        //when
        accountRepository.save(account);

        //then
        //Assertions.assertThat().
    }
    
    // 출금 내역 등록
    @Test
    @DisplayName("출금 내역 등록")
    public void accountExpenditureSaveTest() throws Exception {
        //given
        
        //when
        
        //then
    
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
