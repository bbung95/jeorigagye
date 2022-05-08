package com.jeorigagye.repository;

import com.jeorigagye.domain.Group;
import com.jeorigagye.dto.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.member.id = :memberId")
    public Page findGroupBySaerch(Long memberId, Pageable pageable);
}
