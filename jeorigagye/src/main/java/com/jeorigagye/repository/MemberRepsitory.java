package com.jeorigagye.repository;

import com.jeorigagye.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepsitory extends JpaRepository<Member , Long> {

    public Member findByMembername(String mambername);
}
