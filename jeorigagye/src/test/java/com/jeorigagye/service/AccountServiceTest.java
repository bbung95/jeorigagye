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

import static org.assertj.core.api.Assertions.assertThat;

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

        Account account = getAccount(AccountType.INCOME, category, member);

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

        Account account = getAccount(AccountType.INCOME, category, member);

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

        Account account1 = getAccount(AccountType.INCOME, category, member);
        Account account2 = getAccount(AccountType.INCOME, category, member);

        accountRepository.save(account1);
        accountRepository.save(account2);

        //when
        int sumPrice = accountRepository.findByMemberIdAndTypeWithAccountSumPrice(member.getId(), AccountType.INCOME);

        //then
        assertThat(sumPrice).isEqualTo(account1.getPrice()+account2.getPrice());
    }

    @Test
    @DisplayName("출금내역 합계_TEST")
    public void accountExpenditureSumTest() throws Exception {
        //given
        Category category = em.find(Category.class, 1L);
        Member member = getMember("bbung95");
        memberRepsitory.save(member);

        Account account1 = getAccount(AccountType.EXPENDITURE, category, member);
        Account account2 = getAccount(AccountType.EXPENDITURE, category, member);

        accountRepository.save(account1);
        accountRepository.save(account2);

        //when
        int sumPrice = accountRepository.findByMemberIdAndTypeWithAccountSumPrice(member.getId(), AccountType.EXPENDITURE);

        //then
        assertThat(sumPrice).isEqualTo(account1.getPrice()+account2.getPrice());
    }

    public Member getMember(String membername){

        Member member = Member.builder()
                .membername(membername)
                .password("1234")
                .name("뻥뻥이")
                .build();

        return member;
    };

    public Account getAccount(AccountType type, Category category, Member member){

        Account account = Account.builder()
                .name("입출금")
                .price(15000)
                .type(1)
                .category(category)
                .member(member)
                .build();

        return account;
    };
}
