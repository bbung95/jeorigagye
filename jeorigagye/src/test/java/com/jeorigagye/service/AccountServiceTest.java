package com.jeorigagye.service;

import com.jeorigagye.domain.Account;
import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Member;
import com.jeorigagye.enums.AccountType;
import com.jeorigagye.repository.AccountRepository;
import com.jeorigagye.repository.MemberRepsitory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


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
    @DisplayName("입금 내역 등록_TEST")
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

        Account findAccount = accountRepository.findById(account.getId()).get();

        //then
        assertThat(account.getName()).isEqualTo(findAccount.getName());
        assertThat(account.getPrice()).isEqualTo(findAccount.getPrice());
        assertThat(account.getType()).isEqualTo(findAccount.getType());
        assertThat(account.getMember().getMembername()).isEqualTo(findAccount.getMember().getMembername());
    }
    
    // 출금 내역 등록
    @Test
    @DisplayName("출금 내역 등록_TEST")
    public void accountExpenditureSaveTest() throws Exception {
        //given
        Category category = em.find(Category.class, 1L);
        Member member = getMember("bbung95");
        memberRepsitory.save(member);

        Account account = Account.builder()
                .name("첫 입금")
                .price(100000)
                .type(AccountType.EXPENDITURE)
                .category(category)
                .member(member)
                .build();

        //when
        accountRepository.save(account);

        Account findAccount = accountRepository.findById(account.getId()).get();

        //then
        assertThat(account.getName()).isEqualTo(findAccount.getName());
        assertThat(account.getPrice()).isEqualTo(findAccount.getPrice());
        assertThat(account.getType()).isEqualTo(findAccount.getType());
        assertThat(account.getMember().getMembername()).isEqualTo(findAccount.getMember().getMembername());
    }

    @Test
    @DisplayName("입금내역 합계_TEST")
    public void accountIncomeSumTest() throws Exception {
        //given
        Category category = em.find(Category.class, 1L);
        Member member = getMember("bbung95");
        memberRepsitory.save(member);

        Account account1 = Account.builder()
                .name("첫 입금")
                .price(100000)
                .type(AccountType.INCOME)
                .category(category)
                .member(member)
                .build();

        Account account2 = Account.builder()
                .name("두번째 입금")
                .price(299900)
                .type(AccountType.INCOME)
                .category(category)
                .member(member)
                .build();

        accountRepository.save(account1);
        accountRepository.save(account2);

        //when
        int sumPrice = accountRepository.findByMemberIdWithIncomeSumPrice(member.getId(), AccountType.INCOME);

        //then
        assertThat(sumPrice).isEqualTo(399900);
    }

    @Test
    @DisplayName("출금내역 합계_TEST")
    public void accountExpenditureSumTest() throws Exception {
        //given
        Category category = em.find(Category.class, 1L);
        Member member = getMember("bbung95");
        memberRepsitory.save(member);

        Account account1 = Account.builder()
                .name("첫 출금")
                .price(20000)
                .type(AccountType.EXPENDITURE)
                .category(category)
                .member(member)
                .build();

        Account account2 = Account.builder()
                .name("두번째 출금")
                .price(15000)
                .type(AccountType.EXPENDITURE)
                .category(category)
                .member(member)
                .build();

        accountRepository.save(account1);
        accountRepository.save(account2);

        //when
        int sumPrice = accountRepository.findByMemberIdWithIncomeSumPrice(member.getId(), AccountType.EXPENDITURE);

        //then
        assertThat(sumPrice).isEqualTo(35000);
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
