package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseEntity;

import javax.persistence.*;

@Entity
public class GroupFriend extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;
}
