package com.jeorigagye.repository;

import com.jeorigagye.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepsitory extends JpaRepository<Member , Long> {
}
