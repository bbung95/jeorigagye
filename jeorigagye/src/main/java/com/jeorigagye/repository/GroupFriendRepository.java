package com.jeorigagye.repository;

import com.jeorigagye.domain.GroupFriend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupFriendRepository extends JpaRepository<GroupFriend, Long> {

    @Query("select gf from GroupFriend gf where gf.group.id = :groupId")
    public Page findGroupFriendBySearch(Long groupId, Pageable pageable);
}
