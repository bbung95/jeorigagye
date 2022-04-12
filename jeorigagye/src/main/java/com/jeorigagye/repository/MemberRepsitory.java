package com.jeorigagye.repository;

import com.jeorigagye.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepsitory extends JpaRepository<Member , Long> {

    public Optional<Member> findByMembername(String membername);
}
