package com.jeorigagye.repository;

import com.jeorigagye.domain.Friend;
import com.jeorigagye.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select m from Friend f join Member m ON f.target.id = m.id where f.member.id = :memberId")
    public Page<Member> findFriendByMemberId(Long memberId, Pageable pageable);

    public Friend findFriendByMemberIdAndTargetId(Long memberId, Long targetId);

}
