package com.jeorigagye.repository;

import com.jeorigagye.domain.Expenditure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    @Query("select SUM(e.price) from Expenditure e where e.member.id = :memberId")
    public int findByMemberIdWithExpenditureSumPrice(Long memberId);

    @Query("select e from Expenditure e where e.member.id = :memberId")
    public Page findExpenditureBySearch(Long memberId, Pageable pageable);
}
