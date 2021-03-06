package com.jeorigagye.repository;

import com.jeorigagye.domain.Account;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.account.AccountDto;
import com.jeorigagye.enums.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select SUM(a.price) from Account a where a.member.id = :memberId and a.type = :type")
    public int findByMemberIdAndTypeWithAccountSumPrice(Long memberId, AccountType type);

    @Query("select distinct a from Account a left join a.member where a.type = :type and a.member.id = :memberId")
    public Page<Account> findAccountBySearch(Long memberId, AccountType type, Pageable pageable);
}
