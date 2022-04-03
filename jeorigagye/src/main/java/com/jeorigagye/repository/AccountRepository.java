package com.jeorigagye.repository;

import com.jeorigagye.domain.Account;
import com.jeorigagye.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select SUM(a.price) from Account a where a.member.id = :memberId and a.type = :type")
    public int findByMemberIdWithIncomeSumPrice(Long memberId, AccountType type);
}
